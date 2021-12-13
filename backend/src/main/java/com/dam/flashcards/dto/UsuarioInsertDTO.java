package com.dam.flashcards.dto;

public class UsuarioInsertDTO extends UsuarioDTO {

	private static final long serialVersionUID = 1L;

	private String contrasena;

	public UsuarioInsertDTO() {
		super();
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
