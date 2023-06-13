package com.challenge.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Microservicio usuarios")
                        .description("API Documentation")
                        .version("3.0")
                        .contact(new Contact().name("Globallogic").email(""))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}
