package com.dam.flashcards.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.dam.flashcards.Factory;
import com.dam.flashcards.dto.TarjetaBasicaDTO;
import com.dam.flashcards.dto.TarjetaDTO;
import com.dam.flashcards.services.TarjetaService;
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
class TarjetaResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarjetaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    private PageImpl<TarjetaDTO> pageTarjetaDto;
    private PageImpl<TarjetaBasicaDTO> pageTarjetaBasicaDto;
    private TarjetaDTO tarjetaDTO;
    private TarjetaBasicaDTO tarjetaBasicaDTO;
    private Long existingId;
    private Long nonExistingId;

    private String operatorUsername;
    private String operatorPassword;
    private String adminUsername;
    private String adminPassword;

    @BeforeEach
    void setUp() throws Exception {
        operatorUsername = "mariafs";
        operatorPassword = "123456";
        adminUsername = "juanpg";
        adminPassword = "123456";

        existingId = 1L;
        nonExistingId = 2000L;
        tarjetaDTO = Factory.createTarjetaDTO();
        tarjetaBasicaDTO = Factory.createTarjetaBasicaDTO();
        pageTarjetaBasicaDto = new PageImpl<>(List.of(tarjetaBasicaDTO));
        pageTarjetaDto = new PageImpl<>(List.of(tarjetaDTO));

        when(service.findAllPaged(anyLong(), anyString(), any(Pageable.class))).thenReturn(pageTarjetaBasicaDto);
        when(service.findAllCompletePaged(anyLong(), anyString(), any(Pageable.class))).thenReturn(pageTarjetaDto);
        when(service.findById(existingId)).thenReturn(tarjetaDTO);
        when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
        when(service.update(eq(existingId), any())).thenReturn(tarjetaDTO);
        when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);
    }

    @Test
    void findAllShouldReturnPageOfTarjetaBasicaDTO() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        ResultActions result = mockMvc.perform(get("/tarjetas").header("Authorization", "Bearer " + accessToken));
        result.andExpect(status().isOk());
    }

    @Test
    void findAllCompleteShouldReturnPageOfTarjetaDTO() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        ResultActions result = mockMvc
                .perform(get("/tarjetas/completa").header("Authorization", "Bearer " + accessToken));
        result.andExpect(status().isOk());
    }

    @Test
    void findByIdSholdReturnTarjetaDTOWhenIdExists() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        ResultActions result = mockMvc
                .perform(get("/tarjetas/{id}", existingId).header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
    }

    @Test
    void findByIdSholdReturnNotFoundWhenIdDoesNotExists() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        ResultActions result = mockMvc
                .perform(get("/tarjetas/{id}", nonExistingId)
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }

    @Test
    void updateShouldReturnTarjetaDTOWhenIdExists() throws Exception {
        String accessToken = obtainAccessToken(adminUsername, adminPassword);
        String jsonBody = objectMapper.writeValueAsString(tarjetaDTO);
        ResultActions result = mockMvc
                .perform(put("/tarjetas/{id}", existingId)
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
        String jsonBody = objectMapper.writeValueAsString(tarjetaDTO);
        ResultActions result = mockMvc
                .perform(put("/tarjetas/{id}", nonExistingId)
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());

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
