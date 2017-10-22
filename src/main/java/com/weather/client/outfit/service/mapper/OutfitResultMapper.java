package com.weather.client.outfit.service.mapper;

import com.weather.client.outfit.client.model.Weather;
import com.weather.client.outfit.dto.OutfitResultDto;
import com.weather.client.outfit.suggestion.OutfitLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OutfitResultMapper {

    private final MessageSource messageSource;

    @Autowired
    public OutfitResultMapper(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public OutfitResultDto mapToOutfitResult(Weather weather, OutfitLevel outfitLevel) {
        String temperature = formatTemperature(weather.getActual().getTemp());

        return OutfitResultDto.builder()
                .temperature(temperature)
                .outfitLevel(outfitLevel.getLevel())
                .build();
    }

    private String formatTemperature(Object... args) {
        return messageSource.getMessage("temperature.celcius", args, LocaleContextHolder.getLocale());
    }

}
