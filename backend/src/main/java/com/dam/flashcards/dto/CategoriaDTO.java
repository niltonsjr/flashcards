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
	private UsuarioDTO usuario;
	private Set<TarjetaDTO> tarjetas = new HashSet<>();

	public CategoriaDTO() {
	}

	public CategoriaDTO(Long id, String nombre, UsuarioDTO usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.usuario = usuario;
	}

	public CategoriaDTO(Categoria entity) {
		this.id = entity.getId();
		this.nombre = entity.getNombre();
		this.usuario = new UsuarioDTO(entity.getUsuario());
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

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Set<TarjetaDTO> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(Set<TarjetaDTO> tarjetas) {
		this.tarjetas = tarjetas;
	}

}
