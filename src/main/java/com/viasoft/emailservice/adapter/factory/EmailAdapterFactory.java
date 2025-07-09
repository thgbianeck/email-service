package com.viasoft.emailservice.adapter.factory;

import com.viasoft.emailservice.adapter.EmailAdapter;
import com.viasoft.emailservice.enums.EmailProvider;
import com.viasoft.emailservice.exception.EmailProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Factory para criação de adaptadores de email.
 *
 * Esta classe implementa o padrão Factory Method para
 * criar instâncias apropriadas de adaptadores baseado
 * no provedor configurado.
 *
 * Aplica os princípios:
 * - Single Responsibility Principle (SRP): responsável apenas pela
 *   criação de adaptadores
 * - Dependency Inversion Principle (DIP): depende de abstrações,
 *   não de implementações
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
@Component
public class EmailAdapterFactory {

    /**
     * Mapa de adaptadores indexados por tipo de provedor.
     */
    private final Map<String, EmailAdapter<?>> adapters;

    /**
     * Construtor que injeta todos os adaptadores disponíveis.
     *
     * @param adapterList lista de adaptadores implementados
     */
    @Autowired
    public EmailAdapterFactory(final List<EmailAdapter<?>> adapterList) {
        this.adapters = adapterList.stream()
                .collect(Collectors.toMap(
                        EmailAdapter::getProviderType,
                        Function.identity()
                ));
    }

    /**
     * Cria um adaptador baseado no provedor especificado.
     *
     * @param provider provedor de email
     * @return adaptador apropriado para o provedor
     * @throws EmailProcessingException se o provedor não for suportado
     */
    public EmailAdapter<?> createAdapter(final EmailProvider provider) {
        EmailAdapter<?> adapter = adapters.get(provider.getValue());

        if (adapter == null) {
            throw new EmailProcessingException(
                    "Adaptador não encontrado para o provedor: "
                            + provider.getValue()
            );
        }

        return adapter;
    }

    /**
     * Verifica se um provedor é suportado.
     *
     * @param provider provedor a ser verificado
     * @return true se o provedor for suportado, false caso contrário
     */
    public boolean isProviderSupported(final EmailProvider provider) {
        return adapters.containsKey(provider.getValue());
    }

    /**
     * Retorna a lista de provedores suportados.
     *
     * @return conjunto de provedores suportados
     */
    public java.util.Set<String> getSupportedProviders() {
        return adapters.keySet();
    }
}
