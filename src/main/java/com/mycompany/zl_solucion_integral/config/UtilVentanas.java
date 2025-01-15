package com.mycompany.zl_solucion_integral.config;

import javax.swing.JFrame;

/**
 *
 * @author Dazac
 */
public class UtilVentanas {

    // Método que configura una ventana en pantalla completa
    public static void aplicarPantallaCompleta(JFrame ventana) {
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
