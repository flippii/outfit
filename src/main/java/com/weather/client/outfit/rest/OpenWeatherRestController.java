package com.weather.client.outfit.rest;

import com.weather.client.outfit.client.model.Weather;
import com.weather.client.outfit.dto.CoordinatesDto;
import com.weather.client.outfit.dto.CityDto;
import com.weather.client.outfit.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/weather")
public class OpenWeatherRestController {

    private final OpenWeatherService openWeatherService;

    @Autowired
    public OpenWeatherRestController(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    @GetMapping(path = "/city/{country}/{city}", produces = APPLICATION_JSON_VALUE)
    public Weather weatherByCity(@Valid CityDto cityDto) {
        return openWeatherService.getWeatherByCity(cityDto);
    }

    @GetMapping(path = "/coordinates/{lan}/{lat}", produces = APPLICATION_JSON_VALUE)
    public Weather weatherByCoordinates(@Valid CoordinatesDto coordinatesDto) {
        return openWeatherService.getWeatherByCoordinates(coordinatesDto);
    }

}
