package com.dam.flashcards.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.dam.flashcards.Factory;
import com.dam.flashcards.dto.CategoriaDTO;
import com.dam.flashcards.services.CategoriaService;
import com.dam.flashcards.services.exceptions.DatabaseException;
import com.dam.flashcards.services.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    private PageImpl<CategoriaDTO> pageCategoriaDTO;
    private CategoriaDTO categoriaDTO;
    private CategoriaDTO newCategoriaDTO;
    private Long existingId;
    private Long nonExistingId;
    private Long dependentId;

    private String adminUsername;
    private String adminPassword;

    @BeforeEach
    void setUp() throws Exception {
        adminUsername = "juanpg";
        adminPassword = "123456";

        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;
        categoriaDTO = Factory.createCategoriaDTO();
        pageCategoriaDTO = new PageImpl<>(List.of(categoriaDTO));
        newCategoriaDTO = Factory.createCategoriaDTO(null);

        when(service.findAllPaged(any(Pageable.class))).thenReturn(pageCategoriaDTO);
        when(service.findById(existingId)).thenReturn(categoriaDTO);
        when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
        when(service.insert(any())).thenReturn(categoriaDTO);

        when(service.update(eq(existingId), any())).thenReturn(categoriaDTO);
        when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);

        doNothing().when(service).delete(existingId);
        doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);
        doThrow(DatabaseException.class).when(service).delete(dependentId);

    }

    @Test
    void findAllShouldReturnPageOfCategoriaDTO() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        ResultActions result = mockMvc.perform(get("/categorias").header("Authorization", "Bearer " + accessToken));
        result.andExpect(status().isOk());
    }

    @Test
    void findByIdSholdReturnCategoriaDTOWhenIdExists() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        ResultActions result = mockMvc
                .perform(get("/categorias/{id}", existingId).header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
    }

    @Test
    void findByIdSholdReturnNotFoundWhenIdDoesNotExists() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        ResultActions result = mockMvc
                .perform(get("/categorias/{id}", nonExistingId)
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }

    @Test
    void insertShouldReturnCreatedWhenDataAreValid() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        String jsonBody = objectMapper.writeValueAsString(newCategoriaDTO);
        ResultActions result = mockMvc.perform(post("/categorias").header("Authorization", "Bearer " + accessToken)
                .content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
    }

    @Test
    void updateShouldReturnTarjetaDTOWhenIdExists() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        String jsonBody = objectMapper.writeValueAsString(categoriaDTO);
        ResultActions result = mockMvc
                .perform(put("/categorias/{id}", existingId)
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
    }

    @Test
    void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        String jsonBody = objectMapper.writeValueAsString(categoriaDTO);
        ResultActions result = mockMvc
                .perform(put("/categorias/{id}", nonExistingId)
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }

    @Test
    void deleteShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        ResultActions result = mockMvc.perform(delete("/categorias/{id}", nonExistingId)
                .header("Authorization", "Bearer " + accessToken));
        result.andExpect(status().isNotFound());
    }

    @Test
    void deleteShouldReturnNoContentWhenIdExists() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        ResultActions result = mockMvc.perform(delete("/categorias/{id}", existingId)
                .header("Authorization", "Bearer " + accessToken));
        result.andExpect(status().isNoContent());
    }


    private String obtainAccessToken(String username, String password) throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", clientId);
        params.add("username", username);
        params.add("password", password);

        ResultActions result = mockMvc
                .perform(post("/oauth/token").params(params).with(httpBasic(clientId, clientSecret))
                        .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

}
