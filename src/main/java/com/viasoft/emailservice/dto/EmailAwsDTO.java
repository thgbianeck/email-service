package com.viasoft.emailservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO específico para o provedor AWS.
 *
 * Esta classe representa a estrutura de dados necessária
 * para envio de emails através da plataforma Amazon Web Services.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Schema(description = "Dados de email formatados para AWS")
public class EmailAwsDTO {

    @Schema(description = "Email do destinatário", example = "destinatario@exemplo.com")
    @NotBlank(message = "Recipient é obrigatório")
    @Email(message = "Recipient deve ter formato válido")
    @Size(max = 45, message = "Recipient deve ter no máximo 45 caracteres")
    private String recipient;

    @Schema(description = "Nome do destinatário", example = "João Silva")
    @NotBlank(message = "RecipientName é obrigatório")
    @Size(max = 60, message = "RecipientName deve ter no máximo 60 caracteres")
    private String recipientName;

    @Schema(description = "Email do remetente", example = "remetente@exemplo.com")
    @NotBlank(message = "Sender é obrigatório")
    @Email(message = "Sender deve ter formato válido")
    @Size(max = 45, message = "Sender deve ter no máximo 45 caracteres")
    private String sender;

    @Schema(description = "Assunto do email", example = "Bem-vindo ao nosso sistema")
    @NotBlank(message = "Subject é obrigatório")
    @Size(max = 120, message = "Subject deve ter no máximo 120 caracteres")
    private String subject;

    @Schema(description = "Conteúdo do email", example = "Olá! Seja bem-vindo ao nosso sistema.")
    @NotBlank(message = "Content é obrigatório")
    @Size(max = 256, message = "Content deve ter no máximo 256 caracteres")
    private String content;

    /**
     * Construtor padrão.
     */
    public EmailAwsDTO() {
    }

    /**
     * Construtor com todos os parâmetros.
     *
     * @param recipient email do destinatário
     * @param recipientName nome do destinatário
     * @param sender email do remetente
     * @param subject assunto do email
     * @param content conteúdo do email
     */
    public EmailAwsDTO(String recipient, String recipientName, String sender,
                       String subject, String content) {
        this.recipient = recipient;
        this.recipientName = recipientName;
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }

    // Getters e Setters

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EmailAwsDTO{" +
                "recipient='" + recipient + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", sender='" + sender + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}