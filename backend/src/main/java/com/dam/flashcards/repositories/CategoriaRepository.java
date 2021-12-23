package com.dam.flashcards.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Usuario;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Page<Categoria> findByUsuario(Usuario usuario, Pageable pageable);

}
