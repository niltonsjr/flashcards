package com.dam.flashcards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.flashcards.entities.Tarjeta;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long>{
}
