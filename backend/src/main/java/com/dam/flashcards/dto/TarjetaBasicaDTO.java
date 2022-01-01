package com.dam.flashcards.dto;

import java.io.Serializable;

import com.dam.flashcards.entities.Tarjeta;

public class TarjetaBasicaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String frontal;
	private String trasera;
	private Long categoriaId;
	private Long usuarioId;

	public TarjetaBasicaDTO() {
	}

	public TarjetaBasicaDTO(Long id, String frontal, String trasera, Long categoriaId, Long usuarioId) {
		this.id = id;
		this.frontal = frontal;
		this.trasera = trasera;
		this.categoriaId = categoriaId;
		this.usuarioId = usuarioId;
	}

	public TarjetaBasicaDTO(Tarjeta entity) {
		id = entity.getId();
		frontal = entity.getFrontal();
		trasera = entity.getTrasera();
		categoriaId = entity.getCategoria().getId();
		usuarioId = entity.getUsuario().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrontal() {
		return frontal;
	}

	public void setFrontal(String frontal) {
		this.frontal = frontal;
	}

	public String getTrasera() {
		return trasera;
	}

	public void setTrasera(String trasera) {
		this.trasera = trasera;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

}
