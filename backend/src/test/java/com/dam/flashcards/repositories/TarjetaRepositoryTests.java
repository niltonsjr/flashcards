package com.dam.flashcards.repositories;

import java.util.Optional;

import com.dam.flashcards.Factory;
import com.dam.flashcards.entities.Tarjeta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

@DataJpaTest
public class TarjetaRepositoryTests {

    @Autowired
    private TarjetaRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalTarjetas;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalTarjetas = 60L;
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        repository.deleteById(existingId);
        Optional<Tarjeta> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesnNotExists() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        Tarjeta tarjeta = Factory.createTarjeta();
        tarjeta.setId(null);
        tarjeta = repository.save(tarjeta);
        Assertions.assertNotNull(tarjeta.getId());
        Assertions.assertEquals(countTotalTarjetas + 1, tarjeta.getId());
    }

    @Test
    public void findByIdShouldReturnNotEmptyOptionalWhenIdExists() {
        Optional<Tarjeta> result = repository.findById(existingId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
        Optional<Tarjeta> result = repository.findById(nonExistingId);
        Assertions.assertTrue(result.isEmpty());
    }
}
