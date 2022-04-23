package com.dam.flashcards.dto;

import java.io.Serializable;

import com.dam.flashcards.entities.Rol;
/**
 * Clase DTO con datos de Rol
 */
public class RolDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;

	public RolDTO() {
	}

	public RolDTO(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public RolDTO(Rol entity) {
		id = entity.getId();
		nombre = entity.getNombre();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
