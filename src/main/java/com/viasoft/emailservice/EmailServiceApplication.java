package com.viasoft.emailservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Email Service.
 * <p>
 * Esta aplicação fornece um serviço REST para envio de emails
 * com adaptação automática para diferentes provedores (AWS/OCI)
 * baseado em configuração.
 *
 * @author Viasoft
 * @version 1.0.0
 * @since 2024
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Email Service API",
                version = "1.0.0",
                description = "API para envio de emails com adaptação para "
                        + "múltiplos provedores",
                contact = @Contact(
                        name = "Viasoft",
                        email = "contato@viasoft.com.br"),
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT")))
public class EmailServiceApplication {

    /**
     * Método principal que inicia a aplicação Spring Boot.
     *
     * @param args argumentos da linha de comando passados para a aplicação
     */
    public static void main(final String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }

}
