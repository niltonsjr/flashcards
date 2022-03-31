package com.dam.flashcards.services;

import java.util.Optional;

import com.dam.flashcards.Factory;
import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.repositories.UsuarioRepository;
import com.dam.flashcards.services.exceptions.ForbiddenException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AuthServiceTests {

    @InjectMocks
    private AuthService service;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    private Long nonExistingId;
    private String nombreUsuario;
    private Usuario usuario;

    @BeforeEach
    void setUp() throws Exception {
        nonExistingId = 1000L;
        nombreUsuario = "mariafs";
        usuario = Factory.createUsuario();

        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(nombreUsuario);

        Mockito.when(usuarioRepository.findByNombreDeUsuario(nombreUsuario)).thenReturn(Optional.of(usuario));

    }

    @Test
    void autenticadoShouldReturnUsuarioifIsAuthenticated() {
        Usuario autenticado = service.autenticado();
        Assertions.assertNotNull(autenticado);
        Mockito.verify(usuarioRepository, Mockito.times(1)).findByNombreDeUsuario(nombreUsuario);
    }

    @Test
    void validarUsuarioLogadoOAdministradorShouldThrowForbiddenExceptionIfProvidedUsuarioIdIsNotSameAsUsuarioAutenticadoAndUsuarioLogadoIsNotAdministrador() {
        Assertions.assertThrows(ForbiddenException.class, () -> {
            service.validarUsuarioLogadoOAdministrador(nonExistingId);
        });
    }

}
