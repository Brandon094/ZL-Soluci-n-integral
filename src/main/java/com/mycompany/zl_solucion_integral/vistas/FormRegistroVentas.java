package com.mycompany.zl_solucion_integral.vistas;

import com.mycompany.zl_solucion_integral.config.UtilVentanas;
import com.mycompany.zl_solucion_integral.controllers.ProductoController;
import com.mycompany.zl_solucion_integral.controllers.VentasController;
import com.mycompany.zl_solucion_integral.models.Producto;
import com.mycompany.zl_solucion_integral.models.Sesion;
import com.mycompany.zl_solucion_integral.models.Venta;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * La clase {registroVentas} representa la interfaz gráfica para el registro,
 * modificación y eliminación de ventas en la aplicación {zl_solucion_integral}.
 * Esta ventana permite a los usuarios ingresar detalles de ventas, visualizar
 * ventas existentes en una tabla y gestionar las ventas mediante las opciones
 * proporcionadas.
 *
 * <p>
 * Dependiendo del rol del usuario (administrador o no), se muestra u oculta el
 * botón para regresar al menú principal.
 * </p>
 *
 * @autor Dazac
 */
public class FormRegistroVentas extends javax.swing.JFrame {

    VentasController ventasCtrl = new VentasController();
    ProductoController productoCtrl = new ProductoController();
    // Define el ArrayList de ventas a nivel de la clase
    private List<Venta> ventasCotizadas = new ArrayList<>();
    private Sesion sesion;

    /**
     * Constructor de la clase {registroVentas}.
     *
     * <p>
     * Inicializa los componentes gráficos y configura la visibilidad del botón
     * {btnMenuPrincipal} basado en el rol del usuario.
     * </p>
     *
     * @param esAdmin Un booleano que indica si el usuario es administrador.
     */
    public FormRegistroVentas(boolean esAdmin) {
        initComponents();
        setTitle("Registro de ventas");
        UtilVentanas.aplicarPantallaCompleta(this);
        btnMenuPrincipal.setVisible(esAdmin); // Ocultar el boton inicalmente
        ventasCtrl.MostrarVentas(tbVentas); // Mostrar las ventas 
        agregarListenerTablaVentas(tbVentas); // Agregar listener a la table ventas 
    }

    // Método para obtener los datos del formulario 
    private Venta obtenerDatosFormulario() {
        try {
            // Obtener los datos del formulario
            String codigo = txtCodigo.getText().trim();
            String productoName = txtProducto.getText().trim();
            String cantidadStr = txtCantidad.getText().trim();
            String cliente = txtCliente.getText().trim();
            String NoCcCliente = txtNoCc.getText().trim();
            String descuentoStr = txtDescuento.getText().trim();
            String vendedor = sesion.getUsuarioLogueado();

            Producto productoEncontrado = null;

            // Buscar el producto por código o nombre
            if (!codigo.isEmpty()) {
                productoEncontrado = productoCtrl.buscarProductoPorCodigo(codigo);
            }
            if (productoEncontrado == null && !productoName.isEmpty()) {
                productoEncontrado = productoCtrl.buscarProductoPorNombre(productoName);
            }

            // Validar que se haya encontrado el producto
            if (productoEncontrado == null) {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return null;
            }

            // Parsear cantidad, descuento
            int cantidad = Integer.parseInt(cantidadStr);
            double descuento = descuentoStr.isEmpty() ? 0.0 : Double.parseDouble(descuentoStr);

            // Calcular total
            double precio = productoEncontrado.getPrecio();
            double total = precio * cantidad;
            if (descuento > 0) {
                total -= total * (descuento / 100);
            }

            LocalDate fecha = LocalDate.now();
            return new Venta(codigo, productoEncontrado, cantidad, cliente, NoCcCliente, fecha, vendedor, total, descuento);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos. Verifique los campos numéricos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Método para validar campos antes de procesar la venta
    public boolean validarCampos() {
        if (ventasCotizadas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe realizar una cotización antes de registrar la venta.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        btnMenuPrincipal = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnGuardarVenta = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        textCliente = new javax.swing.JLabel();
        textPrecioUnitarioInfo = new javax.swing.JLabel();
        textProductoInfo = new javax.swing.JLabel();
        textCantidadInfo = new javax.swing.JLabel();
        textValorTotal = new javax.swing.JLabel();
        btnCotizar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        txtNoCc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        textCc = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        textDescuentoInfo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVentas = new javax.swing.JTable();
        textVendedor = new javax.swing.JLabel();
        textDescuentoAplicado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Console", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registro de Ventas");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        btnCerrar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnCerrar.setText("Cerrar secion");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnMenuPrincipal.setText("Menu Principal");
        btnMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPrincipalActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingresa los datos para registrar la venta"));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ingrese la cantidad:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Ingrese el codigo: ");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        btnGuardarVenta.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnGuardarVenta.setText("Venta");
        btnGuardarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVentaActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nombre cliente:");

        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });

        jLabel5.setText("INFORMACION DE VENTA");

        textCliente.setText("Cliente:");

        textPrecioUnitarioInfo.setText("Precio:");

        textProductoInfo.setText("Producto:");

        textCantidadInfo.setText("Cantidad:");

        textValorTotal.setText("Valor total:");

        btnCotizar.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnCotizar.setText("Cotizar");
        btnCotizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCotizarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ingrese el producto:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Numero de cedula:");

        jLabel8.setText("INFORMACION DEL CLIENTE");

        textCc.setText("# C.C Cliente:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Ingrese el descuento: ");

        textDescuentoInfo.setText("Descuento:");

        tbVentas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "producto", "cantidad", "codigo", "precio", "cliente", "CC Cliente", "Fecha", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbVentas);

        textVendedor.setText("Vendedor:");

        textDescuentoAplicado.setText("Descuento aplicado:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator3)
                    .addComponent(btnGuardarVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCliente)
                                    .addComponent(txtNoCc)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCantidad)
                                    .addComponent(txtDescuento)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigo)
                                    .addComponent(txtProducto)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textDescuentoInfo)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textCliente))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textCantidadInfo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textProductoInfo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textPrecioUnitarioInfo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textCc))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textDescuentoAplicado))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textVendedor)
                                    .addComponent(textValorTotal)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jLabel5)))
                        .addGap(0, 59, Short.MAX_VALUE))
                    .addComponent(btnCotizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNoCc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCotizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(3, 3, 3)
                        .addComponent(textCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textCc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textProductoInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textPrecioUnitarioInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textCantidadInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textDescuentoInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textDescuentoAplicado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textVendedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textValorTotal)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar)
                        .addGap(21, 21, 21))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(406, 406, 406))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMenuPrincipal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMenuPrincipal)
                    .addComponent(btnCerrar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // ocultar la interfaz actual
        this.setVisible(false);

        // Instanciar la clase de logIn
        FormLogIn log_In = new FormLogIn();
        // Mostrar la interfaz de logIn
        log_In.setVisible(true);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPrincipalActionPerformed
        // Ocultar la interfaz de registro ventas
        this.setVisible(false);
        // abrir la nueva interfaz
        FormMainWindow dbMag = new FormMainWindow();
        dbMag.setVisible(true);
    }//GEN-LAST:event_btnMenuPrincipalActionPerformed

    private void mostrarInformacionCotizacion(List<Venta> ventas) {
        // Limpiar los campos de texto antes de agregar nueva información
        textCliente.setText("Cliente:");
        textCc.setText("# C.C Cliente:");
        textProductoInfo.setText("Producto:");
        textPrecioUnitarioInfo.setText("Precio:");
        textCantidadInfo.setText("Cantidad:");
        textDescuentoInfo.setText("Descuento:");
        textDescuentoAplicado.setText("Descuento aplicado:");
        textVendedor.setText("Vendedor:");
        textValorTotal.setText("Valor total:");

        if (!ventas.isEmpty()) {
            // Obtener la información del primer cliente (asumiendo que todas las ventas son del mismo cliente)
            Venta primeraVenta = ventas.get(0);
            textCliente.setText("Cliente: " + primeraVenta.getNameCliente());
            textCc.setText("C.C: " + primeraVenta.getNoCcCliente());

            // Variables para concatenar la información de todos los productos
            StringBuilder productos = new StringBuilder();
            StringBuilder precios = new StringBuilder();
            StringBuilder cantidades = new StringBuilder();
            StringBuilder descuentos = new StringBuilder();
            StringBuilder descuentosAplicados = new StringBuilder();

            // Inicializar el total acumulado
            double totalFinal = 0;

            // Recorrer todas las ventas y construir las cadenas separadas por comas
            for (Venta venta : ventas) {
                Producto producto = venta.getProducto();

                // Agregar información separada por comas
                if (productos.length() > 0) {
                    productos.append(", "); // Si no es el primero, agregar coma
                }
                productos.append(producto.getProducto());

                if (precios.length() > 0) {
                    precios.append(", ");
                }
                precios.append(producto.getPrecio());

                if (cantidades.length() > 0) {
                    cantidades.append(", ");
                }
                cantidades.append(venta.getCantidad());

                if (descuentos.length() > 0) {
                    descuentos.append(", ");
                }
                descuentos.append(venta.getDescuento() > 0 ? venta.getDescuento() + "%" : "No aplica");

                if (descuentosAplicados.length() > 0) {
                    descuentosAplicados.append(", ");
                }
                double valorSinDescuento = producto.getPrecio() * venta.getCantidad();
                double descuentoFinal = (venta.getDescuento() > 0) ? valorSinDescuento - venta.getTotal() : 0;
                descuentosAplicados.append(descuentoFinal);

                // Sumar el total de la venta actual al total final
                totalFinal += venta.getTotal();
            }

            // Asignar los valores concatenados a los campos correspondientes
            textProductoInfo.setText("Producto(s): " + productos.toString());
            textPrecioUnitarioInfo.setText("Precio(s): " + precios.toString());
            textCantidadInfo.setText("Cantidad(es): " + cantidades.toString());
            textDescuentoInfo.setText("Descuento(s): " + descuentos.toString());
            textDescuentoAplicado.setText("Descuento aplicado(s): " + descuentosAplicados.toString());

            // Mostrar el nombre del vendedor y el total final acumulado
            textVendedor.setText("Vendedor: " + sesion.getUsuarioLogueado());
            textValorTotal.setText("Valor total: " + totalFinal);
        } else {
            JOptionPane.showMessageDialog(this, "No hay productos cotizados.", "Información de cotización", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnCotizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCotizarActionPerformed
        Venta venta = obtenerDatosFormulario();
        if (venta != null) {
            // Primero agrega la venta a la lista de cotizadas
            ventasCotizadas.add(venta);

            // Luego muestra la información de la cotización
            mostrarInformacionCotizacion(ventasCotizadas);

            // Muestra mensaje de éxito
            JOptionPane.showMessageDialog(this, "Cotización realizada con éxito.",
                    "Cotización", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos requeridos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCotizarActionPerformed

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteActionPerformed
    // Metodo para modifficar la venta selecionada
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Venta venta = obtenerDatosFormulario();
        if (venta == null) {
            JOptionPane.showMessageDialog(this, "Selecione una venta para modificar");
            return;
        }

        try {
            int idVenta = ventasCtrl.obtenerIdVentaSeleccionado(tbVentas);
            if (idVenta != -1) {
                venta.setId(idVenta);
                ventasCtrl.modificarVenta(venta, idVenta, tbVentas);
                ventasCtrl.MostrarVentas(tbVentas);
                List<Venta> ventas = new ArrayList<>();
                ventas.add(venta);
                mostrarInformacionCotizacion(ventas);
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una venta para modificar.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la venta: " + e.getMessage());
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void agregarListenerTablaVentas(JTable tbVentas) {
        tbVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int filaSeleccionada = tbVentas.getSelectedRow();
                if (filaSeleccionada != -1) {
                    txtProducto.setText(tbVentas.getValueAt(filaSeleccionada, 1).toString());
                    txtCantidad.setText(tbVentas.getValueAt(filaSeleccionada, 2).toString());
                    txtCodigo.setText(tbVentas.getValueAt(filaSeleccionada, 3).toString());
                    txtCliente.setText(tbVentas.getValueAt(filaSeleccionada, 5).toString());    // Columna "cliente"
                    txtNoCc.setText(tbVentas.getValueAt(filaSeleccionada, 6).toString());       // Columna "No de Cédula"                    
                }
            }
        });
    }

    // Metodo para Guardar la venta en la base de datos
    private void btnGuardarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVentaActionPerformed
        if (ventasCotizadas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe realizar una cotización antes de registrar la venta.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            FormConfiVenta formConfiVenta = new FormConfiVenta(ventasCotizadas, tbVentas);
            formConfiVenta.setVisible(true);
        }
    }//GEN-LAST:event_btnGuardarVentaActionPerformed

    // Metodo  para limpiar el formulario
    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtProducto.setText("");
        txtCantidad.setText("");
        txtCliente.setText("");
        txtNoCc.setText("");
        txtDescuento.setText("");
        textCliente.setText("Cliente:");
        textCc.setText("# C.C Cliente:");
        textProductoInfo.setText("Producto:");
        textPrecioUnitarioInfo.setText("Precio:");
        textCantidadInfo.setText("Cantidad:");
        textDescuentoInfo.setText("Descuento:");
        textDescuentoAplicado.setText("Descuento aplicado:");
        textVendedor.setText("Vendedor:");
        textValorTotal.setText("Valor total:");
    }
    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormRegistroVentas(true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCotizar;
    private javax.swing.JButton btnGuardarVenta;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable tbVentas;
    private javax.swing.JLabel textCantidadInfo;
    private javax.swing.JLabel textCc;
    private javax.swing.JLabel textCliente;
    private javax.swing.JLabel textDescuentoAplicado;
    private javax.swing.JLabel textDescuentoInfo;
    private javax.swing.JLabel textPrecioUnitarioInfo;
    private javax.swing.JLabel textProductoInfo;
    private javax.swing.JLabel textValorTotal;
    private javax.swing.JLabel textVendedor;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtNoCc;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
}
