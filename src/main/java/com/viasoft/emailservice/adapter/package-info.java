/**
 * Pacote contendo adaptadores para diferentes provedores de email.
 *
 * <p>Este pacote implementa o padrão Adapter para permitir a integração
 * com múltiplos provedores de email (SendGrid, Amazon SES, etc.) de forma
 * transparente e desacoplada.</p>
 *
 * <p>Os adaptadores convertem os dados do formato padrão da aplicação
 * ({@link com.viasoft.emailservice.dto.EmailRequestDTO}) para os formatos
 * específicos exigidos por cada provedor.</p>
 *
 * <h2>Principais componentes:</h2>
 * <ul>
 *   <li>{@link com.viasoft.emailservice.adapter.EmailAdapter} - Interface base</li>
 *   <li>Implementações específicas para cada provedor</li>
 * </ul>
 *
 * <h2>Padrões aplicados:</h2>
 * <ul>
 *   <li><strong>Adapter Pattern</strong> - Adaptação entre interfaces incompatíveis</li>
 *   <li><strong>Strategy Pattern</strong> - Seleção dinâmica de provedores</li>
 *   <li><strong>Dependency Inversion Principle</strong> - Inversão de dependências</li>
 * </ul>
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
package com.viasoft.emailservice.adapter;
