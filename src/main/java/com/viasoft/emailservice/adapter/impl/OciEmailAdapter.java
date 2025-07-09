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
 * - Single Responsibility Principle (SRP): responsável apenas pela adaptação OCI
 * - Open/Closed Principle (OCP): aberto para extensão, fechado para modificação
 *
 * @author Viasoft
 * @version 1.0.0
 * @since 2024
 */
@Component
public class OciEmailAdapter implements EmailAdapter<EmailOciDTO> {

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
    public EmailOciDTO adapt(EmailRequestDTO emailRequest) {
        if (emailRequest == null) {
            throw new IllegalArgumentException("EmailRequestDTO não pode ser nulo");
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
    private void validateOciLimits(EmailRequestDTO emailRequest) {
        if (emailRequest.getEmailDestinatario() != null &&
                emailRequest.getEmailDestinatario().length() > 40) {
            throw new IllegalArgumentException(
                    "Email do destinatário excede o limite de 40 caracteres para OCI");
        }

        if (emailRequest.getNomeDestinatario() != null &&
                emailRequest.getNomeDestinatario().length() > 50) {
            throw new IllegalArgumentException(
                    "Nome do destinatário excede o limite de 50 caracteres para OCI");
        }

        if (emailRequest.getEmailRemetente() != null &&
                emailRequest.getEmailRemetente().length() > 40) {
            throw new IllegalArgumentException(
                    "Email do remetente excede o limite de 40 caracteres para OCI");
        }

        if (emailRequest.getAssunto() != null &&
                emailRequest.getAssunto().length() > 100) {
            throw new IllegalArgumentException(
                    "Assunto excede o limite de 100 caracteres para OCI");
        }

        if (emailRequest.getConteudo() != null &&
                emailRequest.getConteudo().length() > 250) {
            throw new IllegalArgumentException(
                    "Conteúdo excede o limite de 250 caracteres para OCI");
        }
    }
}