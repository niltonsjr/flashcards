package com.dam.flashcards.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	public Usuario autenticado() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			logger.info("Nombre de usuario autenticado: "+ username);
			return usuarioRepository.findByNombreDeUsuario(username);
		} catch (Exception e) {
			throw new UnauthorizedException("Usuario inválido.");
		}
	}
	
	public void ValidarUsuarioLogadoOAdministrador(Long usuarioId) {
		Usuario usuario = autenticado();
		logger.info("Usuario autenticado: " + usuario.getNombreDeUsuario() + " con id: " + usuario.getId() + ". Id buscado: "+ usuarioId);
		if (!usuario.getId().equals(usuarioId) && !usuario.tieneRol("ROLE_ADMINISTRADOR")) {
			throw new ForbiddenException("Acceso denegado.");
		}
	}
}
