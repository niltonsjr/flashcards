package com.dam.flashcards.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String nombreDeUsuario;
	private String nombre;
	private String apellidos;
	@Column(unique = true)
	private String email;
	private String contrasena;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	private List<Tarjeta> tarjetas = new ArrayList<>();

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	private Set<Categoria> categorias = new HashSet<>();

	private String resetToken;

	public Usuario() {
	}

	public Usuario(Long id, String nombreDeUsuario, String nombre, String apellidos, String email, String contrasena) {
		this.id = id;
		this.nombreDeUsuario = nombreDeUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.contrasena = contrasena;
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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public String getResetToken() {
		return this.resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return contrasena;
	}

	@Override
	public String getUsername() {
		return nombreDeUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean tieneRol(String nombreRol) {
		for (Rol rol : roles) {
			if (rol.getNombre().equals(nombreRol)) {
				return true;
			}
		}
		return false;
	}
}
