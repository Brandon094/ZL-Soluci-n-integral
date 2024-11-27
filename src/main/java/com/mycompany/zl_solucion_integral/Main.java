package com.mycompany.zl_solucion_integral;

import com.mycompany.zl_solucion_integral.config.ConexionDB;
import com.mycompany.zl_solucion_integral.config.DatabaseInitializer;
import com.mycompany.zl_solucion_integral.vistas.FormLogIn;
import com.mycompany.zl_solucion_integral.vistas.FormRegistroUsuarios;
import javax.swing.JOptionPane;

/**
 * Clase principal que inicia la aplicación.
 *
 * La clase `Main` es el punto de entrada de la aplicación. Su método `main` es
 * el que se ejecuta al iniciar el programa. Esta clase se encarga de
 * inicializar la base de datos y de mostrar el formulario de inicio de sesión
 * de la aplicación.
 *
 * @author Dazac
 */
public class Main {

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     *
     * Este método inicializa la base de datos y, si la inicialización es
     * exitosa, inicia la aplicación mostrando el formulario de inicio de
     * sesión. Si la inicialización de la base de datos falla, muestra un
     * mensaje de error.
     *
     * @param args Argumentos de línea de comandos (no utilizados en esta
     * aplicación).
     */
    public static void main(String[] args) {
        if (inicializarBaseDatos()) {
            iniciarAplicacion();
        }
    }

    /**
     * Método para inicializar la base de datos.
     *
     * Este método crea una instancia de `ConexionDB` y `DatabaseInitializer`
     * para inicializar las tablas necesarias en la base de datos. Si ocurre un
     * error durante la inicialización, se muestra un mensaje de error al
     * usuario.
     *
     * @return `true` si la base de datos se inicializa correctamente, `false`
     * en caso contrario.
     */
    private static boolean inicializarBaseDatos() {
        ConexionDB conexion = new ConexionDB();
        try {
            DatabaseInitializer dbInit = new DatabaseInitializer(conexion);
            dbInit.inicializarTablas();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al inicializar la base de datos: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para iniciar la aplicación.
     *
     * Este método crea e muestra el formulario de inicio de sesión
     * (`FormLogIn`). Este formulario es el punto de entrada para el usuario de
     * la aplicación.
     */
    private static void iniciarAplicacion() {
        FormLogIn loginForm = new FormLogIn();
        loginForm.setVisible(true);
       //FormRegistroUsuarios ru = new FormRegistroUsuarios();
       //ru.setVisible(true);
    }
}
