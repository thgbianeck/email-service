package com.viasoft.emailservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * DTO para respostas de erro de validação.
 * Esta classe estende o DTO base de erro e adiciona informações
 * específicas sobre erros de validação de campos.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Schema(description = "Resposta para erros de validação")
public final class ValidationErrorResponseDTO extends ErrorResponseDTO {

    /**
     * Mapa contendo os erros de validação por campo.
     * A chave é o nome do campo e o valor é a mensagem de erro.
     */
    @Schema(description = "Erros de validação por campo",
            example = "{'email': 'Email deve ter formato válido',"
            + " 'nome': 'Nome é obrigatório'}")
    private Map<String, String> errors;

    /**
     * Construtor padrão.
     */
    public ValidationErrorResponseDTO() {
        super();
    }

    /**
     * Construtor com parâmetros.
     *
     * @param httpStatus código de status HTTP
     * @param errorType tipo do erro
     * @param errorMessage mensagem do erro
     * @param validationErrors mapa de erros de validação
     */
    public ValidationErrorResponseDTO(final int httpStatus,
                                      final String errorType,
                                      final String errorMessage,
                                      final Map<String, String>
                                              validationErrors) {
        super(httpStatus, errorType, errorMessage);
        this.errors = validationErrors;
    }

    /**
     * Obtém o mapa de erros de validação.
     *
     * @return o mapa de erros de validação
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * Define o mapa de erros de validação.
     *
     * @param validationErrors o mapa de erros de validação a ser definido
     */
    public void setErrors(final Map<String, String> validationErrors) {
        this.errors = validationErrors;
    }

    @Override
    public String toString() {
        return "ValidationErrorResponseDTO{"
                + "timestamp=" + getTimestamp()
                + ", status=" + getStatus()
                + ", error='" + getError() + '\''
                + ", message='" + getMessage() + '\''
                + ", errors=" + errors
                + '}';
    }
}