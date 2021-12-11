package com.dam.flashcards.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Rol;
import com.dam.flashcards.entities.Tarjeta;
import com.dam.flashcards.entities.Usuario;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombreDeUsuario;
	private String nombre;
	private String apellidos;
	private String email;
	private Set<RolDTO> roles = new HashSet<>();
	private List<TarjetaDTO> tarjetas = new ArrayList<>();
	private Set<CategoriaDTO> categorias = new HashSet<>();

	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, String nombreDeUsuario, String nombre, String apellidos, String email) {
		this.id = id;
		this.nombreDeUsuario = nombreDeUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}

	public UsuarioDTO(Usuario entity) {
		this.id = entity.getId();
		this.nombreDeUsuario = entity.getNombreDeUsuario();
		this.nombre = entity.getNombre();
		this.apellidos = entity.getApellidos();
		this.email = entity.getEmail();
	}

	public UsuarioDTO(Usuario entity, Set<Categoria> categorias, Set<Rol> roles, List<Tarjeta> tarjetas) {
		this(entity);
		categorias.forEach(cat -> this.categorias.add(new CategoriaDTO(cat)));
		roles.forEach(rol -> this.roles.add(new RolDTO(rol)));
		tarjetas.forEach(tar -> this.tarjetas.add(new TarjetaDTO(tar)));
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

	public List<TarjetaDTO> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<TarjetaDTO> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public Set<CategoriaDTO> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<CategoriaDTO> categorias) {
		this.categorias = categorias;
	}

}
