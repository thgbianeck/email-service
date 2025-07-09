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
 * - Single Responsibility Principle (SRP): responsável apenas pelo processamento de emails
 * - Dependency Inversion Principle (DIP): depende de abstrações
 * - Open/Closed Principle (OCP): aberto para extensão de novos provedores
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final EmailConfig emailConfig;
    private final EmailAdapterFactory adapterFactory;
    private final JsonSerializer jsonSerializer;

    /**
     * Construtor com injeção de dependências.
     *
     * @param emailConfig configuração de email
     * @param adapterFactory factory de adaptadores
     * @param jsonSerializer serializador JSON
     */
    @Autowired
    public EmailServiceImpl(EmailConfig emailConfig,
                            EmailAdapterFactory adapterFactory,
                            JsonSerializer jsonSerializer) {
        this.emailConfig = emailConfig;
        this.adapterFactory = adapterFactory;
        this.jsonSerializer = jsonSerializer;
    }

    /**
     * Processa uma requisição de email.
     *
     * @param emailRequest dados da requisição de email
     * @throws EmailProcessingException se ocorrer erro no processamento
     */
    @Override
    public void processEmail(EmailRequestDTO emailRequest) {
        try {
            logger.info("Iniciando processamento de email para: {}",
                    emailRequest.getEmailDestinatario());

            // Obter o provedor configurado
            EmailProvider provider = emailConfig.getEmailProvider();
            logger.debug("Provedor configurado: {}", provider.getValue());

            // Criar o adaptador apropriado
            EmailAdapter<?> adapter = adapterFactory.createAdapter(provider);
            logger.debug("Adaptador criado: {}", adapter.getClass().getSimpleName());

            // Adaptar os dados
            Object adaptedEmail = adapter.adapt(emailRequest);
            logger.debug("Dados adaptados com sucesso");

            // Serializar em JSON
            String jsonResult = jsonSerializer.serialize(adaptedEmail);

            // Imprimir no console conforme requisito
            System.out.println("=== EMAIL PROCESSADO ===");
            System.out.println("Provedor: " + provider.getValue());
            System.out.println("JSON Serializado:");
            System.out.println(jsonResult);
            System.out.println("========================");

            logger.info("Email processado com sucesso para provedor: {}", provider.getValue());

        } catch (IllegalArgumentException e) {
            logger.error("Erro de validação ao processar email: {}", e.getMessage());
            throw e;
        } catch (EmailProcessingException e) {
            logger.error("Erro ao processar email: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Erro inesperado ao processar email: {}", e.getMessage(), e);
            throw new EmailProcessingException(
                    "Erro inesperado durante o processamento do email", e
            );
        }
    }
}