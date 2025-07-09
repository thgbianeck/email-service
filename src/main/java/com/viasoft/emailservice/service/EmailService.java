package com.viasoft.emailservice.service;

import com.viasoft.emailservice.dto.EmailRequestDTO;

/**
 * Interface do serviço de email.
 *
 * Define o contrato para processamento de emails,
 * aplicando o princípio da Inversão de Dependência (DIP).
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
public interface EmailService {

    /**
     * Processa uma requisição de email.
     *
     * Este método é responsável por:
     * - Adaptar os dados para o provedor configurado
     * - Serializar o objeto adaptado
     * - Imprimir o resultado no console
     *
     * @param emailRequest dados da requisição de email
     * @throws com.viasoft.emailservice.exception.EmailProcessingException se ocorrer erro no processamento
     * @throws com.viasoft.emailservice.exception.InvalidEmailDataException se os dados forem inválidos
     */
    void processEmail(EmailRequestDTO emailRequest);
}