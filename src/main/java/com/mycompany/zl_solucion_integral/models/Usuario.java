package com.mycompany.zl_solucion_integral.models;

/**
 * Clase que representa a un usuario en el sistema.
 *
 * @author Dazac
 */
public class Usuario {

    // Atributos
    private int id;
    private String nombre;
    private String telefono;
    private String email;
    private String contraseña;
    private String rol; // Se puede manejar como String o int según el diseño de tu aplicación

    // Constructor vacío (útil para instanciar sin datos iniciales)
    public Usuario() {
    }

    // Constructor con parámetros (para inicializar con datos)
    public Usuario(int id, String nombre, String telefono, String email, String contraseña, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    // Getters y Setters para cada atributo
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Método para representar al usuario como cadena de texto 
    //(opcional, útil para depuración)
    @Override
    public String toString() {
        return "Usuario{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", telefono='" + telefono + '\''
                + ", email='" + email + '\''
                + ", contraseña='" + contraseña + '\''
                + ", rol='" + rol + '\''
                + '}';
    }
}
