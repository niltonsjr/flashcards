package com.dam.flashcards.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.dam.flashcards.services.validation.UsuarioInsertValid;

/**
 * Clase DTO con datos para insertar un nuevo usuario
 */
@UsuarioInsertValid
public class UsuarioInsertDTO extends UsuarioDTO {

	private static final long serialVersionUID = 1L;

	@Size(min = 6, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres.")
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
