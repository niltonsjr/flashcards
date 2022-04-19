package com.dam.flashcards.services;

import com.dam.flashcards.dto.EmailDTO;

import org.springframework.stereotype.Service;
@Service
public interface EmailService {

    void sendEmail(EmailDTO dto);
}
