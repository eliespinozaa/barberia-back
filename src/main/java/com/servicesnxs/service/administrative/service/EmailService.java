package com.servicesnxs.service.administrative.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.from}")
    private String from;

    @Value("${app.frontend.reset-url}")
    private String resetUrl;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarCorreoRecuperacion(String correoDestino, String token) {
        String link = resetUrl + "?token=" + token;

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom(from);
        mensaje.setTo(correoDestino);
        mensaje.setSubject("Recupera tu contraseña — Barber System");
        mensaje.setText(
            "Hola,\n\n" +
            "Recibimos una solicitud para restablecer tu contraseña.\n" +
            "Da clic en el siguiente enlace (válido por 30 minutos):\n\n" +
            link + "\n\n" +
            "Si tú no solicitaste esto, puedes ignorar este correo.\n\n" +
            "— Barber System"
        );

        mailSender.send(mensaje);
    }
}