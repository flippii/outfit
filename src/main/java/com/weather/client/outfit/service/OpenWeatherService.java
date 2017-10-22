package com.weather.client.outfit.service;

import com.weather.client.outfit.client.OpenWeatherClient;
import com.weather.client.outfit.client.model.Weather;
import com.weather.client.outfit.dto.CoordinatesDto;
import com.weather.client.outfit.exception.WeatherNotFoundException;
import com.weather.client.outfit.dto.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OpenWeatherService {

    private final OpenWeatherClient openWeatherClient;

    @Autowired
    public OpenWeatherService(OpenWeatherClient openWeatherClient) {
        this.openWeatherClient = openWeatherClient;
    }

    public Weather getWeatherByCity(CityDto cityDto) {
        return Optional.ofNullable(openWeatherClient.getByCity(cityDto))
                .orElseThrow(() -> new WeatherNotFoundException("Weather data for: \""
                        + cityDto + "\" not found."));
    }

    public Weather getWeatherByCoordinates(CoordinatesDto coordinatesDto) {
        return Optional.ofNullable(openWeatherClient.getByCoordinates(coordinatesDto))
                .orElseThrow(() -> new WeatherNotFoundException("Weather data for: \""
                        + coordinatesDto + "\" not found."));
    }

}
