package com.dam.flashcards.dto;

import java.io.Serializable;

import com.dam.flashcards.entities.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public CategoriaDTO(Categoria entity) {
		this.id = entity.getId();
		this.nombre = entity.getNombre();
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
