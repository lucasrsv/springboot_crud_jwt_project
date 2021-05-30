package com.lucas.projeto.projeto.services;

import com.lucas.projeto.projeto.domain.Pedido;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);
}
