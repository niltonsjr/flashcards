package com.dam.flashcards.repositories;

import java.util.List;

import com.dam.flashcards.Factory;
import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
class CategoriaRepositoryTests {

    @Autowired
    private CategoriaRepository repository;

    private Usuario existingUser;

    @BeforeEach
    void setUp() {
        existingUser = Factory.createUsuario();
    }

    @Test
    void findByUsuarioShouldReturnPageOfCategoria() {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<Categoria> result = repository.findByUsuario(existingUser, pageRequest);
        Assertions.assertTrue(result.hasContent());
        List<Categoria> categorias = result.getContent();
        Assertions.assertTrue(categorias.stream().allMatch((c) -> c.getUsuario().equals(existingUser)));
    }

}
