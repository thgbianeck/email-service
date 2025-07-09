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
 * - Single Responsibility Principle (SRP): responsável apenas pela adaptação AWS
 * - Open/Closed Principle (OCP): aberto para extensão, fechado para modificação
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Component
public class AwsEmailAdapter implements EmailAdapter<EmailAwsDTO> {

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
    public EmailAwsDTO adapt(EmailRequestDTO emailRequest) {
        if (emailRequest == null) {
            throw new IllegalArgumentException("EmailRequestDTO não pode ser nulo");
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
    private void validateAwsLimits(EmailRequestDTO emailRequest) {
        if (emailRequest.getEmailDestinatario() != null &&
                emailRequest.getEmailDestinatario().length() > 45) {
            throw new IllegalArgumentException(
                    "Email do destinatário excede o limite de 45 caracteres para AWS");
        }

        if (emailRequest.getNomeDestinatario() != null &&
                emailRequest.getNomeDestinatario().length() > 60) {
            throw new IllegalArgumentException(
                    "Nome do destinatário excede o limite de 60 caracteres para AWS");
        }

        if (emailRequest.getEmailRemetente() != null &&
                emailRequest.getEmailRemetente().length() > 45) {
            throw new IllegalArgumentException(
                    "Email do remetente excede o limite de 45 caracteres para AWS");
        }

        if (emailRequest.getAssunto() != null &&
                emailRequest.getAssunto().length() > 120) {
            throw new IllegalArgumentException(
                    "Assunto excede o limite de 120 caracteres para AWS");
        }

        if (emailRequest.getConteudo() != null &&
                emailRequest.getConteudo().length() > 256) {
            throw new IllegalArgumentException(
                    "Conteúdo excede o limite de 256 caracteres para AWS");
        }
    }
}
