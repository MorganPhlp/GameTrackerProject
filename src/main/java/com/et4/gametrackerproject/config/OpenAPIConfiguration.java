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
                        .version("1.0.0")
                        .description("API documentation for Game Tracker project"));
    }

    @Bean
    public GroupedOpenApi restApiV1() {
        return GroupedOpenApi.builder()
                .group("REST API v1")
                .packagesToScan("com.et4.gametrackerproject.controller.api", "com.et4.gametrackerproject.controller")
                .pathsToMatch("/**")
                .build();
    }
}
