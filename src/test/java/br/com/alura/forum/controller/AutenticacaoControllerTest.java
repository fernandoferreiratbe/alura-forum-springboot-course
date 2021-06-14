package br.com.alura.forum.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class AutenticacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornar400AoReceberLoginIncorreto() throws Exception {
        URI uri = new URI("/auth");
        String corpoDaRequisicao = "{ \"email\": \"invalid@me.com\", \"senha\": \"123mudar\" }";

        this.mockMvc
                .perform(MockMvcRequestBuilders
                    .post(uri)
                    .content(corpoDaRequisicao)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                    .status()
                    .is(HttpStatus.UNAUTHORIZED.value()));
    }
}
