package com.dam.flashcards.config;

import com.dam.flashcards.services.EmailService;
import com.dam.flashcards.services.SendGridEmailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    
    /** 
     * Bean de EmailServide para perfil prod
     * @return EmailService
     */
    @Bean
    public EmailService emailService() {
        return new SendGridEmailService();
    }
}
