package com.dam.flashcards.services;

import java.io.IOException;

import com.dam.flashcards.dto.EmailDTO;
import com.dam.flashcards.services.exceptions.EmailException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SendGridEmailService implements EmailService {

    private static Logger LOG = org.slf4j.LoggerFactory.getLogger(SendGridEmailService.class);

    @Autowired
    private SendGrid sendGrid;

    /**
     * Implementación del método para enviar un Email
     * 
     * @param dto los datos del email a ser enviado
     */
    public void sendEmail(EmailDTO dto) {
        Email from = new Email(dto.getFromEmail(), dto.getFromName());
        Email to = new Email(dto.getTo());
        Content content = new Content(dto.getContentType(), dto.getBody());
        Mail mail = new Mail(from, dto.getSubject(), to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            LOG.info("Enviando email a: " + dto.getTo());
            Response response = sendGrid.api(request);
            if (response.getStatusCode() >= 400 && response.getStatusCode() <= 500) {
                LOG.error("Error al enviar email:" + response.getBody());
                throw new EmailException(response.getBody());
            }
            LOG.info("¡Email enviado!: " + response.getStatusCode());

        } catch (IOException e) {
            throw new EmailException(e.getMessage());
        }
    }

}
