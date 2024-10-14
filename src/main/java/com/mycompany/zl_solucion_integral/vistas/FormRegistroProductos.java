package com.mycompany.zl_solucion_integral.vistas;

import com.mycompany.zl_solucion_integral.config.UtilVentanas;
import com.mycompany.zl_solucion_integral.controllers.ProductoController;
import com.mycompany.zl_solucion_integral.models.Producto;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * Ventana para registrar y gestionar productos. Controla el registro,
 * modificación y eliminación de productos.
 *
 * @author Dazac
 */
public class FormRegistroProductos extends javax.swing.JFrame {
    // Controlador para manejar las operaciones con los productos

    ProductoController productoCtrl = new ProductoController();

    /*
    * Constructor que inicaliza la ventana y muestra los productos en la tabla
     */
    public FormRegistroProductos() {
        initComponents();
        setTitle("Registro de productos");
        UtilVentanas.aplicarPantallaCompleta(this);
        // Mostrar los datos de la tabla productos
        productoCtrl.mostrarProductos(tbProductos);
        // inicializar el metodo para obtener los datos del producto selecionado
        agregarListenerTablaProductos(tbProductos);
    }

    // Método para obtener los datos del formulario 
    private Producto obtenerDatosFormulario() {
        // Obtener los datos del formulario
        String productoNombre = txtProducto.getText();
        String cantidadStr = txtCantidad.getText();
        String precioStr = txtPrecio.getText();
        String codigo = txtCodigo.getText();

        // Inicializar variables para cantidad y precio
        int cantidad = 0;
        double precio = 0.0;

        try {
            // Parsear cantidad y precio
            cantidad = Integer.parseInt(cantidadStr);
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            // Mostrar un mensaje si ocurre un error en el parseo
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero y el precio un número decimal válido.");
            return null; // Retornar null si hay un error en el parseo
        }

        // Crear y retornar un objeto Producto con los datos
        return new Producto(productoNombre, precio, cantidad, codigo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnMenuPricipal = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtProducto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbProductos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "producto", "precio", "cantidad", "precio total"
            }
        ));
        jScrollPane1.setViewportView(tbProductos);

        jLabel1.setFont(new java.awt.Font("Lucida Console", 0, 36)); // NOI18N
        jLabel1.setText("Registro de Productos");

        btnMenuPricipal.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnMenuPricipal.setText("Menu principal");
        btnMenuPricipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPricipalActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese los datos para realizar el registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14))); // NOI18N

        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnGuardar.setText("Guardar ");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Producto:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Precio:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Cantidad:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Codigo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenuPricipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(jLabel1)
                .addContainerGap(440, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMenuPricipal)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción para cerrar la aplicación.
     */
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // Cerrar la aplicacion
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed
    /**
     * Acción para volver al menú principal.
     */
    private void btnMenuPricipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPricipalActionPerformed
        // cerrar la ventana actual 
        this.setVisible(false);
        // Abrir una ventana de magerDB
        FormMainWindow dbManager = new FormMainWindow();
        dbManager.setVisible(true);
    }//GEN-LAST:event_btnMenuPricipalActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
    }//GEN-LAST:event_txtCodigoActionPerformed
    /**
     * Método para guardar un nuevo producto.
     */
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Obtener los datos del formulario
        Producto producto = obtenerDatosFormulario();

        // Verificar si el producto es nulo (error en la conversión)
        if (producto == null) {
            return; // Ya se ha mostrado un mensaje de error en obtenerDatosFormulario()
        }

        // Validar que los campos no estén vacíos
        if (producto.getProducto().isEmpty() || producto.getCantidad() <= 0 || producto.getPrecio() <= 0 || producto.getCodigo().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos correctamente.");
            return;
        }

        try {
            // Calcular el total (precio * cantidad)
            double total = producto.getCantidad() * producto.getPrecio();
            producto.setTotal(total);

            // Agregar o actualizar el producto en la base de datos
            productoCtrl.agregarOActualizarProductoSiExiste(producto);

            // Actualizar los datos de la tabla
            productoCtrl.mostrarProductos(tbProductos);

            // Limpiar los campos del formulario después de guardar
            limpiarFormulario();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el producto: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // Obtener el ID del producto seleccionado en la tabla
        int idProducto = productoCtrl.obtenerIdProductoSeleccionado(tbProductos);

        // Obtener información del producto para mostrar en el formulario de confirmación
        Producto producto = productoCtrl.obtenerProductoPorId(idProducto);
        String infoProducto = producto.toString();

        // Llamar al formulario de confirmación de eliminación
        FormConfEliminacion formConfEliminacion = new FormConfEliminacion(idProducto, tbProductos, infoProducto);
        formConfEliminacion.setVisible(true);
    }//GEN-LAST:event_btnEliminarActionPerformed

    // Método para agregar un MouseListener a la tabla de productos
    private void agregarListenerTablaProductos(JTable tbProductos) {
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Obtener la fila seleccionada
                int filaSeleccionada = tbProductos.getSelectedRow();

                // Verificar si hay una fila seleccionada
                if (filaSeleccionada != -1) {
                    // Llenar los campos de texto con los datos del producto seleccionado
                    txtProducto.setText(tbProductos.getValueAt(filaSeleccionada, 1).toString()); // Columna "producto"
                    txtCantidad.setText(tbProductos.getValueAt(filaSeleccionada, 3).toString()); // Columna "cantidad"
                    txtPrecio.setText(tbProductos.getValueAt(filaSeleccionada, 2).toString());   // Columna "precio"
                    txtCodigo.setText(tbProductos.getValueAt(filaSeleccionada, 4).toString());   // Columna "codigo"
                }
            }
        });
    }

    // Metodo para modificar un producto selecionado
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // Obtener los datos del formulario
        Producto producto = obtenerDatosFormulario();

        // Verificar si el producto es nulo (error en la conversión)
        if (producto == null) {
            return; // Ya se ha mostrado un mensaje de error en obtenerDatosFormulario()
        }

        // Validar que los campos no estén vacíos
        if (producto.getProducto().isEmpty() || producto.getCantidad() <= 0 || producto.getPrecio() <= 0 || producto.getCodigo().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos correctamente.");
            return;
        }

        try {
            // Obtener el ID del producto seleccionado en la tabla
            int idProducto = productoCtrl.obtenerIdProductoSeleccionado(tbProductos);

            if (idProducto != -1) {
                // Calcular el total (precio * cantidad)
                double total = producto.getCantidad() * producto.getPrecio();
                producto.setTotal(total);
                producto.setId(idProducto);

                // Llamar al método del controlador para modificar el producto
                productoCtrl.modificarProducto(producto);

                // Actualizar la tabla de productos
                productoCtrl.mostrarProductos(tbProductos);

                // Limpiar los campos del formulario
                limpiarFormulario();

            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un producto para modificar.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar el producto: " + e.getMessage());
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    // Metodo para Limpiar el formulario  
    private void limpiarFormulario() {
        txtProducto.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        txtCodigo.setText("");
    }

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
            java.util.logging.Logger.getLogger(FormRegistroProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormRegistroProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormRegistroProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormRegistroProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormRegistroProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMenuPricipal;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
}
