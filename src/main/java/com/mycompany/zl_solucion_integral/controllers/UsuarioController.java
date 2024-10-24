package com.mycompany.zl_solucion_integral.controllers;

import com.mycompany.zl_solucion_integral.config.ConexionDB;
import com.mycompany.zl_solucion_integral.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de manejar las operaciones relacionadas con los usuarios en
 * la base de datos.
 *
 * La clase `UsuarioController` proporciona métodos para agregar, modificar,
 * eliminar y mostrar usuarios, así como para validar credenciales y la
 * existencia de usuarios. Utiliza una instancia de `ConexionDB` para
 * interactuar con la base de datos.
 *
 * @author Dazac
 */
public class UsuarioController {

    private final ConexionDB conexion = new ConexionDB();
    private final Logger logger = Logger.getLogger(UsuarioController.class.getName());

    /**
     * Agrega un nuevo usuario a la base de datos.
     *
     * Verifica si el usuario ya existe antes de intentar agregarlo. Muestra un
     * mensaje de error si el usuario ya existe o si ocurre un problema durante
     * el registro.
     *
     * @param usuario Un objeto `Usuario` que contiene la información del nuevo
     * usuario.
     */
    public void agregarUsuario(final Usuario usuario) {
        // Verificar si el usuario ya existe
        if (validarExistenciaUsuario(usuario.getNombre())) {
            JOptionPane.showMessageDialog(null, "El usuario con ese nombre ya existe. Por favor, "
                    + "elija otro nombre.");
            return; // Detener la ejecución si el usuario ya existe
        }

        final String sql = "INSERT INTO usuarios (nombre, telefono, email, contraseña, rol) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conexion.establecerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer los parámetros en la consulta SQL
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getTelefono());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getContraseña());
            pstmt.setString(5, usuario.getRol());

            // Ejecutar la consulta SQL
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al registrar el usuario", e);
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
    }

    /**
     * Modifica un usuario existente en la base de datos.
     *
     * Actualiza la información del usuario con el ID especificado. Muestra un
     * mensaje de éxito si la actualización se realiza correctamente, o un
     * mensaje de error en caso contrario.
     *
     * @param nombre Nombre del usuario.
     * @param telefono Teléfono del usuario.
     * @param email Correo del usuario.
     * @param rol Rol del usuario.
     * @param contraseña Contraseña del usuario.
     * @param idUsuario ID del usuario a modificar.
     */
    public void modificarUsuario(final String nombre, final String telefono, final String email, final String rol, final String contraseña, final int idUsuario) {
        final String sql = "UPDATE usuarios SET nombre = ?, telefono = ?, email = ?, rol = ?, contraseña = ? WHERE id = ?";

        try (Connection conn = conexion.establecerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer los valores en la consulta
            pstmt.setString(1, nombre);
            pstmt.setString(2, telefono);
            pstmt.setString(3, email);
            pstmt.setString(4, rol);
            pstmt.setString(5, contraseña);
            pstmt.setInt(6, idUsuario);

            // Ejecutar la actualización
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar el usuario",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al modificar el usuario", e);
            JOptionPane.showMessageDialog(null, "Error al modificar el usuario: "
                    + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            conexion.cerrarConexion();
        }
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * Solicita confirmación antes de eliminar el usuario. Muestra un mensaje de
     * éxito si la eliminación se realiza correctamente, o un mensaje de error
     * en caso contrario.
     *
     * @param idUsuario ID del usuario a eliminar.
     * @param tbUsuarios Tabla de usuarios para actualizar después de la
     * eliminación.
     */
    public void eliminarUsuario(int idUsuario, JTable tbUsuarios) {
        if (idUsuario == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario de la tabla",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirmar la eliminación
        int confirmacion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea eliminar este usuario?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = conexion.establecerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUsuario);

            // Ejecutar la consulta
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
                mostrarUsuarios(tbUsuarios);  // Actualizar la tabla
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: "
                    + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            conexion.cerrarConexion();
        }
    }

    /**
     * Método para obtener el ID del usuario seleccionado en una tabla.
     *
     * Este método devuelve el ID del usuario seleccionado en la tabla
     * (`JTable`). Si no hay una fila seleccionada, devuelve `-1` para indicar
     * que no se ha seleccionado ningún usuario.
     *
     * @param tabla La tabla (`JTable`) de donde se obtiene el usuario
     * seleccionado.
     * @return El ID del usuario seleccionado o `-1` si no se ha seleccionado
     * ninguna fila.
     */
    public int obtenerIdUsuarioSeleccionado(final JTable tabla) {
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
     * Muestra todos los usuarios en una tabla.
     *
     * Consulta la base de datos para obtener la lista de usuarios y la muestra
     * en la tabla proporcionada.
     *
     * @param tablaUsuarios Tabla donde se mostrarán los usuarios.
     */
    public void mostrarUsuarios(final JTable tablaUsuarios) {
        final DefaultTableModel modelo = new DefaultTableModel();
        final String sql = "SELECT * FROM usuarios";

        modelo.addColumn("Id");
        modelo.addColumn("Usuario");
        modelo.addColumn("Email");
        modelo.addColumn("# Tel");
        modelo.addColumn("Rol");
        modelo.addColumn("Contraseña");

        tablaUsuarios.setModel(modelo);

        try (Connection conn = conexion.establecerConexion(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("rol"),
                    rs.getString("contraseña")
                });
            }
            tablaUsuarios.setModel(modelo);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al mostrar usuarios", e);
            JOptionPane.showMessageDialog(null, "Error al mostrar: " + e.toString());
        } finally {
            conexion.cerrarConexion();
        }
    }

    /**
     * Valida las credenciales de un administrador.
     *
     * @param usuario Nombre del usuario administrador.
     * @param contraseña Contraseña del usuario administrador.
     * @return `true` si las credenciales son válidas, `false` en caso
     * contrario.
     */
    public boolean validarCredencialesAdmin(final String usuario, final String contraseña) {
        // Convertir el nombre de usuario a minúsculas
        String usuarioEnMinusculas = usuario.toLowerCase();

        final String sql = "SELECT COUNT(*) FROM usuarios WHERE "
                + "nombre = ? AND contraseña = ? AND rol = 1";

        try (Connection conn = conexion.establecerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Asignar los parámetros
            pstmt.setString(1, usuarioEnMinusculas); // Usar el usuario en minúsculas
            pstmt.setString(2, contraseña);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al validar credenciales", e);
            JOptionPane.showMessageDialog(null, "Error al validar credenciales: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return false;
    }

    /**
     * Valida la existencia de un usuario.
     *
     * @param nombreUsuario Nombre del usuario a validar.
     * @return `true` si el usuario existe, `false` en caso contrario.
     */
    public boolean validarExistenciaUsuario(final String nombreUsuario) {
        final String sql = "SELECT COUNT(*) AS total FROM usuarios WHERE nombre = ?";

        try (Connection conn = conexion.establecerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt("total") > 0;
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al validar existencia del usuario", e);
            JOptionPane.showMessageDialog(null, "Error al validar existencia de usuario: "
                    + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return false;
    }

    /**
     * Valida las credenciales de un usuario regular.
     *
     * @param usuario Nombre del usuario.
     * @param contraseña Contraseña del usuario.
     * @return `true` si las credenciales son válidas, `false` en caso
     * contrario.
     */
    public boolean validarCredencialesUsuarioRegular(final String usuario, final String contraseña) {
        final String sql = "SELECT COUNT(*) FROM usuarios WHERE nombre = ? AND contraseña = ? AND rol != 1";

        try (Connection conn = conexion.establecerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario);
            pstmt.setString(2, contraseña);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al validar credenciales del usuario regular", e);
            JOptionPane.showMessageDialog(null, "Error al validar credenciales: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return false;
    }
}
