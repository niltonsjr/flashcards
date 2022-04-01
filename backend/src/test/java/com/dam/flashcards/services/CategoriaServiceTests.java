package com.dam.flashcards.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.dam.flashcards.Factory;
import com.dam.flashcards.dto.CategoriaDTO;
import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.repositories.CategoriaRepository;
import com.dam.flashcards.repositories.TarjetaRepository;
import com.dam.flashcards.repositories.UsuarioRepository;
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
class CategoriaServiceTests {

    @InjectMocks
    private CategoriaService service;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private TarjetaRepository tarjetaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AuthService authService;

    private Long existingId;
    private Long nonExistingId;
    private Long dependentId;
    private PageImpl<Categoria> page;
    private Usuario usuario;
    private Categoria categoria;
    private CategoriaDTO categoriaDTO;
    private Pageable pageable;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;
        usuario = Factory.createUsuario();
        categoria = Factory.createCategoria();
        categoriaDTO = Factory.createCategoriaDTO();
        page = new PageImpl<Categoria>(List.of(categoria));
        pageable = PageRequest.of(0, 10);

        Mockito.when(authService.autenticado()).thenReturn(usuario);

        Mockito.when(categoriaRepository.getById(existingId)).thenReturn(categoria);
        Mockito.when(categoriaRepository.findById(existingId)).thenReturn(Optional.of(categoria));
        Mockito.when(categoriaRepository.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
        Mockito.when(categoriaRepository.save(ArgumentMatchers.any())).thenReturn(categoria);
        Mockito.when(categoriaRepository.findByUsuario(ArgumentMatchers.any(Usuario.class),
                ArgumentMatchers.any(Pageable.class))).thenReturn(page);
        Mockito.doThrow(EntityNotFoundException.class).when(categoriaRepository).getById(nonExistingId);
        Mockito.doNothing().when(categoriaRepository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(categoriaRepository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(categoriaRepository).deleteById(dependentId);

        Mockito.when(usuarioRepository.getById(existingId)).thenReturn(usuario);
    }

    @Test
    void findAllPagedShoultReturnPageOfCategoriaDTO() {
        Page<CategoriaDTO> result = service.findAllPaged(pageable);
        Assertions.assertNotNull(result);
        Mockito.verify(authService, Mockito.times(1)).autenticado();
        Mockito.verify(categoriaRepository, Mockito.times(1)).findByUsuario(usuario, pageable);
    }

    @Test
    void findByIdShouldReturnCategoriaDTOOfProvidesCategoriaId() {
        CategoriaDTO result = service.findById(existingId);
        Assertions.assertNotNull(result);
        Mockito.verify(categoriaRepository, Mockito.times(1)).findById(existingId);
    }

    @Test
    void findByIdShouldThrowResourceNotFoundExceptionIfProvidedCategoriaIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    @Test
    void insertSholdReturnCategoriaDTO() {
        CategoriaDTO result = service.insert(categoriaDTO);
        Assertions.assertNotNull(result);
    }

    @Test
    void updateShouldReturnCategoriaDTOWhenIdExists() {
        CategoriaDTO dto = service.update(existingId, categoriaDTO);
        Assertions.assertNotNull(dto);
    }

    @Test
    void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, categoriaDTO);
        });
    }

    @Test
    void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> {
            service.delete(existingId);
        });
        Mockito.verify(categoriaRepository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
        Mockito.verify(categoriaRepository, Mockito.times(1)).deleteById(nonExistingId);
    }

    @Test
    void deleteShouldThrowDatabaseExceptionWhenDependentId() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            service.delete(dependentId);
        });
        Mockito.verify(categoriaRepository, Mockito.times(1)).deleteById(dependentId);
    }

}
