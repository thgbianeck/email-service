package com.viasoft.emailservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import com.viasoft.emailservice.exception.EmailProcessingException;
import com.viasoft.emailservice.exception.InvalidEmailDataException;
import com.viasoft.emailservice.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes unitários para EmailController.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@WebMvcTest(EmailController.class)
class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    private EmailRequestDTO validEmailRequest;

    @BeforeEach
    void setUp() {
        validEmailRequest = new EmailRequestDTO(
                "destinatario@teste.com",
                "João Silva",
                "remetente@teste.com",
                "Assunto de Teste",
                "Conteúdo do email de teste"
        );
    }

    @Test
    @DisplayName("Enviar email com dados válidos deve retornar 204 No Content")
    void sendEmail_ComDadosValidos_DeveRetornar204() throws Exception {
        // Given
        doNothing().when(emailService).processEmail(any(EmailRequestDTO.class));

        // When & Then
        mockMvc.perform(post("/emails/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validEmailRequest)))
                .andExpect(status().isNoContent());

        verify(emailService, times(1)).processEmail(any(EmailRequestDTO.class));
    }

    @Test
    @DisplayName("Enviar email com destinatário inválido deve retornar 400 Bad Request")
    void sendEmail_ComEmailInvalido_DeveRetornar400() throws Exception {
        // Given
        EmailRequestDTO invalidEmail = new EmailRequestDTO(
                "email-invalido",
                "João Silva",
                "remetente@teste.com",
                "Assunto",
                "Conteúdo"
        );

        // When & Then
        mockMvc.perform(post("/emails/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmail)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.emailDestinatario").exists());

        verify(emailService, never()).processEmail(any(EmailRequestDTO.class));
    }

    @Test
    @DisplayName("Enviar email com campos obrigatórios vazios deve retornar 400 Bad Request")
    void sendEmail_ComCamposObrigatoriosVazios_DeveRetornar400() throws Exception {
        // Given
        EmailRequestDTO emptyEmail = new EmailRequestDTO(
                "",
                "",
                "",
                "",
                ""
        );

        // When & Then
        mockMvc.perform(post("/emails/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emptyEmail)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists());

        verify(emailService, never()).processEmail(any(EmailRequestDTO.class));
    }

    @Test
    @DisplayName("Enviar email com erro de processamento deve retornar 500 Internal Server Error")
    void sendEmail_ComErroDeProcessamento_DeveRetornar500() throws Exception {
        // Given
        doThrow(new EmailProcessingException("Erro de processamento"))
                .when(emailService).processEmail(any(EmailRequestDTO.class));

        // When & Then
        mockMvc.perform(post("/emails/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validEmailRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Erro no processamento do email"));

        verify(emailService, times(1)).processEmail(any(EmailRequestDTO.class));
    }

    @Test
    @DisplayName("Enviar email com dados inválidos deve retornar 400 Bad Request")
    void sendEmail_ComDadosInvalidos_DeveRetornar400() throws Exception {
        // Given
        doThrow(new InvalidEmailDataException("Dados inválidos"))
                .when(emailService).processEmail(any(EmailRequestDTO.class));

        // When & Then
        mockMvc.perform(post("/emails/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validEmailRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Dados de email inválidos"));

        verify(emailService, times(1)).processEmail(any(EmailRequestDTO.class));
    }

    @Test
    @DisplayName("Verificar saúde do serviço deve retornar 200 OK")
    void health_DeveRetornar200() throws Exception {
        // When & Then
        mockMvc.perform(get("/emails/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Email Service está funcionando!"));
    }
}