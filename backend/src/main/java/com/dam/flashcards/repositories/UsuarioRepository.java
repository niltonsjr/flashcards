package com.dam.flashcards.repositories;

import java.util.Optional;

import com.dam.flashcards.entities.Rol;
import com.dam.flashcards.entities.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Page<Usuario> findByRoles(Rol rol, Pageable pageable);

	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByResetToken(String resetToken);

	Optional<Usuario> findByNombreDeUsuario(String nombreDeUsuario);

}
