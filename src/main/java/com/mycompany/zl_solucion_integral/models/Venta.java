package com.mycompany.zl_solucion_integral.models;

import java.time.LocalDate;

public class Venta {

    // Atributos
    private int id;
    private String codigo;
    private int cantidad; // Cantidad solicitada por el cliente
    private LocalDate fecha;
    private Producto producto;
    private double descuento;
    private double total;
    private Usuario cliente;
    private String vendedor; // Vendedor responsable de la venta
    private int cantidadDisponible; // Cantidad restante en inventario después de la venta
    private String metodoPago;

    // Constructor vacío
    public Venta() {
    }

    // Constructor básico
    public Venta(Producto producto, Usuario cliente, String vendedor) {
        this.producto = producto;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    // Constructor completo
    public Venta(String codigo, Producto producto, int cantidad, LocalDate fecha, String vendedor, Usuario cliente, double total, double descuento, int cantidadDisponible, String metodoPago) {
        this.codigo = codigo;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.total = total;
        this.descuento = descuento;
        this.cantidadDisponible = cantidadDisponible;
        this.metodoPago = metodoPago;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }      
    // Representación en texto
    @Override
    public String toString() {
        return "Venta{"
                + "codigo='" + codigo + '\''
                + ", cantidadSolicitada=" + cantidad
                + ", cantidadDisponible=" + cantidadDisponible
                + ", fecha=" + fecha
                + ", producto=" + (producto != null ? producto.getProducto() : "N/A")
                + ", vendedor='" + vendedor + '\''
                + ", total=" + total
                + ", descuento=" + descuento
                + ", cliente=" + (cliente != null ? cliente.getNombre() : "N/A")
                + '}';
    }
}
