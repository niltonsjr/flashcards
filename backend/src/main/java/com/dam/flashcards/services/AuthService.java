package com.dam.flashcards.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.repositories.UsuarioRepository;
import com.dam.flashcards.services.exceptions.ForbiddenException;
import com.dam.flashcards.services.exceptions.UnauthorizedException;

@Service
public class AuthService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	public Usuario autenticado() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return usuarioRepository.findByNombreDeUsuario(username);
		} catch (Exception e) {
			throw new UnauthorizedException("Usuario inválido.");
		}
	}
	
	@Transactional
	public void validarUsuarioLogadoOAdministrador(Long usuarioId) {
		Usuario usuario = autenticado();		
		if (!usuario.getId().equals(usuarioId) && !usuario.tieneRol("ROLE_ADMINISTRADOR")) {
			throw new ForbiddenException("Acceso denegado.");
		}
	}
}
