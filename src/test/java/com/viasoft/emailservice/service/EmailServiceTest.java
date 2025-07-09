package com.viasoft.emailservice.service;

import com.viasoft.emailservice.adapter.EmailAdapter;
import com.viasoft.emailservice.adapter.factory.EmailAdapterFactory;
import com.viasoft.emailservice.config.EmailConfig;
import com.viasoft.emailservice.dto.EmailAwsDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import com.viasoft.emailservice.enums.EmailProvider;
import com.viasoft.emailservice.exception.EmailProcessingException;
import com.viasoft.emailservice.service.impl.EmailServiceImpl;
import com.viasoft.emailservice.util.JsonSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para EmailService.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private EmailConfig emailConfig;

    @Mock
    private EmailAdapterFactory adapterFactory;

    @Mock
    private JsonSerializer jsonSerializer;

    @Mock
    private EmailAdapter<EmailAwsDTO> emailAdapter;

    @InjectMocks
    private EmailServiceImpl emailService;

    private EmailRequestDTO emailRequest;
    private EmailAwsDTO emailAwsDTO;

    @BeforeEach
    void setUp() {
        emailRequest = new EmailRequestDTO(
                "destinatario@teste.com",
                "João Silva",
                "remetente@teste.com",
                "Assunto de Teste",
                "Conteúdo do email de teste"
        );

        emailAwsDTO = new EmailAwsDTO(
                "destinatario@teste.com",
                "João Silva",
                "remetente@teste.com",
                "Assunto de Teste",
                "Conteúdo do email de teste"
        );
    }

    @Test
    @DisplayName("processEmail - Deve processar email com sucesso usando o provedor AWS")
    void processEmail_ComProvedorAWS_DeveProcessarComSucesso() {
        // Given
        when(emailConfig.getEmailProvider()).thenReturn(EmailProvider.AWS);
        when(adapterFactory.createAdapter(EmailProvider.AWS)).thenReturn((EmailAdapter) emailAdapter);
        when(emailAdapter.adapt(emailRequest)).thenReturn(emailAwsDTO);
        when(jsonSerializer.serialize(emailAwsDTO)).thenReturn("{\"recipient\":\"destinatario@teste.com\"}");

        // When
        assertDoesNotThrow(() -> emailService.processEmail(emailRequest));

        // Then
        verify(emailConfig, times(1)).getEmailProvider();
        verify(adapterFactory, times(1)).createAdapter(EmailProvider.AWS);
        verify(emailAdapter, times(1)).adapt(emailRequest);
        verify(jsonSerializer, times(1)).serialize(emailAwsDTO);
    }

    @Test
    @DisplayName("processEmail - Deve processar email com sucesso usando o provedor SMTP")
    void processEmail_ComErroNaAdaptacao_DeveLancarExcecao() {
        // Given
        when(emailConfig.getEmailProvider()).thenReturn(EmailProvider.AWS);
        when(adapterFactory.createAdapter(EmailProvider.AWS)).thenReturn((EmailAdapter) emailAdapter);
        when(emailAdapter.adapt(emailRequest)).thenThrow(new IllegalArgumentException("Erro de adaptação"));

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> emailService.processEmail(emailRequest)
        );

        assertEquals("Erro de adaptação", exception.getMessage());
        verify(jsonSerializer, never()).serialize(any());
    }

    @Test
    @DisplayName("processEmail - Deve lançar exceção ao ocorrer erro na adaptação do email")
    void processEmail_ComErroNaSerializacao_DeveLancarExcecao() {
        // Given
        when(emailConfig.getEmailProvider()).thenReturn(EmailProvider.AWS);
        when(adapterFactory.createAdapter(EmailProvider.AWS)).thenReturn((EmailAdapter) emailAdapter);
        when(emailAdapter.adapt(emailRequest)).thenReturn(emailAwsDTO);
        when(jsonSerializer.serialize(emailAwsDTO))
                .thenThrow(new EmailProcessingException("Erro na serialização"));

        // When & Then
        EmailProcessingException exception = assertThrows(
                EmailProcessingException.class,
                () -> emailService.processEmail(emailRequest)
        );

        assertEquals("Erro na serialização", exception.getMessage());
    }

    @Test
    @DisplayName("processEmail - Deve lançar EmailProcessingException ao ocorrer erro inesperado")
    void processEmail_ComErroInesperado_DeveLancarEmailProcessingException() {
        // Given
        when(emailConfig.getEmailProvider()).thenThrow(new RuntimeException("Erro inesperado"));

        // When & Then
        EmailProcessingException exception = assertThrows(
                EmailProcessingException.class,
                () -> emailService.processEmail(emailRequest)
        );

        assertTrue(exception.getMessage().contains("Erro inesperado durante o processamento do email"));
    }
}