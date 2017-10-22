package com.weather.client.outfit.utils;

import com.weather.client.outfit.client.model.Weather;
import com.weather.client.outfit.dto.CityDto;
import com.weather.client.outfit.dto.CoordinatesDto;
import com.weather.client.outfit.dto.OutfitResultDto;
import com.weather.client.outfit.suggestion.OutfitLevel;

public class ModelStubUtils {

    public static CityDto createCityStub(String cityName, String country) {
        return CityDto.builder()
                .city(cityName)
                .country(country)
                .build();
    }

    public static CoordinatesDto createCoordinatesStub(double lat, double lon) {
        return CoordinatesDto.builder()
                .lat(lat)
                .lon(lon)
                .build();
    }

    public static OutfitResultDto createOutfitResultStub(String temparature, OutfitLevel outfitLevel) {
        return OutfitResultDto.builder()
                .temperature(temparature)
                .outfitLevel(outfitLevel.getLevel())
                .build();
    }

    public static Weather createWeatherStub(double temperature, String cityName) {
        Weather.Actual actual = new Weather.Actual();
        actual.setTemp(temperature);

        Weather weather = new Weather();
        weather.setName(cityName);
        weather.setActual(actual);

        return weather;
    }

}
