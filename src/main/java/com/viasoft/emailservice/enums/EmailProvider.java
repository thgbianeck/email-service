package com.viasoft.emailservice.enums;

/**
 * Enumeração que define os provedores de email suportados.
 *
 * Esta enum é utilizada para identificar qual provedor de email
 * deve ser utilizado para o processamento das mensagens.
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
public enum EmailProvider {

    /**
     * Provedor Amazon Web Services (AWS)
     */
    AWS("AWS"),

    /**
     * Provedor Oracle Cloud Infrastructure (OCI)
     */
    OCI("OCI");

    private final String value;

    /**
     * Construtor do enum.
     *
     * @param value valor string do provedor
     */
    EmailProvider(String value) {
        this.value = value;
    }

    /**
     * Obtém o valor string do provedor.
     *
     * @return valor do provedor
     */
    public String getValue() {
        return value;
    }

    /**
     * Converte uma string para o enum correspondente.
     *
     * @param value valor a ser convertido
     * @return enum correspondente
     * @throws IllegalArgumentException se o valor não for válido
     */
    public static EmailProvider fromValue(String value) {
        for (EmailProvider provider : EmailProvider.values()) {
            if (provider.value.equalsIgnoreCase(value)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("Provedor de email inválido: " + value);
    }
}