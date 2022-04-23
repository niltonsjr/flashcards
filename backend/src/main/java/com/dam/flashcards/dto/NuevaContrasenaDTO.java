package com.dam.flashcards.dto;

import java.io.Serializable;

/**
 * Clase DTO con datos de nueva contraseña y token para uso en método para
 * reasignar contraseña desde olvidó su contraseña
 */
public class NuevaContrasenaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nuevaContrasena;
	private String token;

	public NuevaContrasenaDTO() {
	}

	public NuevaContrasenaDTO(String nuevaContrasena, String token) {
		this.nuevaContrasena = nuevaContrasena;
		this.token = token;
	}

	public String getNuevaContrasena() {
		return this.nuevaContrasena;
	}

	public void setNuevaContrasena(String nuevaContrasena) {
		this.nuevaContrasena = nuevaContrasena;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public NuevaContrasenaDTO nuevaContrasena(String nuevaContrasena) {
		setNuevaContrasena(nuevaContrasena);
		return this;
	}

	public NuevaContrasenaDTO token(String token) {
		setToken(token);
		return this;
	}
}
