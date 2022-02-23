package com.dam.flashcards.dto;

import java.io.Serializable;

public class NuevaContrasenaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nuevaContrasena;

	public NuevaContrasenaDTO() {
	}

	public NuevaContrasenaDTO(String nuevaContrasena) {
		this.nuevaContrasena = nuevaContrasena;
	}

	public String getNuevaContrasena() {
		return nuevaContrasena;
	}

	public void setNuevaContrasena(String nuevaContrasena) {
		this.nuevaContrasena = nuevaContrasena;
	}

}
