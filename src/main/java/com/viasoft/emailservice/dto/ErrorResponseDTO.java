package com.viasoft.emailservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * DTO base para respostas de erro da aplicação.
 * Esta classe representa a estrutura padrão de resposta para erros,
 * fornecendo informações consistentes sobre falhas na aplicação.
 *
 * <p>Esta classe é final para garantir que não seja estendida,
 * mantendo a integridade do contrato de dados do DTO.</p>
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Schema(description = "Resposta padrão para erros da aplicação")
public class ErrorResponseDTO {

    /**
     * Timestamp de quando o erro ocorreu.
     */
    @Schema(description = "Data e hora do erro",
            example = "2025-01-15T10:30:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    /**
     * Código de status HTTP do erro.
     */
    @Schema(description = "Código de status HTTP", example = "400")
    private int status;

    /**
     * Tipo/categoria do erro.
     */
    @Schema(description = "Tipo do erro", example = "Dados inválidos")
    private String error;

    /**
     * Mensagem descritiva do erro.
     */
    @Schema(description = "Mensagem do erro",
            example = "Falha na validação dos dados")
    private String message;

    /**
     * Construtor padrão.
     */
    public ErrorResponseDTO() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Construtor com parâmetros básicos.
     *
     * @param httpStatus código de status HTTP
     * @param errorType tipo do erro
     * @param errorMessage mensagem do erro
     */
    public ErrorResponseDTO(final int httpStatus,
                            final String errorType,
                            final String errorMessage) {
        this.timestamp = LocalDateTime.now();
        this.status = httpStatus;
        this.error = errorType;
        this.message = errorMessage;
    }

    // Getters e Setters

    /**
     * Obtém o timestamp do erro.
     *
     * @return o timestamp do erro
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Define o timestamp do erro.
     *
     * @param errorTimestamp o timestamp do erro a ser definido
     */
    public void setTimestamp(final LocalDateTime errorTimestamp) {
        this.timestamp = errorTimestamp;
    }

    /**
     * Obtém o código de status HTTP.
     *
     * @return o código de status HTTP
     */
    public int getStatus() {
        return status;
    }

    /**
     * Define o código de status HTTP.
     *
     * @param httpStatus o código de status HTTP a ser definido
     */
    public void setStatus(final int httpStatus) {
        this.status = httpStatus;
    }

    /**
     * Obtém o tipo do erro.
     *
     * @return o tipo do erro
     */
    public String getError() {
        return error;
    }

    /**
     * Define o tipo do erro.
     *
     * @param errorType o tipo do erro a ser definido
     */
    public void setError(final String errorType) {
        this.error = errorType;
    }

    /**
     * Obtém a mensagem do erro.
     *
     * @return a mensagem do erro
     */
    public String getMessage() {
        return message;
    }

    /**
     * Define a mensagem do erro.
     *
     * @param errorMessage a mensagem do erro a ser definida
     */
    public void setMessage(final String errorMessage) {
        this.message = errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorResponseDTO{"
                + "timestamp=" + timestamp
                + ", status=" + status
                + ", error='" + error + '\''
                + ", message='" + message + '\''
                + '}';
    }
}