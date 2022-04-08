package com.dam.flashcards.resources;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dam.flashcards.Factory;
import com.dam.flashcards.dto.NuevaContrasenaDTO;
import com.dam.flashcards.dto.UsuarioEmailDTO;
import com.dam.flashcards.entities.Usuario;
import com.dam.flashcards.services.EmailService;
import com.dam.flashcards.services.UsuarioService;
import com.dam.flashcards.services.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class OlvidoContrasenaResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    @MockBean
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsuarioEmailDTO existingUsuarioEmailDTO;
    private UsuarioEmailDTO nonExistingUsuarioEmailDTO;
    private Usuario usuario;
    private NuevaContrasenaDTO nuevaContrasenaDTO;
    private NuevaContrasenaDTO nonExistingNuevaContrasenaDTO;

    @BeforeEach
    void setUp() throws Exception {
        existingUsuarioEmailDTO = new UsuarioEmailDTO("email@gmail.com");
        nonExistingUsuarioEmailDTO = new UsuarioEmailDTO("noexisteemail@gmail.com");
        usuario = Factory.createUsuario();
        nuevaContrasenaDTO = new NuevaContrasenaDTO("nuevaContrasena", "token");
        nonExistingNuevaContrasenaDTO = new NuevaContrasenaDTO("nuevaContrasena", "noExistsToken");

        when(service.findByUsuarioEmail(existingUsuarioEmailDTO.getEmail())).thenReturn(usuario);
        when(service.findByUsuarioEmail(nonExistingUsuarioEmailDTO.getEmail()))
                .thenThrow(ResourceNotFoundException.class);

        when(service.findUsuarioByResetToken(nuevaContrasenaDTO.getToken())).thenReturn(usuario);
        when(service.findUsuarioByResetToken(nonExistingNuevaContrasenaDTO.getToken()))
                .thenThrow(ResourceNotFoundException.class);
    }

    @Test
    void enviarEmailContrasenaOlvidadaShouldDoNothingAndReturnOkWhenEmailExists() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(existingUsuarioEmailDTO);
        ResultActions result = mockMvc
                .perform(post("/contrasena_olvidada").header("Referer", "http://prueba.com/")
                        .content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    void enviarEmailContrasenaOlvidadaShouldThrowNotFoundWhenEmailDoesNotExists() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(nonExistingUsuarioEmailDTO);
        ResultActions result = mockMvc
                .perform(post("/contrasena_olvidada").header("Referer", "http://prueba.com/")
                        .content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }

    @Test
    void resetearContrasenaShouldDoNothinAndReturnOkWhenTokenExists() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(nuevaContrasenaDTO);
        ResultActions result = mockMvc
                .perform(post("/reset_contrasena")
                        .content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    void resetearContrasenaShouldThrowNotFoundWhenTokenDoesNotExists() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(nonExistingNuevaContrasenaDTO);
        ResultActions result = mockMvc
                .perform(post("/reset_contrasena")
                        .content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
                        result.andExpect(status().isNotFound());
    }

}
