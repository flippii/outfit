package com.weather.client.outfit.dto;

import com.weather.client.outfit.suggestion.OutfitLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OutfitResultDto {

    private String temperature;
    private int outfitLevel;

}
