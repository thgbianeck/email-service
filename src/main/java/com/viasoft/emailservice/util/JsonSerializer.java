package com.viasoft.emailservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.viasoft.emailservice.exception.EmailProcessingException;
import org.springframework.stereotype.Component;

/**
 * Utilitário para serialização de objetos em JSON.
 *
 * Esta classe fornece métodos para converter objetos
 * em representação JSON de forma padronizada.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Component
public class JsonSerializer {

    /**
     * Mapper responsável pela conversão de objetos para JSON.
     */
    private final ObjectMapper objectMapper;

    /**
     * Construtor que configura o ObjectMapper.
     */
    public JsonSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Serializa um objeto para JSON.
     *
     * @param object objeto a ser serializado
     * @return representação JSON do objeto
     * @throws EmailProcessingException se ocorrer erro na serialização
     */
    public String serialize(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new EmailProcessingException(
                    "Erro ao serializar objeto para JSON: " + e.getMessage(), e
            );
        }
    }

    /**
     * Serializa um objeto para JSON de forma compacta (sem indentação).
     *
     * @param object objeto a ser serializado
     * @return representação JSON compacta do objeto
     * @throws EmailProcessingException se ocorrer erro na serialização
     */
    public String serializeCompact(final Object object) {
        try {
            ObjectMapper compactMapper = new ObjectMapper();
            return compactMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new EmailProcessingException(
                    "Erro ao serializar objeto para JSON compacto: "
                            + e.getMessage(), e
            );
        }
    }
}
