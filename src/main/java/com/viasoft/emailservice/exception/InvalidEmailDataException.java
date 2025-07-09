package com.viasoft.emailservice.exception;

/**
 * Exceção para dados de email inválidos.
 *
 * Esta exceção é lançada quando os dados de email
 * não atendem aos critérios de validação.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
public class InvalidEmailDataException extends RuntimeException {

    /**
     * Construtor com mensagem.
     *
     * @param message mensagem de erro
     */
    public InvalidEmailDataException(final String message) {
        super(message);
    }

    /**
     * Construtor com mensagem e causa.
     *
     * @param message mensagem de erro
     * @param cause causa da exceção
     */
    public InvalidEmailDataException(final String message,
                                     final Throwable cause) {
        super(message, cause);
    }
}
