package com.dam.flashcards.config;

import com.dam.flashcards.services.EmailService;
import com.dam.flashcards.services.MockEmailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    
    /** 
     * Bean de emailService para el perfil Test
     * @return EmailService
     */
    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
