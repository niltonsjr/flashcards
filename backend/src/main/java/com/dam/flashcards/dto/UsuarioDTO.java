package com.dam.flashcards.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Tarjeta;
import com.dam.flashcards.entities.Usuario;

/**
 * Clase DTO con datos de un usuario completo
 */
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@Size(min = 5, max = 20, message = "El nombre de usuario debe tener entre 5 y 20 caracteres.")
	@NotBlank(message = "Campo obligatorio.")
	private String nombreDeUsuario;
	@NotBlank(message = "Campo obligatorio.")
	private String nombre;
	@NotBlank(message = "Campo obligatorio.")
	private String apellidos;
	@Email(message = "Introducir una dirección de correo electrónico válida.")
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
		id = entity.getId();
		nombreDeUsuario = entity.getNombreDeUsuario();
		nombre = entity.getNombre();
		apellidos = entity.getApellidos();
		email = entity.getEmail();
		entity.getRoles().forEach(rol -> this.roles.add(new RolDTO(rol)));
	}

	public UsuarioDTO(Usuario entity, Set<Categoria> categorias, List<Tarjeta> tarjetas) {
		this(entity);
		categorias.forEach(cat -> this.categorias.add(new CategoriaDTO(cat)));
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
