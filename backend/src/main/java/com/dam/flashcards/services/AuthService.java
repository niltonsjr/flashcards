package com.dam.flashcards.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.repositories.UsuarioRepository;
import com.dam.flashcards.services.exceptions.ForbiddenException;
import com.dam.flashcards.services.exceptions.UnauthorizedException;

@Service
public class AuthService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Método para obtener los datos del usuario autenticado en la aplicación
	 * 
	 * @return Usuario Los datos del usuario
	 */
	@Transactional(readOnly = true)
	public Usuario autenticado() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Usuario> obj = usuarioRepository.findByNombreDeUsuario(username);
		return obj.orElseThrow(() -> new UnauthorizedException("Usuario inválido."));
	}

	/**
	 * Método para comprobar si el usuario cuyo ID facilitado es el mismo del que
	 * está autenticado o tiene Rol de Administrador
	 * 
	 * @param usuarioId El ID del usuario a comprobar
	 */
	@Transactional
	public void validarUsuarioLogadoOAdministrador(Long usuarioId) {
		Usuario usuario = autenticado();
		if (!usuario.getId().equals(usuarioId) && !usuario.tieneRol("ROLE_ADMINISTRADOR")) {
			throw new ForbiddenException("Acceso denegado.");
		}
	}
}
