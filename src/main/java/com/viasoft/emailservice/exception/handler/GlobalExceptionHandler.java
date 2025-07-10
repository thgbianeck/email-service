package com.viasoft.emailservice.exception.handler;

import com.viasoft.emailservice.dto.ErrorResponseDTO;
import com.viasoft.emailservice.dto.ValidationErrorResponseDTO;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Manipulador global de exceções.
 *
 * Esta classe centraliza o tratamento de exceções da aplicação,
 * fornecendo respostas padronizadas para diferentes tipos de erros
 * utilizando DTOs específicos.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Logger para registrar eventos e erros da classe.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Trata exceções de validação de dados.
     *
     * @param ex exceção de validação
     * @return resposta com detalhes dos erros de validação
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDTO> handleValidationExceptions(
            final MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ValidationErrorResponseDTO response = new ValidationErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Dados inválidos",
                "Falha na validação dos dados",
                errors
        );

        LOGGER.warn("Erro de validação: {}", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções de dados de email inválidos.
     *
     * @param ex exceção de dados inválidos
     * @return resposta com detalhes do erro
     */
    @ExceptionHandler(InvalidEmailDataException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidEmailDataException(
            final InvalidEmailDataException ex) {

        ErrorResponseDTO response = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Dados de email inválidos",
                ex.getMessage()
        );

        LOGGER.warn("Dados de email inválidos: {}", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções de processamento de email.
     *
     * @param ex exceção de processamento
     * @return resposta com detalhes do erro
     */
    @ExceptionHandler(EmailProcessingException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailProcessingException(
            final EmailProcessingException ex) {

        ErrorResponseDTO response = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no processamento do email",
                ex.getMessage()
        );

        LOGGER.error("Erro no processamento do email: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Trata exceções de argumentos ilegais.
     *
     * @param ex exceção de argumento ilegal
     * @return resposta com detalhes do erro
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(
            final IllegalArgumentException ex) {

        ErrorResponseDTO response = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Argumento inválido",
                ex.getMessage()
        );

        LOGGER.warn("Argumento inválido: {}", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções genéricas não capturadas.
     *
     * @param ex exceção genérica
     * @return resposta com erro interno do servidor
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(
            final Exception ex) {

        ErrorResponseDTO response = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor",
                "Ocorreu um erro inesperado"
        );

        LOGGER.error("Erro inesperado: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}