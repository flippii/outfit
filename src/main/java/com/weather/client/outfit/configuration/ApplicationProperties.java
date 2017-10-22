package com.weather.client.outfit.configuration;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    @Getter
    private final WeatherApi weatherApi = new WeatherApi();

    @Getter
    private final Swagger swagger = new Swagger();

    @Data
    public static class WeatherApi {

        private String baseUrl;
        private String apiKey;
        private String units;
        private String cityNameUrl;
        private String coordinatesUrl;

    }

    @Data
    public static class Swagger {

        private String title = "Application API";
        private String description = "API documentation";
        private String version = "0.0.1";
        private String termsOfServiceUrl;
        private String contactName;
        private String contactUrl;
        private String contactEmail;
        private String license;
        private String licenseUrl;
        private String defaultIncludePattern = "/api/.*";

    }

}
