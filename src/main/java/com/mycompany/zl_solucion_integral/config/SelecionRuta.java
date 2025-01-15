package com.mycompany.zl_solucion_integral.config;

import java.io.File;
import javax.swing.JFileChooser;

public class SelecionRuta {

    // Método para seleccionar una ruta para guardar un archivo
    public static String obtenerRuta(String titulo, String nombrePredeterminado, String extension) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(titulo);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setSelectedFile(new File(nombrePredeterminado));

        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String ruta = archivoSeleccionado.getAbsolutePath();

            // Verificar y agregar la extensión si no está incluida
            if (!ruta.toLowerCase().endsWith(extension)) {
                ruta += extension;
            }
            return ruta;
        }
        return null; // Si el usuario cancela, retorna null
    }

    // Método para seleccionar un archivo para abrir
    public static String obtenerRutaAbrir(String titulo) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(titulo);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null; // Si el usuario cancela, retorna null
    }
}
