package com.viasoft.emailservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para recebimento de dados de email na requisição.
 *
 * Esta classe representa o objeto de entrada padrão que será
 * adaptado para os diferentes provedores de email.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Schema(description = "Dados de email para envio")
public class EmailRequestDTO {

    @Schema(description = "Email do destinatário", example = "destinatario@exemplo.com")
    @NotBlank(message = "Email do destinatário é obrigatório")
    @Email(message = "Email do destinatário deve ter formato válido")
    @Size(max = 45, message = "Email do destinatário deve ter no máximo 45 caracteres")
    private String emailDestinatario;

    @Schema(description = "Nome do destinatário", example = "João Silva")
    @NotBlank(message = "Nome do destinatário é obrigatório")
    @Size(max = 60, message = "Nome do destinatário deve ter no máximo 60 caracteres")
    private String nomeDestinatario;

    @Schema(description = "Email do remetente", example = "remetente@exemplo.com")
    @NotBlank(message = "Email do remetente é obrigatório")
    @Email(message = "Email do remetente deve ter formato válido")
    @Size(max = 45, message = "Email do remetente deve ter no máximo 45 caracteres")
    private String emailRemetente;

    @Schema(description = "Assunto do email", example = "Bem-vindo ao nosso sistema")
    @NotBlank(message = "Assunto é obrigatório")
    @Size(max = 120, message = "Assunto deve ter no máximo 120 caracteres")
    private String assunto;

    @Schema(description = "Conteúdo do email", example = "Olá! Seja bem-vindo ao nosso sistema.")
    @NotBlank(message = "Conteúdo é obrigatório")
    @Size(max = 256, message = "Conteúdo deve ter no máximo 256 caracteres")
    private String conteudo;

    /**
     * Construtor padrão.
     */
    public EmailRequestDTO() {
    }

    /**
     * Construtor com todos os parâmetros.
     *
     * @param emailDestinatario email do destinatário
     * @param nomeDestinatario nome do destinatário
     * @param emailRemetente email do remetente
     * @param assunto assunto do email
     * @param conteudo conteúdo do email
     */
    public EmailRequestDTO(String emailDestinatario, String nomeDestinatario,
                           String emailRemetente, String assunto, String conteudo) {
        this.emailDestinatario = emailDestinatario;
        this.nomeDestinatario = nomeDestinatario;
        this.emailRemetente = emailRemetente;
        this.assunto = assunto;
        this.conteudo = conteudo;
    }

    // Getters e Setters

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "EmailRequestDTO{" +
                "emailDestinatario='" + emailDestinatario + '\'' +
                ", nomeDestinatario='" + nomeDestinatario + '\'' +
                ", emailRemetente='" + emailRemetente + '\'' +
                ", assunto='" + assunto + '\'' +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }
}