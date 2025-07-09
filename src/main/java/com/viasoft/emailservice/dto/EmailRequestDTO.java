package com.viasoft.emailservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para recebimento de dados de email na requisição.
 * Esta classe representa o objeto de entrada padrão que será
 * adaptado para os diferentes provedores de email.
 *
 * <p>Esta classe é final para garantir que não seja estendida,
 * mantendo a integridade do contrato de dados do DTO.</p>
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Schema(description = "Dados de email para envio")
public final class EmailRequestDTO {

    /** Tamanho máximo permitido para campos de email. */
    public static final int MAX_EMAIL_LENGTH = 45;

    /** Tamanho máximo permitido para o nome do destinatário. */
    public static final int MAX_NAME_LENGTH = 60;

    /** Tamanho máximo permitido para o assunto do email. */
    public static final int MAX_SUBJECT_LENGTH = 120;

    /** Tamanho máximo permitido para o conteúdo do email. */
    public static final int MAX_CONTENT_LENGTH = 256;

    /**
     * Email do destinatário que receberá a mensagem.
     */
    @Schema(description = "Email do destinatário",
            example = "destinatario@exemplo.com")
    @NotBlank(message = "Email do destinatário é obrigatório")
    @Email(message = "Email do destinatário deve ter formato válido")
    @Size(max = MAX_EMAIL_LENGTH,
            message = "Email do destinatário deve ter no máximo "
                    + MAX_EMAIL_LENGTH + " caracteres")
    private String emailDestinatario;

    /**
     * Nome completo do destinatário do email.
     */
    @Schema(description = "Nome do destinatário", example = "João Silva")
    @NotBlank(message = "Nome do destinatário é obrigatório")
    @Size(max = MAX_NAME_LENGTH,
            message = "Nome do destinatário deve ter no máximo "
                    + MAX_NAME_LENGTH + " caracteres")
    private String nomeDestinatario;

    /**
     * Email do remetente que está enviando a mensagem.
     */
    @Schema(description = "Email do remetente",
            example = "remetente@exemplo.com")
    @NotBlank(message = "Email do remetente é obrigatório")
    @Email(message = "Email do remetente deve ter formato válido")
    @Size(max = MAX_EMAIL_LENGTH,
            message = "Email do remetente deve ter no máximo "
                    + MAX_EMAIL_LENGTH + " caracteres")
    private String emailRemetente;

    /**
     * Assunto/título do email a ser enviado.
     */
    @Schema(description = "Assunto do email",
            example = "Bem-vindo ao nosso sistema")
    @NotBlank(message = "Assunto é obrigatório")
    @Size(max = MAX_SUBJECT_LENGTH,
            message = "Assunto deve ter no máximo "
                    + MAX_SUBJECT_LENGTH + " caracteres")
    private String assunto;

    /**
     * Conteúdo/corpo da mensagem do email.
     */
    @Schema(description = "Conteúdo do email",
            example = "Olá! Seja bem-vindo ao nosso sistema.")
    @NotBlank(message = "Conteúdo é obrigatório")
    @Size(max = MAX_CONTENT_LENGTH,
            message = "Conteúdo deve ter no máximo "
                    + MAX_CONTENT_LENGTH + " caracteres")
    private String conteudo;

    /**
     * Construtor padrão.
     */
    public EmailRequestDTO() {
    }

    /**
     * Construtor com todos os parâmetros.
     *
     * @param destinatarioEmail email do destinatário
     * @param destinatarioNome nome do destinatário
     * @param remetenteEmail email do remetente
     * @param emailAssunto assunto do email
     * @param emailConteudo conteúdo do email
     */
    public EmailRequestDTO(final String destinatarioEmail,
                           final String destinatarioNome,
                           final String remetenteEmail,
                           final String emailAssunto,
                           final String emailConteudo) {
        this.emailDestinatario = destinatarioEmail;
        this.nomeDestinatario = destinatarioNome;
        this.emailRemetente = remetenteEmail;
        this.assunto = emailAssunto;
        this.conteudo = emailConteudo;
    }

    // Getters e Setters

    /**
     * Obtém o email do destinatário.
     *
     * @return o email do destinatário
     */
    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    /**
     * Define o email do destinatário.
     *
     * @param destinatarioEmail o email do destinatário a ser definido
     */
    public void setEmailDestinatario(final String destinatarioEmail) {
        this.emailDestinatario = destinatarioEmail;
    }

    /**
     * Obtém o nome do destinatário.
     *
     * @return o nome do destinatário
     */
    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    /**
     * Define o nome do destinatário.
     *
     * @param destinatarioNome o nome do destinatário a ser definido
     */
    public void setNomeDestinatario(final String destinatarioNome) {
        this.nomeDestinatario = destinatarioNome;
    }

    /**
     * Obtém o email do remetente.
     *
     * @return o email do remetente
     */
    public String getEmailRemetente() {
        return emailRemetente;
    }

    /**
     * Define o email do remetente.
     *
     * @param remetenteEmail o email do remetente a ser definido
     */
    public void setEmailRemetente(final String remetenteEmail) {
        this.emailRemetente = remetenteEmail;
    }

    /**
     * Obtém o assunto do email.
     *
     * @return o assunto do email
     */
    public String getAssunto() {
        return assunto;
    }

    /**
     * Define o assunto do email.
     *
     * @param emailAssunto o assunto do email a ser definido
     */
    public void setAssunto(final String emailAssunto) {
        this.assunto = emailAssunto;
    }

    /**
     * Obtém o conteúdo do email.
     *
     * @return o conteúdo do email
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * Define o conteúdo do email.
     *
     * @param emailConteudo o conteúdo do email a ser definido
     */
    public void setConteudo(final String emailConteudo) {
        this.conteudo = emailConteudo;
    }

    @Override
    public String toString() {
        return "EmailRequestDTO{"
                + "emailDestinatario='" + emailDestinatario + '\''
                + ", nomeDestinatario='" + nomeDestinatario + '\''
                + ", emailRemetente='" + emailRemetente + '\''
                + ", assunto='" + assunto + '\''
                + ", conteudo='" + conteudo + '\''
                + '}';
    }
}
