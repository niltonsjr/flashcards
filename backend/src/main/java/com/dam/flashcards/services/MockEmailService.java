package com.dam.flashcards.services;

import com.dam.flashcards.dto.EmailDTO;

import org.slf4j.Logger;

public class MockEmailService implements EmailService {

    private static Logger LOG = org.slf4j.LoggerFactory.getLogger(MockEmailService.class);

    public void sendEmail(EmailDTO dto) {
        LOG.info("Enviando email a: " + dto.getTo());
        LOG.info("¡Email enviado!");
    }

}
