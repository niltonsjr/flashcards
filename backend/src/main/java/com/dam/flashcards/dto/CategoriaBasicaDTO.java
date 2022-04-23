package com.dam.flashcards.dto;

import java.io.Serializable;

import com.dam.flashcards.entities.Categoria;
/**
 * Clase DTO de categoría con datos básicos
 */
public class CategoriaBasicaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private Long usuarioId;

	public CategoriaBasicaDTO() {
	}

	public CategoriaBasicaDTO(Long id, String nombre, Long usuarioId) {
		this.id = id;
		this.nombre = nombre;
		this.usuarioId = usuarioId;
	}

	public CategoriaBasicaDTO(Categoria entity) {
		id = entity.getId();
		nombre = entity.getNombre();
		usuarioId = entity.getUsuario().getId();
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

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

}
