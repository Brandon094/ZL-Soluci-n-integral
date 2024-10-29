package com.mycompany.zl_solucion_integral.vistas;

import com.mycompany.zl_solucion_integral.controllers.VentasController;
import com.mycompany.zl_solucion_integral.models.Producto;
import com.mycompany.zl_solucion_integral.models.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dazac
 */
public class FormConfiVenta extends javax.swing.JFrame {

    private List<Venta> ventasCotizadas = new ArrayList<>();  // Variable para almacenar la lista de cotizaciónes
    private JTable tbVentas;
    private Venta primeraVenta = ventasCotizadas.get(0);// Asumimos que todos tienen el mismo cliente

    public FormConfiVenta(List<Venta> ventasCotizadas, JTable tbVentas) {
        initComponents();
        //UtilVentanas.aplicarPantallaCompleta(this);
        this.ventasCotizadas = ventasCotizadas;
        this.tbVentas = tbVentas;
        cargarDatosVentas();
    }

    // Método para cargar los datos de las ventas en los labels
    private void cargarDatosVentas() {
        if (ventasCotizadas != null && !ventasCotizadas.isEmpty()) {
            // Mostrar la información del cliente            
            textNombre.setText("Nombre: " + primeraVenta.getNameCliente());
            textCedula.setText("N° Cédula: " + primeraVenta.getNoCcCliente());
            textVendedor.setText("Vendedor: " + primeraVenta.getVendedor());

            // Inicializar total
            double totalGeneral = 0;

            // Crear un modelo para la lista de productos cotizados
            DefaultListModel<String> modeloLista = new DefaultListModel<>();

            // Recorrer la lista de ventas y llenar el modelo de la lista
            for (Venta venta : ventasCotizadas) {
                String productoInfo = String.format(
                        "Código: %s | Producto: %s | Precio unitario: %.2f | Cantidad: %d | Descuento: %.2f | Precio Total: %.2f",
                        venta.getProducto().getCodigo(),
                        venta.getProducto().getProducto(),
                        venta.getProducto().getPrecio(),
                        venta.getCantidad(),
                        venta.getDescuento(),
                        venta.getTotal()
                );
                modeloLista.addElement(productoInfo);
                totalGeneral += venta.getTotal(); // Sumar el total de cada venta
            }

            // Establecer el modelo en la lista
            listProCotizados.setModel(modeloLista);

            // Mostrar el total general
            textPrecioTotal.setText("Precio Total: " + totalGeneral);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnCancelar = new javax.swing.JButton();
        btnConfVenta = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        textNombre = new javax.swing.JLabel();
        textCedula = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listProCotizados = new javax.swing.JList<>();
        btnEliminar = new javax.swing.JButton();
        textPrecioTotal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        textVendedor = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("Confirmacion de venta");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setAutoscrolls(true);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnCancelar.setText("Cancelar Venta");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnConfVenta.setText("Confirmar venta ");
        btnConfVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfVentaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion de cliente"));
        jPanel1.setToolTipText("");

        textNombre.setText("Nombre:");

        textCedula.setText("N° Cedula:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textNombre)
                    .addComponent(textCedula))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCedula)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion de producto"));

        listProCotizados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listProCotizados);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        textPrecioTotal.setText("Total:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar)
                    .addComponent(textPrecioTotal))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textPrecioTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion de vendedor"));

        textVendedor.setText("Vendedor:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textVendedor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textVendedor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConfVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 291, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(274, 274, 274))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfVenta)
                    .addComponent(btnCancelar))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfVentaActionPerformed
        System.out.println("Botón Confirmar presionado");
        VentasController ventasCtrl = new VentasController();

        List<Producto> productosCotizados = obtenerProductosCotizados();

        if (productosCotizados.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "No hay productos para confirmar la venta.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Crear y configurar la nueva venta
            Venta venta = new Venta();
            venta.setNameCliente(primeraVenta.getNameCliente());
            venta.setNoCcCliente(primeraVenta.getNoCcCliente());
            venta.setVendedor(primeraVenta.getVendedor());

            // Asegúrate de establecer un producto para la venta
            if (!productosCotizados.isEmpty()) {
                venta.setProducto(productosCotizados.get(0)); // Establecer el primer producto como ejemplo
            }

            // Calcular el total de la venta
            double totalGeneral = 0;
            for (Venta v : ventasCotizadas) {
                totalGeneral += v.getTotal();
            }
            venta.setTotal(totalGeneral);
            venta.setFecha(java.time.LocalDate.now());

            // Guardar la venta
            ventasCtrl.guardarVenta(venta, productosCotizados, tbVentas);
            ((javax.swing.table.AbstractTableModel) tbVentas.getModel()).fireTableDataChanged();
            this.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Mostrar el mensaje de error en consola
            javax.swing.JOptionPane.showMessageDialog(this, "Error al confirmar la venta: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConfVentaActionPerformed

    private List<Producto> obtenerProductosCotizados() {
        List<Producto> productos = new ArrayList<>();
        for (Venta venta : ventasCotizadas) {
            productos.add(venta.getProducto()); // Suponiendo que 'getProducto()' devuelve un objeto de tipo Producto
        }
        return productos;
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int selectedIndex = listProCotizados.getSelectedIndex();

        if (selectedIndex != -1) {
            // Eliminar el producto seleccionado de la lista de ventas cotizadas
            ventasCotizadas.remove(selectedIndex);

            // Actualizar el modelo de la lista para reflejar la eliminación
            DefaultListModel<String> modeloLista = (DefaultListModel<String>) listProCotizados.getModel();
            modeloLista.remove(selectedIndex);

            // Actualizar el precio total después de eliminar el producto
            actualizarPrecioTotal();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto de la lista para eliminar.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void actualizarPrecioTotal() {
        double totalGeneral = 0;
        for (Venta venta : ventasCotizadas) {
            totalGeneral += venta.getTotal();
        }
        textPrecioTotal.setText("Precio Total: " + totalGeneral);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Crear un modelo de tabla ficticio para la JTable
                DefaultTableModel modeloTabla = new DefaultTableModel(
                        new Object[][]{}, // Datos vacíos
                        new String[]{"Código", "Producto", "Cantidad", "Precio unitario", "Total"} // Columnas
                );
                JTable tbVentas = new JTable(modeloTabla);

                // Crear una lista ficticia de Ventas
                List<Venta> ventas = new ArrayList<>();
                // Suponiendo que tienes un constructor de Venta y puedes poblarla con datos de prueba.

                // Llamar al constructor de FormConfiVenta con la venta y la tabla
                new FormConfiVenta(ventas, tbVentas).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfVenta;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JList<String> listProCotizados;
    private javax.swing.JLabel textCedula;
    private javax.swing.JLabel textNombre;
    private javax.swing.JLabel textPrecioTotal;
    private javax.swing.JLabel textVendedor;
    // End of variables declaration//GEN-END:variables
}
