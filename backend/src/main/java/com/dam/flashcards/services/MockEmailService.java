package com.dam.flashcards.services;

import com.dam.flashcards.dto.EmailDTO;

import org.slf4j.Logger;

public class MockEmailService implements EmailService {

    private static Logger LOG = org.slf4j.LoggerFactory.getLogger(MockEmailService.class);

    /**
     * Implementación del método para enviar un Email de forma simulada para su uso
     * en perfil "test"
     * 
     * @param dto los datos del email a ser enviado
     */
    public void sendEmail(EmailDTO dto) {
        LOG.info("Enviando email a: " + dto.getTo());
        LOG.info("Cuerpo del mensaje: " + dto.getBody());
        LOG.info("¡Email enviado!");
    }

}
