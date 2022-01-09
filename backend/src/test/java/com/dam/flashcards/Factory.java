package com.dam.flashcards;

import java.time.Instant;

import com.dam.flashcards.dto.TarjetaDTO;
import com.dam.flashcards.entities.Categoria;
import com.dam.flashcards.entities.Rol;
import com.dam.flashcards.entities.Tarjeta;
import com.dam.flashcards.entities.Usuario;

public class Factory {

    public static Tarjeta createTarjeta() {
        Tarjeta tarjeta = new Tarjeta(1L, "Frontal de la tarjeta", "Trasera de la tarjeta", false, Instant.parse("2020-10-20T20:50:07Z"), 0, 0);
        tarjeta.setCategoria(new Categoria(2L, "Verbos de Francés"));
        return tarjeta;
    }

    public static TarjetaDTO createTarjetaDTO() {
        Tarjeta tarjeta = createTarjeta();
        return new TarjetaDTO(tarjeta);
     }

     public static Usuario createUsuario() {
         Usuario usuario = new Usuario(1L, "juanpg", "Juan", "Brito Campos", "juanpg@gmail.com", "123456");         
         usuario.getRoles().add(new Rol(1L, "administrador"));
         return usuario;
     }

     public static Categoria createCategoria() {
         Categoria categoria = new Categoria(1L, "Inglés");
         return categoria;
     }

}
