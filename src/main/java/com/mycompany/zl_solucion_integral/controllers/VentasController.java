package com.mycompany.zl_solucion_integral.controllers;

/**
 * Controlador para gestionar las operaciones con ventas.
 * <p>
 * Esta clase proporciona métodos para agregar, modificar, eliminar y mostrar
 * ventas en la base de datos.
 * </p>
 * Utiliza una instancia de `ConexionDB` para interactuar con la base de datos.
 *
 * @author Dazac
 */
import com.mycompany.zl_solucion_integral.config.ConexionDB;
import com.mycompany.zl_solucion_integral.models.Sesion;
import com.mycompany.zl_solucion_integral.models.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.JTable;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/* Clase encargada de manejar las operaciones 
relacionadas con las ventas en la base de datos*/
public class VentasController {

    private final ConexionDB conexion; // Variable global para la conexión
    private final Logger logger = Logger.getLogger(VentasController.class.getName());
    private final Sesion sesion;

    // Constructor
    public VentasController() {
        this.conexion = new ConexionDB(); // Instancia de la clase de conexión        
        this.sesion = null;
    }

    // Método para guardar venta
    public void guardarVenta(final Venta venta, JTable tablaVentas) {
        String sqlInsertVenta = "INSERT INTO ventas (producto, cantidad, codigo, precio, cliente, cc_cliente, vendedor, fecha, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlUpdateStock = "UPDATE productos SET cantidad = cantidad - ? WHERE codigo = ? AND cantidad >= ?"; // Consulta para restar cantidad y evitar negativos

        Connection conn = null;
        PreparedStatement psVenta = null;
        PreparedStatement psStock = null;

        try {
            conn = conexion.establecerConexion();
            conn.setAutoCommit(false);  // Iniciar una transacción

            // Actualizar el stock del inventario
            psStock = conn.prepareStatement(sqlUpdateStock);
            psStock.setInt(1, venta.getCantidad()); // Cantidad a restar
            psStock.setString(2, venta.getCodigo()); // Código del producto
            psStock.setInt(3, venta.getCantidad()); // Verifica que el stock actual sea suficiente

            int rowsAffected = psStock.executeUpdate();
            if (rowsAffected == 0) {
                // Si no se actualiza el stock, significa que el inventario no tiene suficiente cantidad
                throw new SQLException("No hay suficiente stock disponible para realizar la venta.");
            }

            // Insertar la venta en la base de datos
            psVenta = conn.prepareStatement(sqlInsertVenta);
            psVenta.setString(1, venta.getProducto().getProducto()); // Producto
            psVenta.setInt(2, venta.getCantidad()); // Cantidad
            psVenta.setString(3, venta.getCodigo()); // Código del producto
            psVenta.setDouble(4, venta.getProducto().getPrecio()); // Precio
            psVenta.setString(5, venta.getNameCliente()); // Nombre del cliente
            psVenta.setString(6, venta.getNoCcCliente()); // CC del cliente
            psVenta.setString(7, sesion.getUsuarioLogueado()); // Vendedor (usuario logueado)
            psVenta.setDate(8, java.sql.Date.valueOf(venta.getFecha())); // Fecha
            psVenta.setDouble(9, venta.getTotal()); // Total

            psVenta.executeUpdate();

            // Si todo está bien, confirmar la transacción
            conn.commit();
            JOptionPane.showMessageDialog(null, "Venta guardada y stock actualizado exitosamente.");

            // Actualizar la tabla de ventas
            MostrarVentas(tablaVentas);
        } catch (SQLException e) {
            // Si ocurre algún error, revertir la transacción
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    logger.log(Level.SEVERE, "Error al revertir la transacción", rollbackEx);
                }
            }
            logger.log(Level.SEVERE, "Error al procesar la venta", e);
            JOptionPane.showMessageDialog(null, "Error al procesar venta: " + e.getMessage());
        } finally {
            // Cerrar las conexiones
            try {
                if (psVenta != null) {
                    psVenta.close();
                }
                if (psStock != null) {
                    psStock.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true); // Restaurar el auto-commit
                }
                conexion.cerrarConexion();
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Error al cerrar conexiones", ex);
            }
        }
    }

// Metodo para modificar una venta
    public void modificarVenta(final Venta venta, int idVenta, JTable tablaVentas) {
        String sqlUpdateVenta = "UPDATE ventas SET producto = ?, cantidad = ?, codigo = ?, precio = ?, cliente = ?, cc_cliente = ?, vendedor = ?, fecha = ?, total = ? WHERE id = ?";
        String sqlUpdateStockRestaurar = "UPDATE productos SET cantidad = cantidad + ? WHERE codigo = ?";  // Para restaurar el stock antiguo
        String sqlUpdateStockNuevo = "UPDATE productos SET cantidad = cantidad - ? WHERE codigo = ? AND cantidad >= ?";  // Para aplicar el nuevo stock

        Connection conn = null;
        PreparedStatement psVenta = null;
        PreparedStatement psStockRestaurar = null;
        PreparedStatement psStockNuevo = null;

        try {
            conn = conexion.establecerConexion();
            conn.setAutoCommit(false);  // Iniciar una transacción

            // Restaurar el stock del producto basado en la venta anterior
            psStockRestaurar = conn.prepareStatement(sqlUpdateStockRestaurar);
            psStockRestaurar.setInt(1, obtenerCantidadAnterior(idVenta));  // Cantidad anterior a restaurar
            psStockRestaurar.setString(2, venta.getCodigo());  // Código del producto
            psStockRestaurar.executeUpdate();

            // Actualizar el stock del producto con la nueva cantidad
            psStockNuevo = conn.prepareStatement(sqlUpdateStockNuevo);
            psStockNuevo.setInt(1, venta.getCantidad());  // Cantidad a restar
            psStockNuevo.setString(2, venta.getCodigo());  // Código del producto
            psStockNuevo.setInt(3, venta.getCantidad());  // Verifica que el stock actual sea suficiente

            int rowsAffected = psStockNuevo.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No hay suficiente stock disponible para la nueva cantidad.");
            }

            // Actualizar los datos de la venta
            psVenta = conn.prepareStatement(sqlUpdateVenta);
            psVenta.setString(1, venta.getProducto().getProducto());  // Producto
            psVenta.setInt(2, venta.getCantidad());  // Nueva cantidad
            psVenta.setString(3, venta.getCodigo());  // Código del producto
            psVenta.setDouble(4, venta.getProducto().getPrecio());  // Precio
            psVenta.setString(5, venta.getNameCliente());  // Cliente
            psVenta.setString(6, venta.getNoCcCliente());  // CC Cliente
            psVenta.setString(7, sesion.getUsuarioLogueado());  // Vendedor
            psVenta.setDate(8, java.sql.Date.valueOf(venta.getFecha()));  // Fecha
            psVenta.setDouble(9, venta.getTotal());  // Total
            psVenta.setInt(10, idVenta);  // ID de la venta a modificar

            psVenta.executeUpdate();

            // Confirmar la transacción
            conn.commit();
            JOptionPane.showMessageDialog(null, "Venta modificada y stock actualizado exitosamente.");

            // Actualizar la tabla de ventas
            MostrarVentas(tablaVentas);
        } catch (SQLException e) {
            // Revertir la transacción en caso de error
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    logger.log(Level.SEVERE, "Error al revertir la transacción", rollbackEx);
                }
            }
            logger.log(Level.SEVERE, "Error al modificar la venta", e);
            JOptionPane.showMessageDialog(null, "Error al modificar venta: " + e.getMessage());
        } finally {
            // Cerrar las conexiones
            try {
                if (psVenta != null) {
                    psVenta.close();
                }
                if (psStockRestaurar != null) {
                    psStockRestaurar.close();
                }
                if (psStockNuevo != null) {
                    psStockNuevo.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);  // Restaurar el auto-commit
                }
                conexion.cerrarConexion();
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Error al cerrar conexiones", ex);
            }
        }
    }

// Método para obtener la cantidad anterior de una venta
    private int obtenerCantidadAnterior(int idVenta) {
        String sql = "SELECT cantidad FROM ventas WHERE id = ?";
        try (Connection conn = conexion.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("cantidad");
            } else {
                throw new SQLException("No se encontró la venta con ID: " + idVenta);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener la cantidad anterior de la venta", e);
            return 0;
        }
    }

    // Metodo para obtener el id de la venta selecionada 
    public int obtenerIdVentaSeleccionado(final JTable tabla) {
        // Obtener el índice de la fila seleccionada
        int filaSeleccionada = tabla.getSelectedRow();

        // Verificar si hay una fila seleccionada
        if (filaSeleccionada != -1) {
            // Obtener el valor de la primera columna (ID del producto) y convertirlo a entero
            return Integer.parseInt(tabla.getValueAt(filaSeleccionada, 0).toString());
        } else {
            // No se ha seleccionado ninguna fila, devolver -1
            return -1;
        }
    }

    /**
     * Método para mostrar todos las ventas en una tabla.
     *
     * Este método realiza una consulta a la base de datos para obtener todos
     * las ventas almacenados y los muestra en una tabla gráfica (`JTable`). Se
     * actualiza la tabla con las columnas de "Id", "Producto", "Precio",
     * "Cantidad", "Código" y "Precio Total".
     *
     * @param tablaVentas La tabla (`JTable`) donde se mostrarán los productos.
     */
    public void MostrarVentas(JTable tablaVentas) {
        // Crear un nuevo modelo de tabla
        final DefaultTableModel modelo = new DefaultTableModel();

        // Definir las columnas del modelo
        modelo.addColumn("Id");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Código");
        modelo.addColumn("Precio");
        modelo.addColumn("Cliente");
        modelo.addColumn("CC Cliente");
        modelo.addColumn("Vendedor");
        modelo.addColumn("Fecha");
        modelo.addColumn("Precio Total");

        // Establecer el modelo de tabla vacío antes de cargar los datos
        tablaVentas.setModel(modelo);

        // Consulta SQL para obtener todas las ventas
        final String sql = "SELECT * FROM ventas";

        try (Connection conn = conexion.establecerConexion(); // Conexión a la base de datos
                 Statement st = conn.createStatement(); // Crear un Statement
                 ResultSet rs = st.executeQuery(sql)) {  // Ejecutar la consulta SQL y obtener el resultado

            // Recorrer el ResultSet para agregar las ventas al modelo de la tabla
            while (rs.next()) {
                double precio = rs.getDouble("precio");  // Obtener el precio del producto
                int cantidad = rs.getInt("cantidad");  // Obtener la cantidad vendida
                double precioTotal = precio * cantidad;  // Calcular el precio total (precio * cantidad)

                // Obtener la fecha como java.sql.Date (puede ser java.sql.Timestamp si es el caso)
                Date fecha = rs.getDate("fecha");

                // Formatear la fecha antes de mostrarla
                String fechaFormateada = formatearFecha(fecha);
                // Agregar una nueva fila al modelo con los datos de la venta
                modelo.addRow(new Object[]{
                    rs.getInt("id"), // ID de la venta
                    rs.getString("producto"), // Producto vendido
                    cantidad, // Cantidad vendida
                    rs.getString("codigo"), // Código del producto
                    precio, // Precio unitario
                    rs.getString("cliente"), // Nombre del cliente
                    rs.getString("cc_cliente"), // Cédula del cliente
                    rs.getString("vendedor"), // Nombre del vendedor
                    fechaFormateada, // Fecha de la venta
                    precioTotal // Precio total
                });
            }

            // Asignar el modelo a la tabla para mostrar las ventas
            tablaVentas.setModel(modelo);
        } catch (Exception e) {
            // Manejo de errores si ocurre un problema al obtener las ventas
            logger.log(Level.SEVERE, "Error al mostrar ventas", e);
            JOptionPane.showMessageDialog(null, "Error al mostrar ventas: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
    }

    // Método para formatear la fecha
    private String formatearFecha(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  // Formato de fecha deseado
        return sdf.format(fecha);  // Retorna la fecha en formato legible
    }
}
