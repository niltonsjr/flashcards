package com.dam.flashcards.services;

import com.dam.flashcards.dto.EmailDTO;

public interface EmailService {

    void sendEmail(EmailDTO dto);
}
