package com.viasoft.emailservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO específico para o provedor OCI.
 *
 * Esta classe representa a estrutura de dados necessária
 * para envio de emails através da plataforma Oracle Cloud Infrastructure.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Schema(description = "Dados de email formatados para OCI")
public class EmailOciDTO {

    @Schema(description = "Email do destinatário", example = "destinatario@exemplo.com")
    @NotBlank(message = "RecipientEmail é obrigatório")
    @Email(message = "RecipientEmail deve ter formato válido")
    @Size(max = 40, message = "RecipientEmail deve ter no máximo 40 caracteres")
    private String recipientEmail;

    @Schema(description = "Nome do destinatário", example = "João Silva")
    @NotBlank(message = "RecipientName é obrigatório")
    @Size(max = 50, message = "RecipientName deve ter no máximo 50 caracteres")
    private String recipientName;

    @Schema(description = "Email do remetente", example = "remetente@exemplo.com")
    @NotBlank(message = "SenderEmail é obrigatório")
    @Email(message = "SenderEmail deve ter formato válido")
    @Size(max = 40, message = "SenderEmail deve ter no máximo 40 caracteres")
    private String senderEmail;

    @Schema(description = "Assunto do email", example = "Bem-vindo ao nosso sistema")
    @NotBlank(message = "Subject é obrigatório")
    @Size(max = 100, message = "Subject deve ter no máximo 100 caracteres")
    private String subject;

    @Schema(description = "Conteúdo do email", example = "Olá! Seja bem-vindo ao nosso sistema.")
    @NotBlank(message = "Body é obrigatório")
    @Size(max = 250, message = "Body deve ter no máximo 250 caracteres")
    private String body;

    /**
     * Construtor padrão.
     */
    public EmailOciDTO() {
    }

    /**
     * Construtor com todos os parâmetros.
     *
     * @param recipientEmail email do destinatário
     * @param recipientName nome do destinatário
     * @param senderEmail email do remetente
     * @param subject assunto do email
     * @param body conteúdo do email
     */
    public EmailOciDTO(String recipientEmail, String recipientName, String senderEmail,
                       String subject, String body) {
        this.recipientEmail = recipientEmail;
        this.recipientName = recipientName;
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.body = body;
    }

    // Getters e Setters

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "EmailOciDTO{" +
                "recipientEmail='" + recipientEmail + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", senderEmail='" + senderEmail + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}