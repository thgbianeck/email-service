package com.viasoft.emailservice.exception.handler;

import com.viasoft.emailservice.exception.EmailProcessingException;
import com.viasoft.emailservice.exception.InvalidEmailDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manipulador global de exceções.
 *
 * Esta classe centraliza o tratamento de exceções da aplicação,
 * fornecendo respostas padronizadas para diferentes tipos de erros.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Trata exceções de validação de dados.
     *
     * @param ex exceção de validação
     * @return resposta com detalhes dos erros de validação
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Dados inválidos");
        response.put("message", "Falha na validação dos dados");
        response.put("errors", errors);

        logger.warn("Erro de validação: {}", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções de dados de email inválidos.
     *
     * @param ex exceção de dados inválidos
     * @return resposta com detalhes do erro
     */
    @ExceptionHandler(InvalidEmailDataException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidEmailDataException(
            InvalidEmailDataException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Dados de email inválidos");
        response.put("message", ex.getMessage());

        logger.warn("Dados de email inválidos: {}", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções de processamento de email.
     *
     * @param ex exceção de processamento
     * @return resposta com detalhes do erro
     */
    @ExceptionHandler(EmailProcessingException.class)
    public ResponseEntity<Map<String, Object>> handleEmailProcessingException(
            EmailProcessingException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Erro no processamento do email");
        response.put("message", ex.getMessage());

        logger.error("Erro no processamento do email: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Trata exceções de argumentos ilegais.
     *
     * @param ex exceção de argumento ilegal
     * @return resposta com detalhes do erro
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Argumento inválido");
        response.put("message", ex.getMessage());

        logger.warn("Argumento inválido: {}", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções genéricas não capturadas.
     *
     * @param ex exceção genérica
     * @return resposta com erro interno do servidor
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Erro interno do servidor");
        response.put("message", "Ocorreu um erro inesperado");

        logger.error("Erro inesperado: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}