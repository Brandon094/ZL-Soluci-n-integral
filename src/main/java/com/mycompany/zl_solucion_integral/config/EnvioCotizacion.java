package com.mycompany.zl_solucion_integral.config;

import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.util.Properties;
import java.net.URI;
import java.net.URLEncoder;
import java.awt.Desktop;

/**
 * Clase encargada de gestionar el envío de cotizaciones por WhatsApp y correo electrónico.
 */
public class EnvioCotizacion {

    /**
     * Enviar una cotización a través de WhatsApp Web.
     *
     * @param telefono    Número de teléfono del destinatario (sin espacios ni guiones).
     * @param rutaArchivo Ruta del archivo generado que contiene la cotización.
     */
    public void enviarPorWhatsApp(String telefono, String rutaArchivo) {
        try {
            // Mensaje predeterminado para WhatsApp.
            String mensaje = "¡Hola! \n\nGracias por confiar en ZL Solución Integral. "
                    + "Te enviamos la cotización que solicitaste. Por favor, revisa el archivo adjunto para conocer todos los detalles. "
                    + "Si tienes alguna pregunta o necesitas ajustar algo, no dudes en contactarnos."
                    + "\n\n¡Estamos aquí para ayudarte!";
            // Genera la URL para abrir WhatsApp Web con el mensaje predefinido.
            String urlWhatsApp = "https://wa.me/" + "+57" + telefono + "?text=" + URLEncoder.encode(mensaje, "UTF-8");

            // Abre el enlace en el navegador predeterminado.
            Desktop.getDesktop().browse(new URI(urlWhatsApp));

            // Notifica al usuario que debe adjuntar manualmente el archivo en WhatsApp Web.
            JOptionPane.showMessageDialog(null, "Por favor, adjunta el archivo manualmente en WhatsApp Web.\nArchivo generado: " + rutaArchivo,
                    "WhatsApp", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            // Manejo de errores al abrir WhatsApp Web.
            JOptionPane.showMessageDialog(null, "Error al abrir WhatsApp Web: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Enviar una cotización por correo electrónico utilizando el servicio SMTP de Gmail.
     *
     * @param correo      Dirección de correo electrónico del destinatario.
     * @param rutaArchivo Ruta del archivo generado que contiene la cotización.
     */
    public void enviarPorCorreo(String correo, String rutaArchivo) {
        try {
            // Configuración de las propiedades del servidor SMTP.
            Properties propiedades = new Properties();
            propiedades.put("mail.smtp.host", "smtp.gmail.com");  // Servidor SMTP de Gmail.
            propiedades.put("mail.smtp.port", "587");  // Puerto para conexión segura (TLS).
            propiedades.put("mail.smtp.auth", "true");  // Habilita la autenticación.
            propiedades.put("mail.smtp.starttls.enable", "true");  // Habilita STARTTLS (cifrado).

            // Autenticación con las credenciales del remitente.
            Session session = Session.getInstance(propiedades, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("zlsolucionintegral@gmail.com", "dcnj xudk hocn szty"); // Cambia por tus credenciales reales.
                }
            });

            // Crear el mensaje de correo.
            MimeMessage mensajeCorreo = new MimeMessage(session);
            mensajeCorreo.setFrom(new InternetAddress("zlsolucionintegral@gmail.com")); // Dirección del remitente.
            mensajeCorreo.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo)); // Destinatario.
            mensajeCorreo.setSubject("Cotización Generada"); // Asunto del correo.

            // Cuerpo del mensaje en texto.
            MimeBodyPart texto = new MimeBodyPart();
            texto.setText("¡Hola! \n\n"
                    + "Adjuntamos la cotización que solicitaste. En ella encontrarás todos los detalles de los productos y servicios que necesitas. "
                    + "Si tienes alguna pregunta, deseas realizar algún ajuste, o necesitas más información, "
                    + "no dudes en ponerte en contacto con nosotros."
                    + "\n\n¡Gracias por elegir ZL Solución Integral!"
                    + "\n\nSaludos cordiales,"
                    + "\nEl equipo de ZL Solución Integral.");

            // Archivo adjunto.
            MimeBodyPart adjunto = new MimeBodyPart();
            adjunto.attachFile(rutaArchivo);

            // Crear el contenido del mensaje con texto y archivo adjunto.
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(texto);
            multipart.addBodyPart(adjunto);

            // Asignar el contenido al mensaje de correo.
            mensajeCorreo.setContent(multipart);

            // Enviar el correo.
            Transport.send(mensajeCorreo);

            // Notificar al usuario que el correo fue enviado exitosamente.
            JOptionPane.showMessageDialog(null, "Cotización enviada por correo con éxito.\nArchivo adjunto: " + rutaArchivo,
                    "Correo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            // Manejo de errores durante el envío del correo.
            JOptionPane.showMessageDialog(null, "Error al enviar el correo: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra opciones al usuario para enviar la cotización por WhatsApp o correo electrónico.
     *
     * @param telefono    Número de teléfono para WhatsApp.
     * @param correo      Dirección de correo electrónico.
     * @param rutaArchivo Ruta del archivo generado que contiene la cotización.
     */
    public void mostrarOpcionesEnvio(String telefono, String correo, String rutaArchivo) {
        // Notificar al usuario que la cotización fue generada exitosamente.
        JOptionPane.showMessageDialog(null, "Cotización generada exitosamente en " + rutaArchivo,
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // Mostrar un cuadro de diálogo con opciones de envío.
        int opcion = JOptionPane.showOptionDialog(null,
                "Seleccione cómo desea enviar la cotización:",
                "Enviar Cotización",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"WhatsApp", "Correo", "Cancelar"},
                "WhatsApp");

        // Ejecutar la acción seleccionada.
        if (opcion == 0) {
            enviarPorWhatsApp(telefono, rutaArchivo); // Enviar por WhatsApp.
        } else if (opcion == 1) {
            enviarPorCorreo(correo, rutaArchivo); // Enviar por correo.
        } else {
            // Notificar que la operación fue cancelada.
            JOptionPane.showMessageDialog(null, "Operación cancelada", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
