/**
 * Pacote contendo as factories para criação de adaptadores de email.
 *
 * Este pacote implementa o padrão Factory Method para criar instâncias
 * apropriadas de adaptadores de email baseado no provedor configurado.
 *
 * As factories neste pacote seguem os princípios SOLID:
 * - Single Responsibility Principle (SRP): cada factory é responsável
 *   apenas pela criação de um tipo específico de objeto
 * - Open/Closed Principle (OCP): permite extensão através de novos
 *   adaptadores sem modificar o código existente
 * - Dependency Inversion Principle (DIP): depende de abstrações,
 *   não de implementações concretas
 *
 * @author Thiago Bianeck
 * @version 1.0.0
 * @since 2025
 */
package com.viasoft.emailservice.adapter.factory;
