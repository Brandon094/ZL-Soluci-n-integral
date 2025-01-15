package com.mycompany.zl_solucion_integral.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Seguridad {

    /**
     * Encripta una contraseña usando SHA-256.
     *
     * @param contraseña La contraseña en texto plano a encriptar.
     * @return La contraseña encriptada en formato hexadecimal.
     */
    public static String encriptarContraseña(String contraseña) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(contraseña.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }

    /**
     * Compara una contraseña en texto plano con una contraseña encriptada.
     *
     * @param contraseñaIngresada La contraseña en texto plano.
     * @param contraseñaEncriptada La contraseña encriptada almacenada.
     * @return true si coinciden, false en caso contrario.
     */
    public static boolean validarContraseña(String contraseñaIngresada, String contraseñaEncriptada) {
        String contraseñaIngresadaEncriptada = encriptarContraseña(contraseñaIngresada);
        return contraseñaIngresadaEncriptada.equals(contraseñaEncriptada);
    }
}
