package com.dam.flashcards.services.exceptions;

public class EmailException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailException(String msg) {
        super(msg);
    }

}
