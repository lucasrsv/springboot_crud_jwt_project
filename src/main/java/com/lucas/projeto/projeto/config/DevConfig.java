package com.lucas.projeto.projeto.config;

import com.lucas.projeto.projeto.services.EmailService;
import com.lucas.projeto.projeto.services.SmtpEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
