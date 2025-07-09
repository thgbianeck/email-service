package com.viasoft.emailservice.controller;

import com.viasoft.emailservice.dto.EmailRequestDTO;
import com.viasoft.emailservice.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST para operações de email.
 * Esta classe expõe endpoints REST para processamento
 * de emails, seguindo as melhores práticas de APIs RESTful.
 * Aplica os princípios:
 * - Single Responsibility Principle (SRP): responsável apenas pela camada de apresentação
 * - Dependency Inversion Principle (DIP): depende da abstração do serviço
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@RestController
@RequestMapping("/emails")
@Tag(name = "Email", description = "API para processamento de emails")
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    private final EmailService emailService;

    /**
     * Construtor com injeção de dependência.
     *
     * @param emailService serviço de email
     */
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Endpoint para processamento de email.
     * Recebe dados de email, adapta conforme o provedor configurado,
     * serializa em JSON e imprime no console.
     *
     * @param emailRequest dados do email a ser processado
     * @return resposta HTTP 204 (No Content) em caso de sucesso
     */
    @PostMapping("/send")
    @Operation(
            summary = "Processar email",
            description = "Processa dados de email adaptando para o provedor configurado (AWS/OCI)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Email processado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)
                    )
            )
    })
    public ResponseEntity<Void> sendEmail(
            @Parameter(description = "Dados do email a ser enviado", required = true)
            @Valid @RequestBody EmailRequestDTO emailRequest) {

        logger.info("Recebida requisição de envio de email para: {}",
                emailRequest.getEmailDestinatario());

        // Processar o email através do serviço
        emailService.processEmail(emailRequest);

        logger.info("Email processado com sucesso");

        // Retornar status 204 conforme especificação
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para verificação de saúde da API.
     *
     * @return status da aplicação
     */
    @GetMapping("/health")
    @Operation(
            summary = "Verificar saúde da API",
            description = "Endpoint para verificação do status da aplicação"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Aplicação funcionando corretamente"
    )
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Email Service está funcionando!");
    }
}