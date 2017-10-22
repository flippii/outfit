package com.weather.client.outfit.client;

import com.weather.client.outfit.client.model.Weather;
import com.weather.client.outfit.dto.CoordinatesDto;
import com.weather.client.outfit.dto.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class OpenWeatherClient {

    private final RestTemplate restTemplate;
    private final OpenWeatherUrlCreator openWeatherUrlCreator;

    @Autowired
    public OpenWeatherClient(RestTemplateBuilder restTemplateBuilder, OpenWeatherUrlCreator openWeatherUrlCreator) {
        this.restTemplate = restTemplateBuilder.build();
        this.openWeatherUrlCreator = openWeatherUrlCreator;
    }

    public Weather getByCity(CityDto cityDto) {
        URI url = openWeatherUrlCreator.createCityAndCountryUri(cityDto.getCity(), cityDto.getCountry());
        return execute(url, Weather.class);
    }

    public Weather getByCoordinates(CoordinatesDto coordinatesDto) {
        URI url = openWeatherUrlCreator.createCoordinatesUri(coordinatesDto.getLat(), coordinatesDto.getLon());
        return execute(url, Weather.class);
    }

    private <T> T execute(URI url, Class<T> responseType) {
        RequestEntity<?> request = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        ResponseEntity<T> exchange = restTemplate.exchange(request, responseType);

        return exchange.getBody();
    }

}
