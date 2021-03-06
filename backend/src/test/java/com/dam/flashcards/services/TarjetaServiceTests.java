package com.dam.flashcards.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.dam.flashcards.Factory;
import com.dam.flashcards.dto.TarjetaBasicaDTO;
import com.dam.flashcards.dto.TarjetaDTO;
import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Tarjeta;
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
class TarjetaServiceTests {

    @InjectMocks
    private TarjetaService service;

    @Mock
    private TarjetaRepository tarjetaRepository;

    @Mock
    private AuthService authService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Long existingId;
    private Long nonExistingId;
    private Long dependentId;
    private PageImpl<Tarjeta> page;
    private Tarjeta tarjeta;
    private Usuario usuario;
    private Categoria categoria;
    private TarjetaDTO tarjetaDTO;
    private Pageable pageable;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;
        tarjeta = Factory.createTarjeta();
        usuario = Factory.createUsuario();
        categoria = Factory.createCategoria();
        tarjetaDTO = Factory.createTarjetaDTO();

        page = new PageImpl<Tarjeta>(List.of(tarjeta));

        pageable = PageRequest.of(0, 10);

        Mockito.when(authService.autenticado()).thenReturn(usuario);

        Mockito.when(categoriaRepository.getById(existingId)).thenReturn(categoria);

        Mockito.when(usuarioRepository.getById(existingId)).thenReturn(usuario);

        Mockito.doNothing().when(tarjetaRepository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(tarjetaRepository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(tarjetaRepository).deleteById(dependentId);

        Mockito.when(tarjetaRepository.findByUsuarioAndCategoria(ArgumentMatchers.any(Usuario.class),
                ArgumentMatchers.any(Categoria.class), ArgumentMatchers.anyString(),
                ArgumentMatchers.any(Pageable.class))).thenReturn(page);
        Mockito.when(tarjetaRepository.findByUsuarioAndCategoria(ArgumentMatchers.any(Usuario.class),
                ArgumentMatchers.isNull(), ArgumentMatchers.anyString(), ArgumentMatchers.any(Pageable.class)))
                .thenReturn(page);
        Mockito.when(tarjetaRepository.save(ArgumentMatchers.any())).thenReturn(tarjeta);

        Mockito.when(tarjetaRepository.findById(existingId)).thenReturn(Optional.of(tarjeta));
        Mockito.when(tarjetaRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(tarjetaRepository.getById(existingId)).thenReturn(tarjeta);
        Mockito.doThrow(EntityNotFoundException.class).when(tarjetaRepository).getById(nonExistingId);
    }

    @Test
    void findAllPagedShoultReturnPageOfTarjetaBasicaDTOOfProvidedCategoria() {
        Page<TarjetaBasicaDTO> result = service.findAllPaged(1L, "prueba", pageable);
        Assertions.assertNotNull(result);
        Mockito.verify(authService, Mockito.times(1)).autenticado();
        Mockito.verify(categoriaRepository, Mockito.times(1)).getById(1L);
        Mockito.verify(tarjetaRepository, Mockito.times(1)).findByUsuarioAndCategoria(usuario, categoria, "prueba",
                pageable);
    }

    @Test
    void findAllPagedShoultReturnPageOfTarjetaBasicaDTOWithAllCategoriasWhenCategoriaIsNotProvided() {
        Page<TarjetaBasicaDTO> result = service.findAllPaged(0L, "prueba", pageable);
        Assertions.assertNotNull(result);
        Mockito.verify(authService, Mockito.times(1)).autenticado();
        Mockito.verify(categoriaRepository, Mockito.times(0)).getById(1L);
        Mockito.verify(tarjetaRepository, Mockito.times(1)).findByUsuarioAndCategoria(usuario, null, "prueba",
                pageable);
    }

    @Test
    void findAllCompletePagedSholdReturnPageOfTarjetaDTOOfProvidedCategoria() {
        Page<TarjetaDTO> result = service.findAllCompletePaged(existingId, "prueba", pageable);
        Assertions.assertNotNull(result);
        Mockito.verify(authService, Mockito.times(1)).autenticado();
        Mockito.verify(categoriaRepository, Mockito.times(1)).getById(existingId);
        Mockito.verify(tarjetaRepository, Mockito.times(1)).findByUsuarioAndCategoria(usuario, categoria, "prueba",
                pageable);
    }

    @Test
    void findAllCompletePagedSholdReturnPageOfTarjetaDTOWithAllCategoriasWhenCategoriaIsNotProvided() {
        Page<TarjetaDTO> result = service.findAllCompletePaged(0L, "prueba", pageable);
        Assertions.assertNotNull(result);
        Mockito.verify(authService, Mockito.times(1)).autenticado();
        Mockito.verify(categoriaRepository, Mockito.times(0)).getById(existingId);
        Mockito.verify(tarjetaRepository, Mockito.times(1)).findByUsuarioAndCategoria(usuario, null, "prueba",
                pageable);
    }

    @Test
    void findByIdShouldReturnTarjetaDTOWhenIdExists() {
        TarjetaDTO dto = service.findById(existingId);
        Assertions.assertNotNull(dto);
        Mockito.verify(tarjetaRepository, Mockito.times(1)).findById(existingId);
    }

    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    @Test
    void insertSholdReturnTarjetaDTO() {
        TarjetaDTO dto = service.insert(tarjetaDTO);
        Assertions.assertNotNull(dto);
        Mockito.verify(tarjetaRepository, Mockito.times(1)).save(tarjeta);
    }

    @Test
    void updateShouldReturnTarjetaDTOWhenIdExists() {
        TarjetaDTO dto = service.update(existingId, tarjetaDTO);
        Assertions.assertNotNull(dto);
    }

    @Test
    void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, tarjetaDTO);
        });
    }

    @Test
    void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> {
            service.delete(existingId);
        });
        Mockito.verify(tarjetaRepository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
        Mockito.verify(tarjetaRepository, Mockito.times(1)).deleteById(nonExistingId);
    }

    @Test
    void deleteShouldThrowDatabaseExceptionWhenDependentId() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            service.delete(dependentId);
        });
        Mockito.verify(tarjetaRepository, Mockito.times(1)).deleteById(dependentId);
    }

}
