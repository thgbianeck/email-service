// src/test/java/com/viasoft/emailservice/adapter/factory/EmailAdapterFactoryTest.java
package com.viasoft.emailservice.adapter.factory;

import com.viasoft.emailservice.adapter.EmailAdapter;
import com.viasoft.emailservice.enums.EmailProvider;
import com.viasoft.emailservice.exception.EmailProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailAdapterFactoryTest {

    private EmailAdapterFactory factory;
    private EmailAdapter<?> mockAdapter;

    @BeforeEach
    void setUp() {
        mockAdapter = mock(EmailAdapter.class);
        when(mockAdapter.getProviderType()).thenReturn("GMAIL");
        factory = new EmailAdapterFactory(List.of(mockAdapter));
    }

    @Test
    @DisplayName("Deve criar o factory com adaptadores válidos")
    void deveCriarAdapterQuandoProvedorExistir() {
        EmailProvider provider = mock(EmailProvider.class);
        when(provider.getValue()).thenReturn("GMAIL");
        EmailAdapter<?> adapter = factory.createAdapter(provider);
        assertNotNull(adapter);
        assertEquals(mockAdapter, adapter);
    }

    @Test
    @DisplayName("Deve lançar exceção quando provedor não existir")
    void deveLancarExcecaoQuandoProvedorNaoExistir() {
        EmailProvider provider = mock(EmailProvider.class);
        when(provider.getValue()).thenReturn("OUTLOOK");
        assertThrows(EmailProcessingException.class, () -> factory.createAdapter(provider));
    }

    @Test
    @DisplayName("Deve retornar o tipo do provedor suportado")
    void deveRetornarTrueSeProvedorForSuportado() {
        EmailProvider provider = mock(EmailProvider.class);
        when(provider.getValue()).thenReturn("GMAIL");
        assertTrue(factory.isProviderSupported(provider));
    }

    @Test
    @DisplayName("Deve retornar false se o provedor não for suportado")
    void deveRetornarFalseSeProvedorNaoForSuportado() {
        EmailProvider provider = mock(EmailProvider.class);
        when(provider.getValue()).thenReturn("YAHOO");
        assertFalse(factory.isProviderSupported(provider));
    }

    @Test
    @DisplayName("Deve retornar os provedores suportados")
    void deveRetornarProvedoresSuportados() {
        Set<String> supported = factory.getSupportedProviders();
        assertEquals(Set.of("GMAIL"), supported);
    }
}