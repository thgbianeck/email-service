package com.viasoft.emailservice.adapter.impl;

import com.viasoft.emailservice.adapter.EmailAdapter;
import com.viasoft.emailservice.dto.EmailAwsDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import com.viasoft.emailservice.enums.EmailProvider;
import org.springframework.stereotype.Component;

/**
 * Adaptador para conversão de dados de email para o formato AWS.
 *
 * Esta classe implementa o padrão Adapter para converter
 * dados do formato padrão para o formato específico da AWS.
 *
 * Aplica os princípios:
 * - Single Responsibility Principle (SRP): responsável apenas pela
 *   adaptação AWS
 * - Open/Closed Principle (OCP): aberto para extensão, fechado para
 *   modificação
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Component
public class AwsEmailAdapter implements EmailAdapter<EmailAwsDTO> {

    /** Limite máximo de caracteres para email do destinatário e remetente. */
    private static final int EMAIL_MAX_LENGTH = 45;

    /** Limite máximo de caracteres para nome do destinatário. */
    private static final int NAME_MAX_LENGTH = 60;

    /** Limite máximo de caracteres para assunto do email. */
    private static final int SUBJECT_MAX_LENGTH = 120;

    /** Limite máximo de caracteres para conteúdo do email. */
    private static final int CONTENT_MAX_LENGTH = 256;

    /**
     * Adapta os dados de email para o formato AWS.
     *
     * Realiza a conversão dos campos do formato padrão para
     * os campos específicos esperados pela AWS, aplicando
     * as validações de tamanho necessárias.
     *
     * @param emailRequest dados de email no formato padrão
     * @return dados adaptados para AWS
     * @throws IllegalArgumentException se os dados excederem os limites da AWS
     */
    @Override
    public EmailAwsDTO adapt(final EmailRequestDTO emailRequest) {
        if (emailRequest == null) {
            throw new IllegalArgumentException(
                    "EmailRequestDTO não pode ser nulo");
        }

        // Validações específicas para AWS
        validateAwsLimits(emailRequest);

        return new EmailAwsDTO(
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
     * @return nome do provedor AWS
     */
    @Override
    public String getProviderType() {
        return EmailProvider.AWS.getValue();
    }

    /**
     * Valida se os dados atendem aos limites específicos da AWS.
     *
     * @param emailRequest dados a serem validados
     * @throws IllegalArgumentException se algum limite for excedido
     */
    private void validateAwsLimits(final EmailRequestDTO emailRequest) {
        if (emailRequest.getEmailDestinatario() != null
                && emailRequest.getEmailDestinatario().length()
                > EMAIL_MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "Email do destinatário excede o limite de "
                            + EMAIL_MAX_LENGTH
                            + " caracteres para AWS");
        }

        if (emailRequest.getNomeDestinatario() != null
                && emailRequest.getNomeDestinatario().length()
                > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "Nome do destinatário excede o limite de "
                            + NAME_MAX_LENGTH
                            + " caracteres para AWS");
        }

        if (emailRequest.getEmailRemetente() != null
                && emailRequest.getEmailRemetente().length()
                > EMAIL_MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "Email do remetente excede o limite de "
                            + EMAIL_MAX_LENGTH
                            + " caracteres para AWS");
        }

        if (emailRequest.getAssunto() != null
                && emailRequest.getAssunto().length() > SUBJECT_MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "Assunto excede o limite de " + SUBJECT_MAX_LENGTH
                            + " caracteres para AWS");
        }

        if (emailRequest.getConteudo() != null
                && emailRequest.getConteudo().length() > CONTENT_MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "Conteúdo excede o limite de " + CONTENT_MAX_LENGTH
                            + " caracteres para AWS");
        }
    }
}
