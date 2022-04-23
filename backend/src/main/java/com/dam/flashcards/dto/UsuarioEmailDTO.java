package com.dam.flashcards.dto;

import java.io.Serializable;
/**
 * Clase DTO con datos de correo electrónico
 */
public class UsuarioEmailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    public UsuarioEmailDTO() {
    }

    public UsuarioEmailDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsuarioEmailDTO email(String email) {
        setEmail(email);
        return this;
    }

}
