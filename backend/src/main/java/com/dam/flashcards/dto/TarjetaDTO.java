package com.dam.flashcards.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;

import com.dam.flashcards.entities.Tarjeta;

/**
 * Clase DTO con datos de una tarjeta completos
 */
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
	private CategoriaBasicaDTO categoria = new CategoriaBasicaDTO();
	private Long usuarioId;

	public TarjetaDTO() {
	}

	public TarjetaDTO(Long id, String frontal, String trasera, Boolean conocida, Instant fechaUltimaRespuesta,
			Integer totalConocidas, Integer totalNoConocidas, CategoriaBasicaDTO categoria, Long usuarioId) {
		this.id = id;
		this.frontal = frontal;
		this.trasera = trasera;
		this.conocida = conocida;
		this.fechaUltimaRespuesta = fechaUltimaRespuesta;
		this.totalConocidas = totalConocidas;
		this.totalNoConocidas = totalNoConocidas;
		this.categoria = categoria;
		this.usuarioId = usuarioId;
	}

	public TarjetaDTO(Tarjeta entity) {
		id = entity.getId();
		frontal = entity.getFrontal();
		trasera = entity.getTrasera();
		conocida = entity.getConocida();
		fechaUltimaRespuesta = entity.getFechaUltimaRespuesta();
		totalConocidas = entity.getTotalConocidas();
		totalNoConocidas = entity.getTotalNoConocidas();
		categoria.setId(entity.getCategoria().getId());
		categoria.setNombre(entity.getCategoria().getNombre());
		categoria.setUsuarioId(entity.getCategoria().getUsuario().getId());
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

	public CategoriaBasicaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaBasicaDTO categoria) {
		this.categoria = categoria;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

}
