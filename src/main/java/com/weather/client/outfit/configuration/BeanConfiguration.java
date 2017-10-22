package com.weather.client.outfit.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public RestTemplateBuilder restTemplate() {
        return new RestTemplateBuilder();
    }

}
