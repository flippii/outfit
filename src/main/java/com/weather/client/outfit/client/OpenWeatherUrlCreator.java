package com.weather.client.outfit.client;

import com.weather.client.outfit.configuration.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

import static com.weather.client.outfit.configuration.ApplicationProperties.WeatherApi;

@Component
public class OpenWeatherUrlCreator {

    private final WeatherApi weatherApi;

    @Autowired
    public OpenWeatherUrlCreator(ApplicationProperties applicationProperties) {
        this.weatherApi = applicationProperties.getWeatherApi();
    }

    public URI createCityAndCountryUri(String cityName, String country) {
        return new UriTemplate(weatherApi.getBaseUrl() + weatherApi.getCityNameUrl())
                .expand(cityName, country, weatherApi.getApiKey(), weatherApi.getUnits());
    }

    public URI createCoordinatesUri(double lat, double lon) {
        return new UriTemplate(weatherApi.getBaseUrl() + weatherApi.getCoordinatesUrl())
                .expand(lat, lon, weatherApi.getApiKey(), weatherApi.getUnits());
    }

}
