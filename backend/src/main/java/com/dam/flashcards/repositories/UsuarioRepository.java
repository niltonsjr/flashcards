package com.dam.flashcards.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.flashcards.entities.Rol;
import com.dam.flashcards.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Page<Usuario> findByRoles(Rol rol, Pageable pageable);
	
	Usuario findByEmail(String email);

	Usuario findByNombreDeUsuario(String nombreDeUsuario);
	
	

}
