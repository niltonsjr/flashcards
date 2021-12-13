package com.dam.flashcards.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;

import com.dam.flashcards.entities.Tarjeta;

public class TarjetaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "Campo obligatorio.")
	private String frontal;
	@NotBlank(message = "Campo obligatorio.")
	private String trasera;
	private Boolean conocida;
	private Instant fechaUltimaRespuesta;
	private Integer totalConocidas;
	private Integer totalNoConocidas;
	private CategoriaDTO categoria;
	private UsuarioDTO usuario;

	public TarjetaDTO() {
	}

	public TarjetaDTO(Long id, String frontal, String trasera, Boolean conocida, Instant fechaUltimaRespuesta,
			Integer totalConocidas, Integer totalNoConocidas, CategoriaDTO categoria, UsuarioDTO usuario) {
		this.id = id;
		this.frontal = frontal;
		this.trasera = trasera;
		this.conocida = conocida;
		this.fechaUltimaRespuesta = fechaUltimaRespuesta;
		this.totalConocidas = totalConocidas;
		this.totalNoConocidas = totalNoConocidas;
		this.categoria = categoria;
		this.usuario = usuario;
	}

	public TarjetaDTO(Tarjeta entity) {
		this.id = entity.getId();
		this.frontal = entity.getFrontal();
		this.trasera = entity.getTrasera();
		this.conocida = entity.getConocida();
		this.fechaUltimaRespuesta = entity.getFechaUltimaRespuesta();
		this.totalConocidas = entity.getTotalConocidas();
		this.totalNoConocidas = entity.getTotalNoConocidas();
		this.categoria = new CategoriaDTO(entity.getCategoria());
		this.usuario = new UsuarioDTO(entity.getUsuario());
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

	public Boolean getConocida() {
		return conocida;
	}

	public void setConocida(Boolean conocida) {
		this.conocida = conocida;
	}

	public Instant getFechaUltimaRespuesta() {
		return fechaUltimaRespuesta;
	}

	public void setFechaUltimaRespuesta(Instant fechaUltimaRespuesta) {
		this.fechaUltimaRespuesta = fechaUltimaRespuesta;
	}

	public Integer getTotalConocidas() {
		return totalConocidas;
	}

	public void setTotalConocidas(Integer totalConocidas) {
		this.totalConocidas = totalConocidas;
	}

	public Integer getTotalNoConocidas() {
		return totalNoConocidas;
	}

	public void setTotalNoConocidas(Integer totalNoConocidas) {
		this.totalNoConocidas = totalNoConocidas;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
