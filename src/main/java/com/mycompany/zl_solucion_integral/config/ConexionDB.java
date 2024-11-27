package com.mycompany.zl_solucion_integral.config;

import com.mycompany.zl_solucion_integral.vistas.FormLogIn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Clase para manejar la conexión con la base de datos SQLite.
 *
 * La clase `ConexionDB` se encarga de establecer y cerrar la conexión con una
 * base de datos SQLite, así como de crear las tablas necesarias en la base de
 * datos si no existen.
 *
 * @author Dazac
 */
public class ConexionDB {

    // Objeto de conexión
    Connection conectar = null;

    // Nombre de la base de datos
    String db = "db";

    // Ruta completa de la base de datos (usando la ubicación del proyecto actual)
    String cadena = "jdbc:sqlite:" + System.getProperty("user.dir") + "/" + db;

    // Referencia a la ventana de inicio de sesión (se usa para mostrar mensajes)
    FormLogIn log_In = new FormLogIn();

    /**
     * Establece la conexión con la base de datos SQLite.
     *
     * Carga el controlador JDBC para SQLite y establece la conexión a la base
     * de datos. Si ocurre un error, se muestra un mensaje de error al usuario.
     *
     * @return Un objeto `Connection` para realizar consultas SQL. Retorna
     * `null` si ocurre un error.
     */
    public Connection establecerConexion() {
        try {
            // Cargar el controlador JDBC de SQLite
            Class.forName("org.sqlite.JDBC");
            // Establecer conexión con la base de datos
            conectar = DriverManager.getConnection(cadena);
            System.out.println("Se conectó correctamente a la base de datos");
        } catch (Exception e) {
            // Mostrar mensaje de error si la conexión falla
            System.out.println("No se conectó correctamente con la base de datos, error: " + e.toString());
        }

        return conectar;
    }

    /**
     * Cierra la conexión con la base de datos.
     *
     * Verifica si la conexión no es nula antes de intentar cerrarla. Muestra un
     * mensaje de error si ocurre un problema al cerrar la conexión.
     */
    public void cerrarConexion() {
        try {
            if (conectar != null) {
                conectar.close();
                System.out.println("Se cerró la conexión correctamente con la base de datos");
            }
        } catch (Exception e) {
            // Mostrar mensaje de error si ocurre un problema al cerrar la conexión
            System.out.println("No se cerró correctamente la base de datos, error: " + e.toString());
        }
    }

    /**
     * Crea la tabla 'usuarios' si no existe.
     *
     * La tabla 'usuarios' contiene las columnas: id, nombre, teléfono, email,
     * rol, y contraseña. Esta tabla almacena la información de los usuarios de
     * la aplicación.
     */
    public void crearTablaUsuariosSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT NOT NULL,"
                + "telefono TEXT NOT NULL,"
                + "email TEXT NOT NULL,"
                + "rol INTEGER NOT NULL,"
                + "contraseña TEXT NOT NULL"
                + ");";

        try (Connection conn = establecerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.execute();
            System.out.println("Tabla 'usuarios' verificada o creada correctamente.");

        } catch (Exception e) {
            System.out.println("Error al crear la tabla 'usuarios': " + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    /**
     * Crea la tabla 'productos' si no existe.
     *
     * La tabla 'productos' contiene las columnas: id, producto, precio,
     * cantidad, código y total. Esta tabla almacena la información de los
     * productos en la aplicación.
     */
    public void crearTablaProductosSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS productos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "producto TEXT NOT NULL,"
                + "precio REAL NOT NULL,"
                + "cantidad INTEGER NOT NULL,"
                + "codigo TEXT NOT NULL,"
                + "categoria TEXT"
                + ");";

        try (Connection conn = establecerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.execute();
            System.out.println("Tabla 'productos' verificada o creada correctamente.");

        } catch (Exception e) {
            System.out.println("Error al crear la tabla 'productos': " + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    /**
     * Crea la tabla 'ventas' si no existe.
     *
     * La tabla 'ventas' contiene las columnas: id, cliente, cc_cliente,
     * vendedor, fecha y total. Esta tabla almacena la información general de la
     * venta.
     */
    public void crearTablaVentasSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS ventas ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "cliente TEXT NOT NULL,"
                + "cc_cliente TEXT NOT NULL,"
                + "vendedor TEXT NOT NULL,"
                + "fecha DATE NOT NULL,"
                + "total REAL NOT NULL"
                + ");";

        try (Connection conn = establecerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.execute();
            System.out.println("Tabla 'ventas' verificada o creada correctamente.");

        } catch (Exception e) {
            System.out.println("Error al crear la tabla 'ventas': " + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    /**
     * Crea la tabla 'detalles_venta' si no existe.
     *
     * La tabla 'detalles_venta' contiene las columnas: id, venta_id, producto,
     * cantidad, código, precio y total. Esta tabla almacena la información
     * detallada de cada producto vendido en una venta específica.
     */
    public void crearTablaDetallesVentaSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS detalles_venta ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "venta_id INTEGER NOT NULL,"
                + "producto TEXT NOT NULL,"
                + "cantidad INTEGER NOT NULL,"
                + "codigo TEXT NOT NULL,"
                + "precio REAL NOT NULL,"
                + "total REAL NOT NULL,"
                + "FOREIGN KEY (venta_id) REFERENCES ventas(id)"
                + ");";

        try (Connection conn = establecerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.execute();
            System.out.println("Tabla 'detalles_venta' verificada o creada correctamente.");

        } catch (Exception e) {
            System.out.println("Error al crear la tabla 'detalles_venta': " + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }
}
