package com.mycompany.zl_solucion_integral.models;

import java.time.LocalDate;

public class Venta {

    // Atributos
    private int id;
    private String codigo;
    private int cantidad;
    private LocalDate fecha;  // Cambiado a LocalDate
    private String nameCliente;
    private String noCcCliente;
    private Producto producto;
    private double descuento;
    private double total;
    private String vendedor;  // Agregado para almacenar el vendedor

    // Constructor vacío
    public Venta() {
    }

    // Constructor completo
    public Venta(String codigo, Producto producto, int cantidad, String nameCliente, String noCcCliente, LocalDate fecha, String vendedor, double total, double descuento) {
        this.codigo = codigo;
        this.producto = producto;
        this.cantidad = cantidad;
        this.nameCliente = nameCliente;
        this.noCcCliente = noCcCliente;
        this.fecha = fecha;
        this.vendedor = vendedor;  // Inicialización del vendedor
        this.total = total;
        this.descuento = descuento;
    }

    // Getters y Setters
    public int getId() {
        return id;
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

    public String getNameCliente() {
        return nameCliente;
    }

    public void setNameCliente(String nameCliente) {
        this.nameCliente = nameCliente;
    }

    public String getNoCcCliente() {
        return noCcCliente;
    }

    public void setNoCcCliente(String noCcCliente) {
        this.noCcCliente = noCcCliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // Nuevo método para obtener el precio del producto
    public double getPrecio() {
        return this.producto.getPrecio();  // Suponiendo que Producto tiene un método getPrecio()
    }

    // Nuevo método para obtener el vendedor
    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "Venta{"
                + "codigo='" + codigo + '\''
                + ", cantidad=" + cantidad
                + ", fecha=" + fecha
                + ", cliente='" + nameCliente + '\''
                + ", noCcCliente='" + noCcCliente + '\''
                + ", producto=" + producto.getProducto()
                + ", vendedor='" + vendedor + '\''
                + ", total=" + total
                + ", descuento=" + descuento
                + '}';
    }

}
