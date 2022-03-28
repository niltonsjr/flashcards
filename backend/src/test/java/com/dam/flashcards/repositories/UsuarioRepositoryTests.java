package com.dam.flashcards.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.dam.flashcards.Factory;
import com.dam.flashcards.entities.Rol;
import com.dam.flashcards.entities.Usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
public class UsuarioRepositoryTests {

    @Autowired
    private UsuarioRepository repository;

    private Rol rolUsuario;
    private String email;
    private String resetToken;
    private String nombreDeUsuario;

    @BeforeEach
    void setUp() {
        rolUsuario = Factory.createRolUsuario();
        email = "juan@gmail.com";
        resetToken = UUID.randomUUID().toString();
        nombreDeUsuario = "juanpg";
    }

    @Test
    void findByRolesShouldReturnPageOfUsersWithProvidedRol() {
        PageRequest page = PageRequest.of(0, 10);
        Page<Usuario> result = repository.findByRoles(rolUsuario, page);
        Assertions.assertTrue(result.hasContent());
        List<Usuario> usuarios = result.getContent();
        Assertions.assertTrue(usuarios.stream().allMatch((u) -> u.getRoles().contains(rolUsuario)));
    }

    @Test
    void findByEmailShouldReturnUsuarioWithProvidedEmail() {
        Optional<Usuario> result = repository.findByEmail(email);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Juan", result.get().getNombre());
        Assertions.assertEquals("Juan", result.get().getNombre());
        Assertions.assertEquals("Perez Gonzalez", result.get().getApellidos());
    }

    @Test
    void findByResetTokenShouldReturnUsuarioWithProvidedResetToken() {
        Usuario usuario = repository.getById(1L);
        usuario.setResetToken(resetToken);
        repository.save(usuario);

        Optional<Usuario> result = repository.findByResetToken(resetToken);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(1L, result.get().getId());
        Assertions.assertEquals("juanpg", result.get().getNombreDeUsuario());
    }

    @Test
    void findByNombreDeUsuarioShouldReturnUsuarioWithProvidedNombre() {
        Optional<Usuario> result = repository.findByNombreDeUsuario(nombreDeUsuario);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("juanpg", result.get().getNombreDeUsuario());
        Assertions.assertEquals("Juan", result.get().getNombre());
    }

}
