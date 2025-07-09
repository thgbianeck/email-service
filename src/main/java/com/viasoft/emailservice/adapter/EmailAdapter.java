package com.viasoft.emailservice.adapter;

import com.viasoft.emailservice.dto.EmailRequestDTO;

/**
 * Interface para adaptação de dados de email.
 *
 * Define o contrato para adaptadores que convertem
 * dados de email do formato padrão para formatos
 * específicos de diferentes provedores.
 *
 * Aplica o princípio da Inversão de Dependência (DIP) do SOLID.
 *
 * @param <T> tipo do DTO de destino
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
public interface EmailAdapter<T> {

    /**
     * Adapta os dados de email do formato padrão para o formato específico
     * do provedor.
     *
     * @param emailRequest dados de email no formato padrão
     * @return dados adaptados para o provedor específico
     * @throws IllegalArgumentException se os dados de entrada forem inválidos
     */
    T adapt(EmailRequestDTO emailRequest);

    /**
     * Retorna o tipo de provedor suportado por este adaptador.
     *
     * @return nome do provedor
     */
    String getProviderType();
}
