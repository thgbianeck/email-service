package com.viasoft.emailservice.adapter;

import com.viasoft.emailservice.adapter.impl.OciEmailAdapter;
import com.viasoft.emailservice.dto.EmailOciDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import com.viasoft.emailservice.enums.EmailProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para OciEmailAdapter.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
class OciEmailAdapterTest {

    private OciEmailAdapter adapter;
    private EmailRequestDTO emailRequest;

    @BeforeEach
    void setUp() {
        adapter = new OciEmailAdapter();
        emailRequest = new EmailRequestDTO(
                "destinatario@teste.com",
                "João Silva",
                "remetente@teste.com",
                "Assunto de Teste",
                "Conteúdo do email de teste"
        );
    }

    @Test
    @DisplayName("adapt_ComDadosValidos_DeveRetornarEmailOciDTO")
    void adapt_ComDadosValidos_DeveRetornarEmailOciDTO() {
        // When
        EmailOciDTO result = adapter.adapt(emailRequest);

        // Then
        assertNotNull(result);
        assertEquals(emailRequest.getEmailDestinatario(), result.getRecipientEmail());
        assertEquals(emailRequest.getNomeDestinatario(), result.getRecipientName());
        assertEquals(emailRequest.getEmailRemetente(), result.getSenderEmail());
        assertEquals(emailRequest.getAssunto(), result.getSubject());
        assertEquals(emailRequest.getConteudo(), result.getBody());
    }

    @Test
    @DisplayName("adapt_ComEmailRequestNulo_DeveLancarExcecao")
    void adapt_ComEmailRequestNulo_DeveLancarExcecao() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> adapter.adapt(null)
        );

        assertEquals("EmailRequestDTO não pode ser nulo", exception.getMessage());
    }

    @Test
    @DisplayName("adapt_ComEmailDestinatarioNulo_DeveLancarExcecao")
    void adapt_ComEmailDestinatarioMuitoLongo_DeveLancarExcecao() {
        // Given
        emailRequest.setEmailDestinatario("a".repeat(41) + "@teste.com");

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> adapter.adapt(emailRequest)
        );

        assertTrue(exception.getMessage().contains("Email do destinatário excede o limite de 40 caracteres"));
    }

    @Test
    @DisplayName("adapt_ComEmailRemetenteNulo_DeveLancarExcecao")
    void adapt_ComAssuntoMuitoLongo_DeveLancarExcecao() {
        // Given
        emailRequest.setAssunto("a".repeat(101));

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> adapter.adapt(emailRequest)
        );

        assertTrue(exception.getMessage().contains("Assunto excede o limite de 100 caracteres"));
    }

    @Test
    @DisplayName("adapt_ComConteudoMuitoLongo_DeveLancarExcecao")
    void adapt_ComConteudoMuitoLongo_DeveLancarExcecao() {
        // Given
        emailRequest.setConteudo("a".repeat(251));

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> adapter.adapt(emailRequest)
        );

        assertTrue(exception.getMessage().contains("Conteúdo excede o limite de 250 caracteres"));
    }

    @Test
    @DisplayName("getProviderType_DeveRetornarOCI")
    void getProviderType_DeveRetornarOCI() {
        // When
        String providerType = adapter.getProviderType();

        // Then
        assertEquals(EmailProvider.OCI.getValue(), providerType);
    }
}