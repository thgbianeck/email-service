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
public final class EmailOciDTO {

    /** Tamanho máximo para campos de email. */
    private static final int MAX_EMAIL_LENGTH = 40;

    /** Tamanho máximo para o nome do destinatário. */
    private static final int MAX_NAME_LENGTH = 50;

    /** Tamanho máximo para o assunto do email. */
    private static final int MAX_SUBJECT_LENGTH = 100;

    /** Tamanho máximo para o corpo do email. */
    private static final int MAX_BODY_LENGTH = 250;

    /**
     * Email do destinatário.
     * Deve ser um endereço de email válido com no máximo 40 caracteres.
     */
    @Schema(description = "Email do destinatário",
            example = "destinatario@exemplo.com")
    @NotBlank(message = "RecipientEmail é obrigatório")
    @Email(message = "RecipientEmail deve ter formato válido")
    @Size(max = MAX_EMAIL_LENGTH,
            message = "RecipientEmail deve ter no máximo 40 caracteres")
    private String recipientEmail;

    /**
     * Nome do destinatário.
     * Deve ter no máximo 50 caracteres.
     */
    @Schema(description = "Nome do destinatário", example = "João Silva")
    @NotBlank(message = "RecipientName é obrigatório")
    @Size(max = MAX_NAME_LENGTH,
            message = "RecipientName deve ter no máximo 50 caracteres")
    private String recipientName;

    /**
     * Email do remetente.
     * Deve ser um endereço de email válido com no máximo 40 caracteres.
     */
    @Schema(description = "Email do remetente",
            example = "remetente@exemplo.com")
    @NotBlank(message = "SenderEmail é obrigatório")
    @Email(message = "SenderEmail deve ter formato válido")
    @Size(max = MAX_EMAIL_LENGTH,
            message = "SenderEmail deve ter no máximo 40 caracteres")
    private String senderEmail;

    /**
     * Assunto do email.
     * Deve ter no máximo 100 caracteres.
     */
    @Schema(description = "Assunto do email",
            example = "Bem-vindo ao nosso sistema")
    @NotBlank(message = "Subject é obrigatório")
    @Size(max = MAX_SUBJECT_LENGTH,
            message = "Subject deve ter no máximo 100 caracteres")
    private String subject;

    /**
     * Conteúdo do email.
     * Deve ter no máximo 250 caracteres.
     */
    @Schema(description = "Conteúdo do email",
            example = "Olá! Seja bem-vindo ao nosso sistema.")
    @NotBlank(message = "Body é obrigatório")
    @Size(max = MAX_BODY_LENGTH,
            message = "Body deve ter no máximo 250 caracteres")
    private String body;

    /**
     * Construtor padrão.
     */
    public EmailOciDTO() {
    }

    /**
     * Construtor com todos os parâmetros.
     *
     * @param recipientEmailParam email do destinatário
     * @param recipientNameParam nome do destinatário
     * @param senderEmailParam email do remetente
     * @param subjectParam assunto do email
     * @param bodyParam conteúdo do email
     */
    public EmailOciDTO(final String recipientEmailParam,
                       final String recipientNameParam,
                       final String senderEmailParam,
                       final String subjectParam,
                       final String bodyParam) {
        this.recipientEmail = recipientEmailParam;
        this.recipientName = recipientNameParam;
        this.senderEmail = senderEmailParam;
        this.subject = subjectParam;
        this.body = bodyParam;
    }

    // Getters e Setters

    /**
     * Retorna o email do destinatário.
     *
     * @return o email do destinatário
     */
    public String getRecipientEmail() {
        return recipientEmail;
    }

    /**
     * Define o email do destinatário.
     *
     * @param recipientEmailParam o email do destinatário a ser definido
     */
    public void setRecipientEmail(final String recipientEmailParam) {
        this.recipientEmail = recipientEmailParam;
    }

    /**
     * Retorna o nome do destinatário.
     *
     * @return o nome do destinatário
     */
    public String getRecipientName() {
        return recipientName;
    }

    /**
     * Define o nome do destinatário.
     *
     * @param recipientNameParam o nome do destinatário a ser definido
     */
    public void setRecipientName(final String recipientNameParam) {
        this.recipientName = recipientNameParam;
    }

    /**
     * Retorna o email do remetente.
     *
     * @return o email do remetente
     */
    public String getSenderEmail() {
        return senderEmail;
    }

    /**
     * Define o email do remetente.
     *
     * @param senderEmailParam o email do remetente a ser definido
     */
    public void setSenderEmail(final String senderEmailParam) {
        this.senderEmail = senderEmailParam;
    }

    /**
     * Retorna o assunto do email.
     *
     * @return o assunto do email
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Define o assunto do email.
     *
     * @param subjectParam o assunto do email a ser definido
     */
    public void setSubject(final String subjectParam) {
        this.subject = subjectParam;
    }

    /**
     * Retorna o corpo do email.
     *
     * @return o corpo do email
     */
    public String getBody() {
        return body;
    }

    /**
     * Define o corpo do email.
     *
     * @param bodyParam o corpo do email a ser definido
     */
    public void setBody(final String bodyParam) {
        this.body = bodyParam;
    }

    @Override
    public String toString() {
        return "EmailOciDTO{"
                + "recipientEmail='" + recipientEmail + '\''
                + ", recipientName='" + recipientName + '\''
                + ", senderEmail='" + senderEmail + '\''
                + ", subject='" + subject + '\''
                + ", body='" + body + '\''
                + '}';
    }
}
