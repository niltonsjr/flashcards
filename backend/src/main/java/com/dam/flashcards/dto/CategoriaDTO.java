package com.dam.flashcards.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Tarjeta;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private Long usuarioId;
	private Set<TarjetaDTO> tarjetas = new HashSet<>();

	public CategoriaDTO() {
	}

	public CategoriaDTO(Long id, String nombre, Long usuarioId) {
		this.id = id;
		this.nombre = nombre;
		this.usuarioId = usuarioId;
	}

	public CategoriaDTO(Categoria entity) {
		id = entity.getId();
		nombre = entity.getNombre();
		usuarioId = entity.getUsuario().getId();
	}

	public CategoriaDTO(Categoria entity, Set<Tarjeta> tarjetas) {
		this(entity);
		tarjetas.forEach(tar -> this.tarjetas.add(new TarjetaDTO(tar)));
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

	public Set<TarjetaDTO> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(Set<TarjetaDTO> tarjetas) {
		this.tarjetas = tarjetas;
	}

}
