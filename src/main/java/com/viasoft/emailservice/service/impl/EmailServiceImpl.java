package com.viasoft.emailservice.service.impl;

import com.viasoft.emailservice.adapter.EmailAdapter;
import com.viasoft.emailservice.adapter.factory.EmailAdapterFactory;
import com.viasoft.emailservice.config.EmailConfig;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import com.viasoft.emailservice.enums.EmailProvider;
import com.viasoft.emailservice.exception.EmailProcessingException;
import com.viasoft.emailservice.service.EmailService;
import com.viasoft.emailservice.util.JsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço de email.
 * Esta classe coordena o processamento de emails,
 * aplicando os padrões Strategy e Adapter para
 * adaptar dados conforme o provedor configurado.
 * Aplica os princípios:
 * - Single Responsibility Principle (SRP): responsável apenas pelo
 *   processamento de emails
 * - Dependency Inversion Principle (DIP): depende de abstrações
 * - Open/Closed Principle (OCP): aberto para extensão de novos provedores
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Service
public class EmailServiceImpl implements EmailService {

    /**
     * Logger para registrar eventos e erros da classe.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmailServiceImpl.class);

    /**
     * Configuração de email contendo as propriedades do provedor.
     */
    private final EmailConfig emailConfig;

    /**
     * Factory responsável por criar adaptadores de email.
     */
    private final EmailAdapterFactory adapterFactory;

    /**
     * Serializador JSON para conversão de objetos.
     */
    private final JsonSerializer jsonSerializer;

    /**
     * Construtor com injeção de dependências.
     *
     * @param emailConfigParam configuração de email
     * @param adapterFactoryParam factory de adaptadores
     * @param jsonSerializerParam serializador JSON
     */
    @Autowired
    public EmailServiceImpl(final EmailConfig emailConfigParam,
                            final EmailAdapterFactory adapterFactoryParam,
                            final JsonSerializer jsonSerializerParam) {
        this.emailConfig = emailConfigParam;
        this.adapterFactory = adapterFactoryParam;
        this.jsonSerializer = jsonSerializerParam;
    }

    /**
     * Processa uma requisição de email.
     *
     * @param emailRequest dados da requisição de email
     * @throws EmailProcessingException se ocorrer erro no processamento
     */
    @Override
    public void processEmail(final EmailRequestDTO emailRequest) {
        try {
            LOGGER.info("Iniciando processamento de email para: {}",
                    emailRequest.getEmailDestinatario());

            // Obter o provedor configurado
            EmailProvider provider = emailConfig.getEmailProvider();
            LOGGER.debug("Provedor configurado: {}", provider.getValue());

            // Criar o adaptador apropriado
            EmailAdapter<?> adapter = adapterFactory.createAdapter(provider);
            LOGGER.debug("Adaptador criado: {}",
                    adapter.getClass().getSimpleName());

            // Adaptar os dados
            Object adaptedEmail = adapter.adapt(emailRequest);
            LOGGER.debug("Dados adaptados com sucesso");

            // Serializar em JSON
            String jsonResult = jsonSerializer.serialize(adaptedEmail);

            // Imprimir no console conforme requisito
            LOGGER.info("=== EMAIL PROCESSADO ===");
            LOGGER.info("Provedor: " + provider.getValue());
            LOGGER.info("JSON Serializado:");
            LOGGER.info(jsonResult);
            LOGGER.info("========================");

            LOGGER.info("Email processado com sucesso para provedor: {}",
                    provider.getValue());

        } catch (IllegalArgumentException e) {
            LOGGER.error("Erro de validação ao processar email: {}",
                    e.getMessage());
            throw e;
        } catch (EmailProcessingException e) {
            LOGGER.error("Erro ao processar email: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("Erro inesperado ao processar email: {}",
                    e.getMessage(), e);
            throw new EmailProcessingException(
                    "Erro inesperado durante o processamento do email", e
            );
        }
    }
}
