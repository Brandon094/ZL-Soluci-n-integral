package com.mycompany.zl_solucion_integral.config;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que gestiona una pantalla de carga con una barra de progreso y un mensaje.
 * Se utiliza para mostrar al usuario el estado de una tarea en segundo plano.
 */
public class PantallaCarga {
    // Diálogo modal que contendrá la pantalla de carga.
    private final JDialog dialogo;
    // Barra de progreso para indicar el avance de la tarea.
    private final JProgressBar barraProgreso;
    // Etiqueta para mostrar mensajes al usuario.
    private final JLabel mensaje;

    /**
     * Constructor de la clase PantallaCarga.
     * @param padre La ventana principal que actúa como padre del diálogo modal.
     */
    public PantallaCarga(JFrame padre) {
        // Inicialización del diálogo modal.
        dialogo = new JDialog(padre, "Cargando...", true); // Modal para bloquear la ventana principal mientras se muestra.
        dialogo.setSize(300, 100); // Tamaño del diálogo.
        dialogo.setLocationRelativeTo(padre); // Ubica el diálogo en el centro de la ventana principal.
        dialogo.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Impide cerrar el diálogo manualmente.

        // Inicialización de la barra de progreso.
        barraProgreso = new JProgressBar(0, 100); // Rango de 0 a 100 para mostrar porcentajes.
        barraProgreso.setStringPainted(true); // Muestra el porcentaje como texto sobre la barra.

        // Inicialización de la etiqueta de mensaje.
        mensaje = new JLabel("Por favor, espere..."); // Mensaje predeterminado.
        mensaje.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto horizontalmente.

        // Configuración del panel principal.
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(mensaje, BorderLayout.NORTH); // Coloca el mensaje en la parte superior.
        panel.add(barraProgreso, BorderLayout.CENTER); // Coloca la barra de progreso en el centro.

        // Agrega el panel al diálogo.
        dialogo.add(panel);
    }

    /**
     * Actualiza el mensaje que se muestra en la pantalla de carga.
     * @param texto El nuevo mensaje que se debe mostrar.
     */
    public void setMensaje(String texto) {
        SwingUtilities.invokeLater(() -> mensaje.setText(texto)); // Actualiza el texto de forma segura en el hilo de la interfaz.
    }

    /**
     * Actualiza el valor de la barra de progreso.
     * @param progreso El porcentaje de progreso (0-100).
     */
    public void setProgreso(int progreso) {
        SwingUtilities.invokeLater(() -> barraProgreso.setValue(progreso)); // Actualiza el progreso de forma segura en el hilo de la interfaz.
    }

    /**
     * Muestra el diálogo de la pantalla de carga.
     */
    public void mostrar() {
        SwingUtilities.invokeLater(() -> dialogo.setVisible(true)); // Muestra el diálogo de forma segura en el hilo de la interfaz.
    }

    /**
     * Cierra el diálogo de la pantalla de carga.
     */
    public void cerrar() {
        SwingUtilities.invokeLater(() -> dialogo.dispose()); // Cierra el diálogo de forma segura en el hilo de la interfaz.
    }
}
