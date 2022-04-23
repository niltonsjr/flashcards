package com.dam.flashcards.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.dam.flashcards.entities.Usuario;

/**
 * Clase DTO con datos básicos de un usuario
 */
public class UsuarioBasicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombreDeUsuario;
	private String nombre;
	private String apellidos;
	private String email;
	private Set<RolDTO> roles = new HashSet<>();

	public UsuarioBasicoDTO() {
	}

	public UsuarioBasicoDTO(Long id, String nombreDeUsuario, String nombre, String apellidos, String email) {
		this.id = id;
		this.nombreDeUsuario = nombreDeUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}

	public UsuarioBasicoDTO(Usuario entity) {
		id = entity.getId();
		nombreDeUsuario = entity.getNombreDeUsuario();
		nombre = entity.getNombre();
		apellidos = entity.getApellidos();
		email = entity.getEmail();
		entity.getRoles().forEach(rol -> this.roles.add(new RolDTO(rol)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RolDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolDTO> roles) {
		this.roles = roles;
	}

}
