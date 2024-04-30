package com.example.EcoCity.configs;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Конфигурация swagger
 */
@SecurityScheme(
        name = "jwtAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Eco City Api")
                        .description("")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Tribushko Danil")
                                .email("danil.tribushko@bk.ru")))
                .tags(List.of(
                        new Tag()
                                .name("Auth Controller")
                                .description("Controller for users authorization and registration"),
                        new Tag()
                                .name("Micro district Controller")
                                .description("Controller for work with micro districts"),
                        new Tag()
                                .name("District Controller")
                                .description("Controller for work with districts"),
                        new Tag()
                                .name("Appeal Type Controller")
                                .description("Controller for work with appeals types"),
                        new Tag()
                                .name("Appeal Controller")
                                .description("Controller for work with appeals"),
                        new Tag()
                                .name("User Controller")
                                .description("Controller for work with users"),
                        new Tag()
                                .name("Event Type Controller")
                                .description("Controller for work with event type")
                ));
    }
}
