package com.weather.client.outfit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoordinatesDto {

    @Min(value = 0)
    private double lat;

    @Min(value = 0)
    private double lon;

}
