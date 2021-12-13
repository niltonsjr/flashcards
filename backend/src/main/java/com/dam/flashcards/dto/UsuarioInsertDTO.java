package com.dam.flashcards.dto;

import javax.validation.constraints.NotBlank;

public class UsuarioInsertDTO extends UsuarioDTO {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo obligatorio.")
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
