package com.viasoft.emailservice.adapter.impl;

import com.viasoft.emailservice.adapter.EmailAdapter;
import com.viasoft.emailservice.dto.EmailOciDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import com.viasoft.emailservice.enums.EmailProvider;
import org.springframework.stereotype.Component;

/**
 * Adaptador para conversão de dados de email para o formato OCI.
 *
 * Esta classe implementa o padrão Adapter para converter
 * dados do formato padrão para o formato específico da OCI.
 *
 * Aplica os princípios:
 * - Single Responsibility Principle (SRP): responsável apenas pela
 *   adaptação OCI
 * - Open/Closed Principle (OCP): aberto para extensão, fechado para
 *   modificação
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Component
public class OciEmailAdapter implements EmailAdapter<EmailOciDTO> {

    /**
     * Limite máximo de caracteres para endereços de email na OCI.
     */
    private static final int MAX_EMAIL_LENGTH = 40;

    /**
     * Limite máximo de caracteres para nomes de destinatário na OCI.
     */
    private static final int MAX_NAME_LENGTH = 50;

    /**
     * Limite máximo de caracteres para assunto do email na OCI.
     */
    private static final int MAX_SUBJECT_LENGTH = 100;

    /**
     * Limite máximo de caracteres para conteúdo do email na OCI.
     */
    private static final int MAX_CONTENT_LENGTH = 250;

    /**
     * Adapta os dados de email para o formato OCI.
     *
     * Realiza a conversão dos campos do formato padrão para
     * os campos específicos esperados pela OCI, aplicando
     * as validações de tamanho necessárias.
     *
     * @param emailRequest dados de email no formato padrão
     * @return dados adaptados para OCI
     * @throws IllegalArgumentException se os dados excederem os limites da OCI
     */
    @Override
    public EmailOciDTO adapt(final EmailRequestDTO emailRequest) {
        if (emailRequest == null) {
            throw new IllegalArgumentException(
                    "EmailRequestDTO não pode ser nulo");
        }

        // Validações específicas para OCI
        validateOciLimits(emailRequest);

        return new EmailOciDTO(
                emailRequest.getEmailDestinatario(),
                emailRequest.getNomeDestinatario(),
                emailRequest.getEmailRemetente(),
                emailRequest.getAssunto(),
                emailRequest.getConteudo()
        );
    }

    /**
     * Retorna o tipo de provedor suportado.
     *
     * @return nome do provedor OCI
     */
    @Override
    public String getProviderType() {
        return EmailProvider.OCI.getValue();
    }

    /**
     * Valida se os dados atendem aos limites específicos da OCI.
     *
     * @param emailRequest dados a serem validados
     * @throws IllegalArgumentException se algum limite for excedido
     */
    private void validateOciLimits(final EmailRequestDTO emailRequest) {
        if (emailRequest.getEmailDestinatario() != null
                && emailRequest.getEmailDestinatario().length()
                > MAX_EMAIL_LENGTH) {
            throw new IllegalArgumentException(
                    "Email do destinatário excede o limite de "
                            + MAX_EMAIL_LENGTH
                            + " caracteres para OCI");
        }

        if (emailRequest.getNomeDestinatario() != null
                && emailRequest.getNomeDestinatario().length()
                > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "Nome do destinatário excede o limite de "
                            + MAX_NAME_LENGTH
                            + " caracteres para OCI");
        }

        if (emailRequest.getEmailRemetente() != null
                && emailRequest.getEmailRemetente().length()
                > MAX_EMAIL_LENGTH) {
            throw new IllegalArgumentException(
                    "Email do remetente excede o limite de "
                            + MAX_EMAIL_LENGTH
                            + " caracteres para OCI");
        }

        if (emailRequest.getAssunto() != null
                && emailRequest.getAssunto().length() > MAX_SUBJECT_LENGTH) {
            throw new IllegalArgumentException(
                    "Assunto excede o limite de " + MAX_SUBJECT_LENGTH
                            + " caracteres para OCI");
        }

        if (emailRequest.getConteudo() != null
                && emailRequest.getConteudo().length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException(
                    "Conteúdo excede o limite de " + MAX_CONTENT_LENGTH
                            + " caracteres para OCI");
        }
    }
}
