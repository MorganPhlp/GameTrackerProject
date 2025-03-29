package com.et4.gametrackerproject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Game Tracker API")
                        .description("API documentation for Game Tracker project"));
    }

    @Bean
    public GroupedOpenApi restApiV1() {
        return GroupedOpenApi.builder()
                .group("REST API v1")
                // On scanne l'ensemble du package contenant les contrôleurs
                .packagesToScan("com.et4.gametrackerproject.controller")
                // On limite l'affichage aux endpoints dont l'URL commence par APP_ROOT
                .pathsToMatch(APP_ROOT + "/**")
                .build();
    }
}
