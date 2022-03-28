package com.dam.flashcards.repositories;

import java.util.List;
import java.util.Optional;

import com.dam.flashcards.Factory;
import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Tarjeta;
import com.dam.flashcards.entities.Usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
class TarjetaRepositoryTests {

    @Autowired
    private TarjetaRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalTarjetas;
    private Usuario existingUser;
    private Categoria existingCategory;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalTarjetas = 60L;
        existingUser = Factory.createUsuario();
        existingCategory = Factory.createCategoria();
    }

    @Test
    void deleteShouldDeleteObjectWhenIdExists() {
        repository.deleteById(existingId);
        Optional<Tarjeta> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesnNotExists() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }

    @Test
    void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        Tarjeta tarjeta = Factory.createTarjeta();
        tarjeta.setId(null);
        tarjeta = repository.save(tarjeta);
        Assertions.assertNotNull(tarjeta.getId());
        Assertions.assertEquals(countTotalTarjetas + 1, tarjeta.getId());
    }

    @Test
    void findByIdShouldReturnNotEmptyOptionalWhenIdExists() {
        Optional<Tarjeta> result = repository.findById(existingId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
        Optional<Tarjeta> result = repository.findById(nonExistingId);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void findByUsuarioAndCategoriaShouldReturnPageOfTarjetasOfProvidedUserAndCategoryAndText() {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<Tarjeta> result = repository.findByUsuarioAndCategoria(existingUser, existingCategory, "f", pageRequest);
        Assertions.assertTrue(result.hasContent());
        List<Tarjeta> tarjetas = result.getContent();
        Assertions.assertTrue(tarjetas.stream().allMatch((t) -> t.getUsuario().equals(existingUser)));          
        Assertions.assertTrue(tarjetas.stream().allMatch((t) -> t.getCategoria().equals(existingCategory)));
        Assertions.assertEquals(2, tarjetas.size());    
        Assertions.assertEquals("Funny", tarjetas.get(0).getFrontal());
        Assertions.assertEquals("Fat", tarjetas.get(1).getFrontal());        
    }
}
