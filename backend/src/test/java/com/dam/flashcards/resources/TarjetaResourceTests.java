package com.dam.flashcards.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.dam.flashcards.Factory;
import com.dam.flashcards.dto.TarjetaBasicaDTO;
import com.dam.flashcards.dto.TarjetaDTO;
import com.dam.flashcards.services.TarjetaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TarjetaResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarjetaService service;

    private PageImpl<TarjetaDTO> pageTarjetaDto;
    private PageImpl<TarjetaBasicaDTO> pageTarjetaBasicaDto;
    private TarjetaDTO tarjetaDTO;
    private TarjetaBasicaDTO tarjetaBasicaDTO;
    private Long existingId;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        tarjetaDTO = Factory.createTarjetaDTO();
        tarjetaBasicaDTO = Factory.createTarjetaBasicaDTO();
        pageTarjetaBasicaDto = new PageImpl<>(List.of(tarjetaBasicaDTO));
        pageTarjetaDto = new PageImpl<>(List.of(tarjetaDTO));

        //Mockito.when(service.findAllPaged(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(pageTarjetaBasicaDto);
    }

    @Test
    public void findAllShouldReturnPage() throws Exception {
        mockMvc.perform(get("/tarjetas")).andExpect(status().isOk());
    }
}
