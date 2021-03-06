package com.dam.flashcards.repositories;

import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Tarjeta;
import com.dam.flashcards.entities.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {

	@Query("SELECT tar FROM Tarjeta tar WHERE " +
			"(:categoria IS NULL OR :categoria = tar.categoria) " +
			"AND :usuario = tar.usuario " +
			"AND ((LOWER(tar.frontal) LIKE LOWER(CONCAT('%',:texto,'%'))) " +
			"OR (LOWER(tar.trasera) LIKE LOWER(CONCAT('%',:texto,'%')))) " +
			"ORDER BY tar.fechaUltimaRespuesta ASC NULLS FIRST, tar.conocida")
	Page<Tarjeta> findByUsuarioAndCategoria(Usuario usuario, Categoria categoria, String texto, Pageable pageable);

}
