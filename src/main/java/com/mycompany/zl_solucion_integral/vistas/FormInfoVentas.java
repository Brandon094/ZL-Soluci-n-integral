package com.mycompany.zl_solucion_integral.vistas;

import com.mycompany.zl_solucion_integral.config.Listener;
import com.mycompany.zl_solucion_integral.config.PantallaCarga;
import com.mycompany.zl_solucion_integral.config.SelecionRuta;

import com.mycompany.zl_solucion_integral.config.UtilVentanas;
import com.mycompany.zl_solucion_integral.controllers.VentasController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author Dazac
 */
public class FormInfoVentas extends javax.swing.JFrame {

    FormMainWindow formManagerWindow = new FormMainWindow();

    VentasController ventasCtrl = new VentasController();
    Listener listenerTb = new Listener();

    public FormInfoVentas() {
        initComponents();
        setTitle("Informe Ventas");
        UtilVentanas.aplicarPantallaCompleta(this);
        ventasCtrl.MostrarVentas(tbVentasInformes);
        contarVentasUnicas();
        calcularPrecioTotalVentas();
        // Crear los campos de texto a llenar con los datos de la fila seleccionada
        JTextField[] camposTexto = {txtFechaInicio, txtFecha};

        // Definir los índices de las columnas que quieres mostrar en los campos de texto
        int[] columnas = {9, 9};

        // Inicializar el método para obtener los datos del producto seleccionado
        listenerTb.agregarListenerTabla(tbVentasInformes, camposTexto, columnas);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVentasInformes = new javax.swing.JTable();
        btnMenuInformes = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFechaFinalizacion = new javax.swing.JTextField();
        btnFiltrar01 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        btnFiltrar02 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        textNumVentas = new javax.swing.JLabel();
        textPrecioTotalVentas = new javax.swing.JLabel();
        btnReiniciarFiltros = new javax.swing.JButton();
        btnExportarExcel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Informe de ventas");

        tbVentasInformes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbVentasInformes);

        btnMenuInformes.setText("Menu principal");
        btnMenuInformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuInformesActionPerformed(evt);
            }
        });

        btnSalir.setText("Cerrar App");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar por rango de fechas"));

        jLabel2.setText("Fecha de Inicio:");

        txtFechaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaInicioActionPerformed(evt);
            }
        });

        jLabel3.setText("Fecha Finalizacion:");

        txtFechaFinalizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaFinalizacionActionPerformed(evt);
            }
        });

        btnFiltrar01.setText("Filtrar");
        btnFiltrar01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrar01ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaFinalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(txtFechaInicio)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFiltrar01)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addComponent(txtFechaFinalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFiltrar01, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar por una fecha"));

        jLabel4.setText("Fecha:");

        btnFiltrar02.setText("Filtrar");
        btnFiltrar02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrar02ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(29, 29, 29)
                        .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnFiltrar02)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFiltrar02)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion de registros"));

        textNumVentas.setText("N° Total de ventas:");

        textPrecioTotalVentas.setText("Precio total de ventas:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textPrecioTotalVentas)
                    .addComponent(textNumVentas))
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textNumVentas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textPrecioTotalVentas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnReiniciarFiltros.setText("Reiniciar filtros");
        btnReiniciarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarFiltrosActionPerformed(evt);
            }
        });

        btnExportarExcel.setText("Exportar Excel");
        btnExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMenuInformes))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnReiniciarFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnExportarExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
            .addComponent(jSeparator2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnReiniciarFiltros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExportarExcel)
                        .addGap(49, 49, 49)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMenuInformes)
                    .addComponent(btnSalir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuInformesActionPerformed
        this.setVisible(false);
        if (formManagerWindow == null) {
            formManagerWindow = new FormMainWindow();
        }
        formManagerWindow.setVisible(true);
    }//GEN-LAST:event_btnMenuInformesActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtFechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaInicioActionPerformed

    private void txtFechaFinalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaFinalizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFinalizacionActionPerformed

    private void btnFiltrar01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrar01ActionPerformed
        String fechaInicio = txtFechaInicio.getText();
        String fechaFinalizacion = txtFechaFinalizacion.getText();

        // validar q los campos no esten vacios 
        if (fechaInicio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio no debe ser vacia.");
        }
        if (fechaFinalizacion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La fecha de finalización no debe ser vacía.\nSe pone la fecha actual en caso de estar vacía la fecha de finalización.");

            // Obtener la fecha formateada
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Ajusta el formato según lo necesites
            String fechaActual = now.format(formatter);

            txtFechaFinalizacion.setText(fechaActual);
            return;
        }
        ventasCtrl.mostrarFechasDefinidas(tbVentasInformes, fechaInicio, fechaFinalizacion);
        contarVentasUnicas();
        calcularPrecioTotalVentas();

    }//GEN-LAST:event_btnFiltrar01ActionPerformed

    public void contarVentasUnicas() {
        // Obtiene el modelo de la tabla
        javax.swing.table.TableModel modelo = tbVentasInformes.getModel();

        // Usamos un Set para almacenar los Id únicos
        java.util.Set<String> ventasUnicas = new java.util.HashSet<>();

        // Recorremos todas las filas de la tabla y agregamos los Id únicos al Set
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String idVenta = modelo.getValueAt(i, 0).toString(); // Supongamos que el Id está en la columna 0
            ventasUnicas.add(idVenta);
        }

        // El tamaño del Set representa el número de ventas únicas
        int totalVentasUnicas = ventasUnicas.size();

        // Muestra el número total de ventas únicas en el campo correspondiente
        textNumVentas.setText("Nª Total de ventas: " + String.valueOf(totalVentasUnicas));
    }

    // Metodo para sumar la columna 9 precio total para mostrarlo
    private void calcularPrecioTotalVentas() {
        // Obtiene el modelo de la tabla
        javax.swing.table.TableModel modelo = tbVentasInformes.getModel();

        // Variable para almacenar la suma total
        double sumaTotal = 0.0;

        // Recorremos todas las filas de la tabla
        for (int i = 0; i < modelo.getRowCount(); i++) {
            // Obtenemos el valor de la columna 11 (índice 10, porque las columnas empiezan en 0)
            String valorColumna = modelo.getValueAt(i, 10).toString();

            // Convertimos el valor a double y lo sumamos (manejo de excepciones por seguridad)
            try {
                double precio = Double.parseDouble(valorColumna);
                sumaTotal += precio;
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir a número: " + valorColumna);
            }
        }

        // Muestra la suma total en el campo correspondiente
        textPrecioTotalVentas.setText("Precio total de ventas: " +String.format("%.2f", sumaTotal));
    }


    private void btnFiltrar02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrar02ActionPerformed
        String fecha = txtFecha.getText();

        ventasCtrl.mostrarVentasPorDia(tbVentasInformes, fecha);
        contarVentasUnicas();
        calcularPrecioTotalVentas();
    }//GEN-LAST:event_btnFiltrar02ActionPerformed

    private void btnReiniciarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarFiltrosActionPerformed
        ventasCtrl.MostrarVentas(tbVentasInformes);
        // Limpiando los campos de texto
        txtFechaInicio.setText("");
        txtFechaFinalizacion.setText("");
        txtFecha.setText("");
        // Contar la ventas y el total de las ventas
        contarVentasUnicas();
        calcularPrecioTotalVentas();

    }//GEN-LAST:event_btnReiniciarFiltrosActionPerformed

    private void btnExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarExcelActionPerformed
        // Obtener la ruta de exportación utilizando la clase SeleccionRuta
        String rutaExcel = SelecionRuta.obtenerRuta("Exportar archivo Excel", "InformeVentas.xlsx", ".xlsx");

        if (rutaExcel != null) {
            // Crear una pantalla de carga
            PantallaCarga pantallaCarga = new PantallaCarga((JFrame) SwingUtilities.getWindowAncestor(this));

            // Crear un SwingWorker para ejecutar la exportación en segundo plano
            SwingWorker<Void, Void> exportacionWorker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    pantallaCarga.mostrar(); // Mostrar la pantalla de carga
                    pantallaCarga.setMensaje("Exportando datos visibles de la tabla...");

                    // Exportar los datos visibles de la tabla a un archivo Excel
                    ventasCtrl.exportarDatosTablaAExcel(tbVentasInformes, rutaExcel);

                    return null;
                }

                @Override
                protected void done() {
                    pantallaCarga.cerrar(); // Cerrar la pantalla de carga
                    JOptionPane.showMessageDialog(null, "Exportación completada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            };

            exportacionWorker.execute(); // Iniciar la tarea en segundo plano
        }
    }//GEN-LAST:event_btnExportarExcelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormInfoVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormInfoVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormInfoVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormInfoVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormInfoVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportarExcel;
    private javax.swing.JButton btnFiltrar01;
    private javax.swing.JButton btnFiltrar02;
    private javax.swing.JButton btnMenuInformes;
    private javax.swing.JButton btnReiniciarFiltros;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tbVentasInformes;
    private javax.swing.JLabel textNumVentas;
    private javax.swing.JLabel textPrecioTotalVentas;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFechaFinalizacion;
    private javax.swing.JTextField txtFechaInicio;
    // End of variables declaration//GEN-END:variables
}
