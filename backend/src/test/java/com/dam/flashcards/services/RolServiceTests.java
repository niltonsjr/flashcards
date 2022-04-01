package com.dam.flashcards.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.dam.flashcards.Factory;
import com.dam.flashcards.dto.RolDTO;
import com.dam.flashcards.entities.Rol;
import com.dam.flashcards.repositories.RolRepository;
import com.dam.flashcards.services.exceptions.DatabaseException;
import com.dam.flashcards.services.exceptions.ResourceNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RolServiceTests {

    @InjectMocks
    private RolService service;

    @Mock
    private RolRepository rolRepository;

    private Long existingId;
    private Long nonExistingId;
    private Long dependentId;
    private PageImpl<Rol> page;
    private Rol rol;
    private RolDTO rolDTO;
    private Pageable pageable;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;
        rol = Factory.createRolUsuario();
        rolDTO = Factory.createRolDTO();
        page = new PageImpl<Rol>(List.of(rol));
        pageable = PageRequest.of(0, 10);

        Mockito.when(rolRepository.getById(existingId)).thenReturn(rol);
        Mockito.when(rolRepository.findById(existingId)).thenReturn(Optional.of(rol));
        Mockito.when(rolRepository.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
        Mockito.when(rolRepository.save(ArgumentMatchers.any())).thenReturn(rol);
        Mockito.when(rolRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(page);
        Mockito.doThrow(EntityNotFoundException.class).when(rolRepository).getById(nonExistingId);
        Mockito.doNothing().when(rolRepository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(rolRepository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(rolRepository).deleteById(dependentId);
    }

    @Test
    void findAllPagedShoultReturnPageOfRolDTO() {
        Page<RolDTO> result = service.findAllPaged(pageable);
        Assertions.assertNotNull(result);
        Mockito.verify(rolRepository, Mockito.times(1)).findAll(pageable);
    }

    @Test
    void findByIdShouldReturnRolDTOOfProvidesRolId() {
        RolDTO result = service.findById(existingId);
        Assertions.assertNotNull(result);
        Mockito.verify(rolRepository, Mockito.times(1)).findById(existingId);
    }

    @Test
    void findByIdShouldThrowResourceNotFoundExceptionIfProvidedRolIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    @Test
    void insertSholdReturnrolDTO() {
        RolDTO result = service.insert(rolDTO);
        Assertions.assertNotNull(result);
    }

    @Test
    void updateShouldReturnrolDTOWhenIdExists() {
        RolDTO dto = service.update(existingId, rolDTO);
        Assertions.assertNotNull(dto);
    }

    @Test
    void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, rolDTO);
        });
    }

    @Test
    void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> {
            service.delete(existingId);
        });
        Mockito.verify(rolRepository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
        Mockito.verify(rolRepository, Mockito.times(1)).deleteById(nonExistingId);
    }

    @Test
    void deleteShouldThrowDatabaseExceptionWhenDependentId() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            service.delete(dependentId);
        });
        Mockito.verify(rolRepository, Mockito.times(1)).deleteById(dependentId);
    }

}
