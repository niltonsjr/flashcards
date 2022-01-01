package com.dam.flashcards.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tarjeta")
public class Tarjeta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "TEXT")
	private String frontal;
	@Column(columnDefinition = "TEXT")
	private String trasera;
	private boolean conocida;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant fechaUltimaRespuesta;

	private Integer totalConocidas;
	private Integer totalNoConocidas;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Tarjeta() {
	}

	public Tarjeta(Long id, String frontal, String trasera, Boolean conocida, Instant fechaUltimaRespuesta,
			Integer totalConocidas, Integer totalNoConocidas) {
		this.id = id;
		this.frontal = frontal;
		this.trasera = trasera;
		this.conocida = conocida;
		this.fechaUltimaRespuesta = fechaUltimaRespuesta;
		this.totalConocidas = totalConocidas;
		this.totalNoConocidas = totalNoConocidas;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjeta other = (Tarjeta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
