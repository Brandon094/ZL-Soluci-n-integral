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
                            double cantidad = (row.getCell(2) != null && !row.getCell(2).toString().isEmpty()) ? Double.parseDouble(row.getCell(2).toString()) : 0.0;
                            String categoria = (row.getCell(4) != null && !row.getCell(4).toString().isEmpty()) ? row.getCell(4).toString().trim() : "Sin Categoría";
                            double precio = (row.getCell(1) != null && !row.getCell(1).toString().isEmpty()) ? Double.parseDouble(row.getCell(1).toString()) : 0.0;

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

                // Ejecutar una consulta para obtener los datos de la tabla
                String query = "SELECT * FROM " + nombreTabla;
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
     * Genera la sentencia SQL para insertar datos en una tabla de SQLite,
     * basada en la primera fila de un archivo Excel.
     *
     * @param sheet La hoja del archivo Excel que contiene los datos.
     * @param nombreTabla Nombre de la tabla donde se insertarán los datos.
     * @return La sentencia SQL de inserción para la tabla.
     */
    private static String generarSQLInsercion(Sheet sheet, String nombreTabla) {
        Row headerRow = sheet.getRow(0);
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(nombreTabla).append(" (");

        for (int j = 0; j < headerRow.getLastCellNum(); j++) {
            sb.append(headerRow.getCell(j).getStringCellValue().trim());
            if (j < headerRow.getLastCellNum() - 1) {
                sb.append(", ");
            }
        }
        sb.append(") VALUES (");

        for (int j = 0; j < headerRow.getLastCellNum(); j++) {
            sb.append("?");
            if (j < headerRow.getLastCellNum() - 1) {
                sb.append(", ");
            }
        }
        sb.append(");");
        return sb.toString();
    }
}
