package com.viasoft.emailservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO específico para o provedor AWS.
 * Esta classe representa a estrutura de dados necessária
 * para envio de emails através da plataforma Amazon Web Services.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Schema(description = "Dados de email formatados para AWS")
public final class EmailAwsDTO {

    /** Tamanho máximo para campos de email (45 caracteres). */
    private static final int MAX_EMAIL_LENGTH = 45;

    /** Tamanho máximo para nome do destinatário (60 caracteres). */
    private static final int MAX_RECIPIENT_NAME_LENGTH = 60;

    /** Tamanho máximo para assunto do email (120 caracteres). */
    private static final int MAX_SUBJECT_LENGTH = 120;

    /** Tamanho máximo para conteúdo do email (256 caracteres). */
    private static final int MAX_CONTENT_LENGTH = 256;

    /**
     * Email do destinatário.
     * Campo obrigatório que deve conter um endereço de email válido.
     */
    @Schema(description = "Email do destinatário",
            example = "destinatario@exemplo.com")
    @NotBlank(message = "Recipient é obrigatório")
    @Email(message = "Recipient deve ter formato válido")
    @Size(max = MAX_EMAIL_LENGTH,
            message = "Recipient deve ter no máximo 45 caracteres")
    private String recipient;

    /**
     * Nome do destinatário.
     * Campo obrigatório que identifica o nome da pessoa que receberá o email.
     */
    @Schema(description = "Nome do destinatário", example = "João Silva")
    @NotBlank(message = "RecipientName é obrigatório")
    @Size(max = MAX_RECIPIENT_NAME_LENGTH,
            message = "RecipientName deve ter no máximo 60 caracteres")
    private String recipientName;

    /**
     * Email do remetente.
     * Campo obrigatório que deve conter um endereço de email válido.
     */
    @Schema(description = "Email do remetente",
            example = "remetente@exemplo.com")
    @NotBlank(message = "Sender é obrigatório")
    @Email(message = "Sender deve ter formato válido")
    @Size(max = MAX_EMAIL_LENGTH,
            message = "Sender deve ter no máximo 45 caracteres")
    private String sender;

    /**
     * Assunto do email.
     * Campo obrigatório que define o título/assunto da mensagem.
     */
    @Schema(description = "Assunto do email",
            example = "Bem-vindo ao nosso sistema")
    @NotBlank(message = "Subject é obrigatório")
    @Size(max = MAX_SUBJECT_LENGTH,
            message = "Subject deve ter no máximo 120 caracteres")
    private String subject;

    /**
     * Conteúdo do email.
     * Campo obrigatório que contém o corpo da mensagem.
     */
    @Schema(description = "Conteúdo do email",
            example = "Olá! Seja bem-vindo ao nosso sistema.")
    @NotBlank(message = "Content é obrigatório")
    @Size(max = MAX_CONTENT_LENGTH,
            message = "Content deve ter no máximo 256 caracteres")
    private String content;

    /**
     * Construtor padrão.
     */
    public EmailAwsDTO() {
    }

    /**
     * Construtor com todos os parâmetros.
     *
     * @param recipientEmail email do destinatário
     * @param recipientDisplayName nome do destinatário
     * @param senderEmail email do remetente
     * @param emailSubject assunto do email
     * @param emailContent conteúdo do email
     */
    public EmailAwsDTO(final String recipientEmail,
                       final String recipientDisplayName,
                       final String senderEmail,
                       final String emailSubject,
                       final String emailContent) {
        this.recipient = recipientEmail;
        this.recipientName = recipientDisplayName;
        this.sender = senderEmail;
        this.subject = emailSubject;
        this.content = emailContent;
    }

    // Getters e Setters

    /**
     * Obtém o email do destinatário.
     *
     * @return o email do destinatário
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Define o email do destinatário.
     *
     * @param recipientEmail o email do destinatário a ser definido
     */
    public void setRecipient(final String recipientEmail) {
        this.recipient = recipientEmail;
    }

    /**
     * Obtém o nome do destinatário.
     *
     * @return o nome do destinatário
     */
    public String getRecipientName() {
        return recipientName;
    }

    /**
     * Define o nome do destinatário.
     *
     * @param recipientDisplayName o nome do destinatário a ser definido
     */
    public void setRecipientName(final String recipientDisplayName) {
        this.recipientName = recipientDisplayName;
    }

    /**
     * Obtém o email do remetente.
     *
     * @return o email do remetente
     */
    public String getSender() {
        return sender;
    }

    /**
     * Define o email do remetente.
     *
     * @param senderEmail o email do remetente a ser definido
     */
    public void setSender(final String senderEmail) {
        this.sender = senderEmail;
    }

    /**
     * Obtém o assunto do email.
     *
     * @return o assunto do email
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Define o assunto do email.
     *
     * @param emailSubject o assunto do email a ser definido
     */
    public void setSubject(final String emailSubject) {
        this.subject = emailSubject;
    }

    /**
     * Obtém o conteúdo do email.
     *
     * @return o conteúdo do email
     */
    public String getContent() {
        return content;
    }

    /**
     * Define o conteúdo do email.
     *
     * @param emailContent o conteúdo do email a ser definido
     */
    public void setContent(final String emailContent) {
        this.content = emailContent;
    }

    @Override
    public String toString() {
        return "EmailAwsDTO{"
                + "recipient='" + recipient + '\''
                + ", recipientName='" + recipientName + '\''
                + ", sender='" + sender + '\''
                + ", subject='" + subject + '\''
                + ", content='" + content + '\''
                + '}';
    }
}
