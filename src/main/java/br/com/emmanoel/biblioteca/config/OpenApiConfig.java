package br.com.emmanoel.biblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Biblioteca API")
                        .version("1.0.0")
                        .description("API do sistema de biblioteca com CRUD de livros e empréstimos.")
                        .contact(new Contact()
                                .name("Emmanoel Felipe")
                                .email("emmanoel@unipe.br")));
    }
}
