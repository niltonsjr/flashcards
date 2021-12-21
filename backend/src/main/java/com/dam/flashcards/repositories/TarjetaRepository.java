package com.dam.flashcards.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.flashcards.entities.Tarjeta;
import com.dam.flashcards.entities.Usuario;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
	
	Page<Tarjeta> findByUsuario(Usuario usuario, Pageable pageable);
	
}
