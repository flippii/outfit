package com.weather.client.outfit.service;

import com.weather.client.outfit.client.model.Weather;
import com.weather.client.outfit.dto.OutfitResultDto;
import com.weather.client.outfit.dto.CityDto;
import com.weather.client.outfit.service.mapper.OutfitResultMapper;
import com.weather.client.outfit.suggestion.OutfitLevel;
import com.weather.client.outfit.suggestion.SuggestionComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutfitLevelService {
    
    private final OpenWeatherService openWeatherService;
    private final SuggestionComponent suggestionComponent;
    private final OutfitResultMapper outfitResultMapper;

    @Autowired
    public OutfitLevelService(OpenWeatherService openWeatherService, SuggestionComponent suggestionComponent,
                              OutfitResultMapper outfitResultMapper) {

        this.openWeatherService = openWeatherService;
        this.suggestionComponent = suggestionComponent;
        this.outfitResultMapper = outfitResultMapper;
    }

    public OutfitResultDto getOutfitSuggestion(CityDto cityDto) {
        Weather weather = openWeatherService.getWeatherByCity(cityDto);
        OutfitLevel outfitLevel = suggestionComponent.calculate(weather.getActual().getTemp());
        return outfitResultMapper.mapToOutfitResult(weather, outfitLevel);
    }

}
