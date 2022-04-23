package com.dam.flashcards.dto;

import java.io.Serializable;

/**
 * Clase DTO con datos de resetToken
 */
public class ResetContrasenaTokenDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String token;

    public ResetContrasenaTokenDTO() {
    }

    public ResetContrasenaTokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ResetContrasenaTokenDTO token(String token) {
        setToken(token);
        return this;
    }

}
