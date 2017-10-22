package com.weather.client.outfit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private final ApplicationProperties applicationProperties;

    public SwaggerConfiguration(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public Docket swaggerApiDocket() {
        Contact contact = new Contact(
                applicationProperties.getSwagger().getContactName(),
                applicationProperties.getSwagger().getContactUrl(),
                applicationProperties.getSwagger().getContactEmail());

        ApiInfo apiInfo = new ApiInfo(
                applicationProperties.getSwagger().getTitle(),
                applicationProperties.getSwagger().getDescription(),
                applicationProperties.getSwagger().getVersion(),
                applicationProperties.getSwagger().getTermsOfServiceUrl(),
                contact,
                applicationProperties.getSwagger().getLicense(),
                applicationProperties.getSwagger().getLicenseUrl(),
                new ArrayList<>());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .directModelSubstitute(java.nio.ByteBuffer.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .select()
                .paths(regex(applicationProperties.getSwagger().getDefaultIncludePattern()))
                .build();
    }

}
