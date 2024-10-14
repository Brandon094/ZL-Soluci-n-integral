package com.mycompany.zl_solucion_integral.models;

public class Producto {

    // Atributos 
    private int id;
    private String producto;
    private double precio;
    private int cantidad;
    private String codigo;
    private double total;

    // Constructor vacío (útil para instanciar sin datos iniciales)
    public Producto() {
    }

    // Constructor con parámetros (para inicializar con datos, incluyendo el total)
    public Producto(int id, String producto, double precio, int cantidad, String codigo, double total) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.total = total;
    }

    // Constructor sin el parametro id 
    public Producto(String producto, double precio, int cantidad, String codigo, double total) {
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.total = total;
    }

    // Constructor sin el parametro de total 
    public Producto(String producto, Double precio, int cantidad, String codigo) {
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.codigo = codigo;
    }

    // Getters y Setters para cada atributo
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Método para representar al usuario como cadena de texto (opcional, útil para depuración)
    @Override
    public String toString() {
        return "ID: " + id + "\n"
                + " Nombre: " + producto + "\n"
                + " Precio: " + precio + "\n"
                + " Cantidad: " + cantidad + "\n"
                + " Código: " + codigo;
    }
}
