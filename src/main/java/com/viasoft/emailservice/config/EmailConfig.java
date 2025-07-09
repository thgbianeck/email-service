package com.viasoft.emailservice.config;

import com.viasoft.emailservice.enums.EmailProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração para o serviço de email.
 *
 * Esta classe centraliza as configurações relacionadas
 * ao provedor de email e outras configurações do sistema.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Configuration
public class EmailConfig {

    @Value("${mail.integracao:AWS}")
    private String mailIntegracao;

    /**
     * Obtém o provedor de email configurado.
     *
     * @return provedor de email configurado
     * @throws IllegalArgumentException se o provedor configurado for inválido
     */
    public EmailProvider getEmailProvider() {
        try {
            return EmailProvider.fromValue(mailIntegracao);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Provedor de email inválido configurado: " + mailIntegracao +
                            ". Valores válidos: AWS, OCI", e
            );
        }
    }

    /**
     * Obtém o valor bruto da configuração de integração.
     *
     * @return valor da configuração mail.integracao
     */
    public String getMailIntegracao() {
        return mailIntegracao;
    }

    /**
     * Define o provedor de email (usado principalmente para testes).
     *
     * @param mailIntegracao provedor a ser configurado
     */
    public void setMailIntegracao(String mailIntegracao) {
        this.mailIntegracao = mailIntegracao;
    }
}