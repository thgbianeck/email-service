package com.viasoft.emailservice.adapter;

import com.viasoft.emailservice.adapter.impl.AwsEmailAdapter;
import com.viasoft.emailservice.dto.EmailAwsDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import com.viasoft.emailservice.enums.EmailProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para AwsEmailAdapter.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
class AwsEmailAdapterTest {

    private AwsEmailAdapter adapter;
    private EmailRequestDTO emailRequest;

    @BeforeEach
    void setUp() {
        adapter = new AwsEmailAdapter();
        emailRequest = new EmailRequestDTO(
                "destinatario@teste.com",
                "João Silva",
                "remetente@teste.com",
                "Assunto de Teste",
                "Conteúdo do email de teste"
        );
    }

    @Test
    @DisplayName("adapt - Com dados válidos, deve retornar EmailAwsDTO")
    void adapt_ComDadosValidos_DeveRetornarEmailAwsDTO() {
        // When
        EmailAwsDTO result = adapter.adapt(emailRequest);

        // Then
        assertNotNull(result);
        assertEquals(emailRequest.getEmailDestinatario(), result.getRecipient());
        assertEquals(emailRequest.getNomeDestinatario(), result.getRecipientName());
        assertEquals(emailRequest.getEmailRemetente(), result.getSender());
        assertEquals(emailRequest.getAssunto(), result.getSubject());
        assertEquals(emailRequest.getConteudo(), result.getContent());
    }

    @Test
    @DisplayName("adapt - Com EmailRequestDTO nulo, deve lançar IllegalArgumentException")
    void adapt_ComEmailRequestNulo_DeveLancarExcecao() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> adapter.adapt(null)
        );

        assertEquals("EmailRequestDTO não pode ser nulo", exception.getMessage());
    }

    @Test
    @DisplayName("adapt - Com EmailDestinatario nulo, deve lançar IllegalArgumentException")
    void adapt_ComEmailDestinatarioMuitoLongo_DeveLancarExcecao() {
        // Given
        emailRequest.setEmailDestinatario("a".repeat(46) + "@teste.com");

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> adapter.adapt(emailRequest)
        );

        assertTrue(exception.getMessage().contains("Email do destinatário excede o limite de 45 caracteres"));
    }

    @Test
    @DisplayName("adapt - Com NomeDestinatario nulo, deve lançar IllegalArgumentException")
    void adapt_ComNomeDestinatarioMuitoLongo_DeveLancarExcecao() {
        // Given
        emailRequest.setNomeDestinatario("a".repeat(61));

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> adapter.adapt(emailRequest)
        );

        assertTrue(exception.getMessage().contains("Nome do destinatário excede o limite de 60 caracteres"));
    }

    @Test
    @DisplayName("adapt - Com EmailRemetente nulo, deve lançar IllegalArgumentException")
    void adapt_ComAssuntoMuitoLongo_DeveLancarExcecao() {
        // Given
        emailRequest.setAssunto("a".repeat(121));

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> adapter.adapt(emailRequest)
        );

        assertTrue(exception.getMessage().contains("Assunto excede o limite de 120 caracteres"));
    }

    @Test
    @DisplayName("adapt - Com Conteudo muito longo, deve lançar IllegalArgumentException")
    void adapt_ComConteudoMuitoLongo_DeveLancarExcecao() {
        // Given
        emailRequest.setConteudo("a".repeat(257));

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> adapter.adapt(emailRequest)
        );

        assertTrue(exception.getMessage().contains("Conteúdo excede o limite de 256 caracteres"));
    }

    @Test
    @DisplayName("getProviderType - Deve retornar o tipo de provedor AWS")
    void getProviderType_DeveRetornarAWS() {
        // When
        String providerType = adapter.getProviderType();

        // Then
        assertEquals(EmailProvider.AWS.getValue(), providerType);
    }
}