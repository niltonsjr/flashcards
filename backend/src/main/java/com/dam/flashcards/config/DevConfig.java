package com.dam.flashcards.config;

import com.dam.flashcards.services.EmailService;
import com.dam.flashcards.services.SendGridEmailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    /**
     * Bean de EmailServide para perfil dev
     * 
     * @return EmailService
     */
    @Bean
    public EmailService emailService() {
        return new SendGridEmailService();
    }
}
