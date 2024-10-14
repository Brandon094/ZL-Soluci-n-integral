package com.mycompany.zl_solucion_integral.vistas;

import com.mycompany.zl_solucion_integral.config.UtilVentanas;
import javax.swing.JOptionPane;

/*
 * Esta clase representa la ventana principal para administrar la base de datos 
 * en la aplicación 'zl_solucion_integral'. Ofrece opciones para registrar usuarios, 
 * productos, ventas e informes. La interfaz gráfica es generada usando Swing.
 * 
 * Al interactuar con esta ventana, los administradores pueden elegir diferentes 
 * funcionalidades relacionadas con la gestión de datos en la aplicación.
 * 
 * Controles disponibles:
 * - Registro de usuarios
 * - Registro de productos
 * - Registro de ventas
 * - Generación de informes
 * - Cerrar la aplicación
 * 
 * La funcionalidad de cada botón abre una nueva ventana que corresponde a la 
 * acción seleccionada, y la interfaz actual se oculta mientras el usuario interactúa 
 * con la nueva interfaz.
 * 
 * Autor: Dazac
 */
public class FormMainWindow extends javax.swing.JFrame {

    private FormRegistroUsuarios formUsuarios;
    private FormRegistroProductos formProductos;
    private FormRegistroVentas formVentas;
    private FormInformes formInformes;

    /**
     * Constructor de la clase que inicializa la ventana 'manager_DB'. Invoca al
     * método initComponents() para configurar los componentes gráficos.
     */
    public FormMainWindow() {
        initComponents();
        setTitle("Menu administrador");
        UtilVentanas.aplicarPantallaCompleta(this);
        btnInformes.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        btnRegistroProductos = new javax.swing.JButton();
        btnRegistroUsuarios = new javax.swing.JButton();
        btnRegistroVentas = new javax.swing.JButton();
        btnInformes = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnCerrarSecion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Console", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Administrar base de datos");

        btnSalir.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnSalir.setText("Salir de la app");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnRegistroProductos.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnRegistroProductos.setText("Registro de productos");
        btnRegistroProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroProductosActionPerformed(evt);
            }
        });

        btnRegistroUsuarios.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnRegistroUsuarios.setText("Registro de usuarios");
        btnRegistroUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroUsuariosActionPerformed(evt);
            }
        });

        btnRegistroVentas.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnRegistroVentas.setText("Registro de ventas");
        btnRegistroVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroVentasActionPerformed(evt);
            }
        });

        btnInformes.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnInformes.setText("Informes");
        btnInformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnRegistroVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistroUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistroProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addComponent(btnInformes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(104, 104, 104))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(btnRegistroProductos)
                .addGap(42, 42, 42)
                .addComponent(btnRegistroUsuarios)
                .addGap(42, 42, 42)
                .addComponent(btnRegistroVentas)
                .addGap(41, 41, 41)
                .addComponent(btnInformes)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        btnCerrarSecion.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnCerrarSecion.setText("Cerrar secion");
        btnCerrarSecion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSecionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCerrarSecion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 222, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(374, 374, 374)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCerrarSecion)
                    .addComponent(btnSalir))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Metodo para cerrar la aplicacion
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    // Método para abrir la ventana de registro de usuarios
    private void btnRegistroUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroUsuariosActionPerformed
        this.setVisible(false); // Ocultar la interfaz acutal    
        if (formUsuarios == null) {
            formUsuarios = new FormRegistroUsuarios();
        }
        formUsuarios.setVisible(true);
    }//GEN-LAST:event_btnRegistroUsuariosActionPerformed

    // Metodo para abrir la ventana de registro de productos
    private void btnRegistroProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroProductosActionPerformed
        this.setVisible(false); // Oculta la ventana actual
        if (formProductos == null) {
            formProductos = new FormRegistroProductos();
        }
        formProductos.setVisible(true);
    }//GEN-LAST:event_btnRegistroProductosActionPerformed

    // Método para abrir la ventana de registro de ventas
    private void btnRegistroVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroVentasActionPerformed
        this.setVisible(false); // Oculta la ventana actual
        if (formVentas == null) {
            formVentas = new FormRegistroVentas(true);
        }
        formVentas.setVisible(true);
    }//GEN-LAST:event_btnRegistroVentasActionPerformed

    // Método para abrir la ventana de informes
    private void btnInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformesActionPerformed
        this.setVisible(false); // Oculta la ventana actual
        if (formInformes == null) {
            formInformes = new FormInformes();
        }
        formInformes.setVisible(true); // Muestra la nueva ventana        
    }//GEN-LAST:event_btnInformesActionPerformed

    private void btnCerrarSecionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSecionActionPerformed
        int confirmed = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cerrar sesión?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            this.setVisible(false); // Oculta la interfaz actual
            FormLogIn log_In = new FormLogIn();
            log_In.setVisible(true); // Muestra la ventana de inicio de sesión
        }
    }//GEN-LAST:event_btnCerrarSecionActionPerformed

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
            java.util.logging.Logger.getLogger(FormMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSecion;
    private javax.swing.JButton btnInformes;
    private javax.swing.JButton btnRegistroProductos;
    private javax.swing.JButton btnRegistroUsuarios;
    private javax.swing.JButton btnRegistroVentas;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
