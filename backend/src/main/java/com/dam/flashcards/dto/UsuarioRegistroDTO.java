package com.dam.flashcards.dto;

import javax.validation.constraints.NotBlank;

/**
 * Clase DTO con datos para insertar un nuevo usuario desde el formulario de registro
 */
public class UsuarioRegistroDTO extends UsuarioInsertDTO {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo obligatorio.")
	private String confirmaContrasena;

	public UsuarioRegistroDTO() {
		super();
	}

	public String getConfirmaContrasena() {
		return confirmaContrasena;
	}

	public void setConfirmaContrasena(String confirmaContrasena) {
		this.confirmaContrasena = confirmaContrasena;
	}

}
