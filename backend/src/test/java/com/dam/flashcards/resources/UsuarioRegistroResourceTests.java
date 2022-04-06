package com.dam.flashcards.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dam.flashcards.Factory;
import com.dam.flashcards.dto.UsuarioDTO;
import com.dam.flashcards.dto.UsuarioRegistroDTO;
import com.dam.flashcards.entities.Usuario;
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
class UsuarioRegistroResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Usuario existingUsuario;
    private UsuarioDTO existingUsuarioDTO;
    private UsuarioRegistroDTO usuarioRegistroDTOExistente;
    private UsuarioRegistroDTO usuarioRegistroDTONoExistente;

    @BeforeEach
    void setUp() throws Exception {
        existingUsuario = Factory.createUsuario();
        existingUsuarioDTO = Factory.createUsuarioDTO();
        usuarioRegistroDTOExistente = Factory.createUsuarioRegistroDTO("fernando");
        usuarioRegistroDTONoExistente = Factory.createUsuarioRegistroDTO("pablo");

        when(service.findUsuarioByNombreDeUsuario(usuarioRegistroDTOExistente.getNombre())).thenReturn(existingUsuario);
        when(service.findUsuarioByNombreDeUsuario(usuarioRegistroDTONoExistente.getNombre()))
                .thenThrow(ResourceNotFoundException.class);
        when(service.register(any())).thenReturn(existingUsuarioDTO);
    }

    @Test
    void registrarUsuarioShouldReturnBadRequestWhenNombreDeUsuarioExists() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(usuarioRegistroDTOExistente);
        ResultActions result = mockMvc
                .perform(post("/registro")
                        .content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isBadRequest());
    }

    @Test
    void registrarUsuarioShouldReturnCreatedWhenNombreDeUsuarioDoesNotExists() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(usuarioRegistroDTONoExistente);
        ResultActions result = mockMvc
                .perform(post("/registro")
                        .content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());

    }
}
