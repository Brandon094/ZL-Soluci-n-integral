package com.mycompany.zl_solucion_integral.util;

import com.mycompany.zl_solucion_integral.config.ConexionDB;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * La clase ExcelSQLiteManager proporciona métodos para importar datos de un
 * archivo Excel a una base de datos SQLite.
 */
public class ExcelSQLiteManager {

    // Se declara 'conexion' como estático para acceder desde un método estático
    private static ConexionDB conexion = new ConexionDB();

    public static void importarExcel(String rutaExcel, String nombreBD, String nombreTabla) {
        Connection conn = null;
        try {
            conn = conexion.establecerConexion();
            if (conn != null) {
                System.out.println("Conexión a SQLite establecida.");

                try (FileInputStream fis = new FileInputStream(rutaExcel); Workbook workbook = new XSSFWorkbook(fis)) {
                    // Recorrer todas las hojas del archivo Excel
                    for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                        Sheet sheet = workbook.getSheetAt(sheetIndex);
                        System.out.println("Importando hoja: " + sheet.getSheetName());

                        // Crear tabla en SQLite si no existe (esto podría cambiar dependiendo de si quieres hacer esto por cada hoja)
                        String createTableSQL = generarSQLCreacionTabla(sheet, nombreTabla);
                        try (PreparedStatement stmt = conn.prepareStatement(createTableSQL)) {
                            stmt.executeUpdate();
                        }

                        // Procesar las filas de la hoja
                        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Empezar en la segunda fila (i = 1)
                            Row row = sheet.getRow(i);

                            if (row == null) {
                                continue; // Si la fila está vacía, saltar a la siguiente
                            }
                            // Leer los datos de cada columna solo si la celda existe y no está vacía
                            String codigoProducto = (row.getCell(3) != null && !row.getCell(3).toString().isEmpty()) ? row.getCell(3).toString().trim() : "";
                            String nombreProducto = (row.getCell(0) != null && !row.getCell(0).toString().isEmpty()) ? row.getCell(0).toString().trim() : "";
                            String categoria = (row.getCell(4) != null && !row.getCell(4).toString().isEmpty()) ? row.getCell(4).toString().trim() : "Sin Categoría";
                            double precio = (row.getCell(1) != null && !row.getCell(1).toString().isEmpty()) ? obtenerNumeroDeCelda(row.getCell(1)) : 0.0;
                            double cantidad = (row.getCell(2) != null && !row.getCell(2).toString().isEmpty()) ? obtenerNumeroDeCelda(row.getCell(2)) : 0.0;

                            // Verificar si el producto tiene al menos un dato válido
                            if (!codigoProducto.isEmpty() && !nombreProducto.isEmpty()) {
                                // Verificar si el producto ya existe
                                String selectSQL = "SELECT cantidad FROM " + nombreTabla + " WHERE codigo = ?";
                                try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
                                    selectStmt.setString(1, codigoProducto);
                                    ResultSet rs = selectStmt.executeQuery();

                                    if (rs.next()) {
                                        double cantidadExistente = rs.getDouble("cantidad");
                                        String updateSQL = "UPDATE " + nombreTabla + " SET cantidad = ?, categoria = ?, precio = ? WHERE codigo = ?";
                                        try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
                                            updateStmt.setDouble(1, cantidadExistente + cantidad); // actualizar cantidad
                                            updateStmt.setString(2, categoria); // actualizar categoría
                                            updateStmt.setDouble(3, precio); // actualizar precio
                                            updateStmt.setString(4, codigoProducto); // código producto
                                            updateStmt.executeUpdate();
                                        }
                                    } else {
                                        String insertSQL = "INSERT INTO " + nombreTabla + " (producto, precio, cantidad, codigo, categoria) VALUES (?, ?, ?, ?, ?)";
                                        try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
                                            insertStmt.setString(1, nombreProducto);
                                            insertStmt.setDouble(2, precio);
                                            insertStmt.setDouble(3, cantidad);
                                            insertStmt.setString(4, codigoProducto);
                                            insertStmt.setString(5, categoria);
                                            insertStmt.executeUpdate();
                                        }
                                    }
                                }
                            }
                        }
                    }

                    System.out.println("Datos importados correctamente a la base de datos.");
                }
            } else {
                System.out.println("Error al establecer la conexión a la base de datos.");
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

// Método auxiliar para obtener un número de una celda, validando que sea numérico
    private static double obtenerNumeroDeCelda(Cell cell) {
        if (cell == null) {
            return 0.0;
        }

        // Si la celda es un número, retornamos el valor como double
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }

        // Si la celda es texto, intentamos convertirla a un número
        try {
            String cellValue = cell.toString().trim();
            return Double.parseDouble(cellValue);
        } catch (NumberFormatException e) {
            return 0.0; // En caso de error, retornamos 0.0
        }
    }

    // Método para exportar datos desde una tabla SQLite a un archivo Excel
    public static void exportarDatosAExcel(String nombreTabla, String rutaExcel) {
        Connection conn = null;
        FileOutputStream fos = null;

        try {
            conn = conexion.establecerConexion();
            if (conn != null) {
                System.out.println("Conexión a SQLite establecida.");

                // Crear un libro de trabajo y una hoja de Excel
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet(nombreTabla);

                // Ejecutar una consulta para obtener los datos de la tabla sin la columna id
                String query = "SELECT producto, precio, cantidad, codigo, categoria FROM " + nombreTabla; // Omitir 'id'
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                // Obtener los metadatos de las columnas
                int columnCount = rs.getMetaData().getColumnCount();

                // Crear la fila de encabezado en la primera fila del Excel
                Row headerRow = sheet.createRow(0);
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = headerRow.createCell(i - 1);
                    cell.setCellValue(rs.getMetaData().getColumnName(i));
                }

                // Escribir los datos en las filas siguientes
                int rowIndex = 1;
                while (rs.next()) {
                    Row row = sheet.createRow(rowIndex++);
                    for (int i = 1; i <= columnCount; i++) {
                        Cell cell = row.createCell(i - 1);
                        cell.setCellValue(rs.getString(i));
                    }
                }

                // Escribir el libro de Excel en el archivo
                fos = new FileOutputStream(rutaExcel);
                workbook.write(fos);

                System.out.println("Datos exportados correctamente a " + rutaExcel);

                // Cerrar recursos
                rs.close();
                stmt.close();
                workbook.close();
            } else {
                System.out.println("Error al establecer la conexión a la base de datos.");
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (SQLException | IOException e) {
                System.out.println("Error al cerrar la conexión o el archivo: " + e.getMessage());
            }
        }
    }

    /**
     * Genera la sentencia SQL para crear una tabla en SQLite con el mismo
     * esquema que la primera fila de un archivo Excel.
     *
     * @param sheet La hoja del archivo Excel que contiene los datos.
     * @param nombreTabla Nombre de la tabla que se creará en SQLite.
     * @return La sentencia SQL para crear la tabla.
     */
    private static String generarSQLCreacionTabla(Sheet sheet, String nombreTabla) {
        Row headerRow = sheet.getRow(0);
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(nombreTabla).append(" (");

        for (int j = 0; j < headerRow.getLastCellNum(); j++) {
            String columnName = headerRow.getCell(j).getStringCellValue().trim();
            // Ajusta la columna de categoría para que acepte NULL o tenga un valor predeterminado
            if (columnName.equalsIgnoreCase("categoria")) {
                sb.append(columnName).append(" TEXT DEFAULT 'Sin Categoría'"); // Establece valor por defecto
            } else {
                sb.append(columnName).append(" TEXT");
            }

            if (j < headerRow.getLastCellNum() - 1) {
                sb.append(", ");
            }
        }
        sb.append(");");
        return sb.toString();
    }

    /**
     * Obtiene el número de filas en una hoja específica de un archivo Excel.
     *
     * @param rutaExcel La ruta del archivo Excel.
     * @return El número total de filas en la hoja principal (primera hoja).
     * @throws IOException Si ocurre un error al leer el archivo Excel.
     */
    public static int obtenerNumeroDeFilas(String rutaExcel) throws IOException {
        try (FileInputStream fis = new FileInputStream(rutaExcel); Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            return sheet.getLastRowNum() + 1; // Retorna el número de filas
        }
    }

    /**
     * Importa una fila específica de un archivo Excel a una tabla de SQLite.
     *
     * @param rutaExcel La ruta del archivo Excel.
     * @param nombreBD El nombre de la base de datos SQLite.
     * @param nombreTabla El nombre de la tabla en la base de datos.
     * @param filaIndex El índice de la fila a importar (0-based).
     * @throws IOException Si ocurre un error al leer el archivo Excel.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public static void importarFila(String rutaExcel, String nombreBD, String nombreTabla, int filaIndex) throws IOException, SQLException {
        try (FileInputStream fis = new FileInputStream(rutaExcel); Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            Row row = sheet.getRow(filaIndex);

            // Ignorar encabezados
            if (filaIndex == 0 || row == null) {
                return; // Si es la fila de encabezados o está vacía, no hacer nada
            }

            // Leer datos de las celdas de la fila
            String codigoProducto = (row.getCell(3) != null) ? row.getCell(3).toString().trim() : "";
            String nombreProducto = (row.getCell(0) != null) ? row.getCell(0).toString().trim() : "";

            // Validar y leer cantidad
            double cantidad = 0.0;
            if (row.getCell(2) != null) {
                Cell cellCantidad = row.getCell(2);
                if (cellCantidad.getCellType() == CellType.NUMERIC) {
                    cantidad = cellCantidad.getNumericCellValue();
                } else {
                    // Manejo de error para celdas no numéricas
                    throw new IllegalArgumentException("La celda en la columna 'CANTIDAD' no es numérica: " + cellCantidad.toString());
                }
            }

            // Leer categoría
            String categoria = (row.getCell(4) != null) ? row.getCell(4).toString().trim() : "Sin Categoría";

            // Validar y leer precio
            double precio = 0.0;
            if (row.getCell(1) != null) {
                Cell cellPrecio = row.getCell(1);
                if (cellPrecio.getCellType() == CellType.NUMERIC) {
                    precio = cellPrecio.getNumericCellValue();
                } else {
                    throw new IllegalArgumentException("La celda en la columna 'PRECIO' no es numérica: " + cellPrecio.toString());
                }
            }

            // Verificar conexión a la base de datos
            try (Connection conn = conexion.establecerConexion()) {
                if (conn == null) {
                    throw new SQLException("No se pudo establecer conexión con la base de datos.");
                }

                // Verificar si el producto ya existe
                String selectSQL = "SELECT cantidad FROM " + nombreTabla + " WHERE codigo = ?";
                try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
                    selectStmt.setString(1, codigoProducto);
                    ResultSet rs = selectStmt.executeQuery();

                    if (rs.next()) {
                        double cantidadExistente = rs.getDouble("cantidad");
                        String updateSQL = "UPDATE " + nombreTabla + " SET cantidad = ?, categoria = ?, precio = ? WHERE codigo = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
                            updateStmt.setDouble(1, cantidadExistente + cantidad);
                            updateStmt.setString(2, categoria);
                            updateStmt.setDouble(3, precio);
                            updateStmt.setString(4, codigoProducto);
                            updateStmt.executeUpdate();
                        }
                    } else {
                        String insertSQL = "INSERT INTO " + nombreTabla + " (producto, precio, cantidad, codigo, categoria) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
                            insertStmt.setString(1, nombreProducto);
                            insertStmt.setDouble(2, precio);
                            insertStmt.setDouble(3, cantidad);
                            insertStmt.setString(4, codigoProducto);
                            insertStmt.setString(5, categoria);
                            insertStmt.executeUpdate();
                        }
                    }
                }
            }
        }
    }

}
