package com.viasoft.emailservice.exception;

/**
 * Exceção para erros de processamento de email.
 *
 * Esta exceção é lançada quando ocorrem erros durante
 * o processamento de emails, como problemas de adaptação
 * ou serialização.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
public class EmailProcessingException extends RuntimeException {

    /**
     * Construtor com mensagem.
     *
     * @param message mensagem de erro
     */
    public EmailProcessingException(String message) {
        super(message);
    }

    /**
     * Construtor com mensagem e causa.
     *
     * @param message mensagem de erro
     * @param cause causa da exceção
     */
    public EmailProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}