package com.viasoft.emailservice.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.viasoft.emailservice.exception.EmailProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonSerializerTest {

    static class Dummy {
        @JsonProperty
        String name;
        @JsonProperty
        int value;

        Dummy(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }

    @Test
    @DisplayName("serialize deve serializar objeto corretamente")
    void serialize_deveSerializarObjetoComIndentacao() {
        JsonSerializer serializer = new JsonSerializer();
        Dummy dummy = new Dummy("teste", 123);

        String json = serializer.serialize(dummy);

        assertTrue(json.contains("\"name\""));
        assertTrue(json.contains("\"value\""));
        assertTrue(json.contains("\n")); // Deve estar indentado
    }

    @Test
    @DisplayName("serializeCompact deve serializar objeto sem indentação")
    void serializeCompact_deveSerializarObjetoSemIndentacao() {
        JsonSerializer serializer = new JsonSerializer();
        Dummy dummy = new Dummy("compacto", 456);

        String json = serializer.serializeCompact(dummy);

        assertTrue(json.contains("\"name\""));
        assertTrue(json.contains("\"value\""));
        assertFalse(json.contains("\n")); // Não deve estar indentado
    }

    @Test
    @DisplayName("serialize deve lançar EmailProcessingException em caso de erro")
    void serialize_deveLancarEmailProcessingExceptionEmErro() {
        JsonSerializer serializer = new JsonSerializer();
        Object objetoNaoSerializavel = new Object() {
            // Jackson não consegue serializar objetos com referência circular
            Object ref = this;
        };

        EmailProcessingException ex = assertThrows(
                EmailProcessingException.class,
                () -> serializer.serialize(objetoNaoSerializavel)
        );
        assertTrue(ex.getMessage().contains("Erro ao serializar objeto para JSON"));
    }

    @Test
    @DisplayName("serializeCompact deve lançar EmailProcessingException em caso de erro")
    void serializeCompact_deveLancarEmailProcessingExceptionEmErro() {
        JsonSerializer serializer = new JsonSerializer();
        Object objetoNaoSerializavel = new Object() {
            Object ref = this;
        };

        EmailProcessingException ex = assertThrows(
                EmailProcessingException.class,
                () -> serializer.serializeCompact(objetoNaoSerializavel)
        );
        assertTrue(ex.getMessage().contains("Erro ao serializar objeto para JSON compacto"));
    }
}