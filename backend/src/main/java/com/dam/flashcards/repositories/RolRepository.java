package com.dam.flashcards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.flashcards.entities.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
}
