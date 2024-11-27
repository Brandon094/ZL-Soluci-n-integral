package com.mycompany.zl_solucion_integral.config;

import javax.swing.JTable;
import javax.swing.JTextField;

public class Listener {

    // Método genérico para agregar un MouseListener a cualquier JTable
    public void agregarListenerTabla(JTable table, JTextField[] fields, int[] columnIndices) {
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int filaSeleccionada = table.getSelectedRow();
                
                // Verificar si hay una fila seleccionada
                if (filaSeleccionada != -1) {
                    // Llenar los campos de texto con los datos de la fila seleccionada
                    for (int i = 0; i < fields.length; i++) {
                        // Asegurarse de que no se intente obtener un valor nulo
                        if (table.getValueAt(filaSeleccionada, columnIndices[i]) != null) {
                            fields[i].setText(table.getValueAt(filaSeleccionada, columnIndices[i]).toString());
                        } else {
                            fields[i].setText(""); // Si el valor es nulo, se limpia el campo
                        }
                    }
                }
            }
        });
    }
}
