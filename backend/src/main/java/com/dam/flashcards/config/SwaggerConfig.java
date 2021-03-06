package com.dam.flashcards.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Método de configuración de Swagger para documentación de API
     * 
     * @return Docket Información de la aplicación para la configuración de Swagger
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dam.flashcards.resources"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Información de la aplicación para la configuración de Swagger
     * 
     * @return ApiInfo objeto con la información de la aplicación
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Flashcards",
                "Aplicación para almacenar flashcards.",
                "1.0",
                "",
                new Contact("Nilton da Silva Junior", "https://github.com/niltonsjr", "nilton.s.j@outlook.es"),
                "", "", Collections.emptyList());
    }
}