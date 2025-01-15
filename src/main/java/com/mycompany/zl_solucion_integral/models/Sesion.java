package com.mycompany.zl_solucion_integral.models;

public class Sesion {

    // Variable estática para almacenar el usuario logueado
    public static String usuarioLogueado;

    // Método para obtener el nombre del usuario logueado
    public static String getUsuarioLogueado() {
        return usuarioLogueado;
    }

    // Método para establecer el usuario logueado
    public static void setUsuarioLogueado(String usuario) {
        usuarioLogueado = usuario;
    }
}
