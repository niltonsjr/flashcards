package com.dam.flashcards;

import java.time.Instant;

import com.dam.flashcards.dto.CategoriaBasicaDTO;
import com.dam.flashcards.dto.TarjetaBasicaDTO;
import com.dam.flashcards.dto.TarjetaDTO;
import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Rol;
import com.dam.flashcards.entities.Tarjeta;
import com.dam.flashcards.entities.Usuario;

public class Factory {

    public static Tarjeta createTarjeta() {
        Tarjeta tarjeta = new Tarjeta(null, "Frontal de la tarjeta", "Trasera de la tarjeta", false,
                Instant.parse("2020-10-20T20:50:07Z"), 0, 0);
        tarjeta.setCategoria(createCategoria());
        tarjeta.setUsuario(createUsuario());
        return tarjeta;
    }

    public static TarjetaDTO createTarjetaDTO() {
        return new TarjetaDTO(null, "Frontal de la tarjeta", "Trasera de la tarjeta", false,
                Instant.parse("2020-10-20T20:50:07Z"),
                0, 0, new CategoriaBasicaDTO(1L, "Vocabulario Inglés", 1L), 1L);
    }

    public static TarjetaBasicaDTO createTarjetaBasicaDTO() {
        return new TarjetaBasicaDTO(createTarjeta());
    }

    public static Usuario createUsuarioAdministrador() {
        Usuario usuario = new Usuario(1L, "juanpg", "Juan", "Perez Gonzalez", "juan@gmail.com", "123456");
        usuario.getRoles().add(createRolUsuario());
        usuario.getRoles().add(createRolAdministrador());
        return usuario;
    }

    public static Usuario createUsuario() {
        Usuario usuario = new Usuario(1L, "mariafs", "Maria", "Fernandez Silva", "maria@gmail.com", "123456");
        usuario.getRoles().add(createRolUsuario());
        return usuario;
    }


    public static Categoria createCategoria() {
        Categoria categoria = new Categoria(1L, "Vocabulario Inglés");
        categoria.setUsuario(createUsuario());
        return categoria;
    }

    public static Rol createRolUsuario() {
        return new Rol(1L, "ROLE_USUARIO");
    }

    public static Rol createRolAdministrador() {
        return new Rol(2L, "ROLE_ADMINISTRADOR");
    }

}
