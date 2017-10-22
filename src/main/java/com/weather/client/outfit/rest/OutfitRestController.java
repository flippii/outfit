package com.weather.client.outfit.rest;

import com.weather.client.outfit.dto.OutfitResultDto;
import com.weather.client.outfit.dto.CityDto;
import com.weather.client.outfit.service.OutfitLevelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/outfit")
public class OutfitRestController {

    private final OutfitLevelService outfitLevelService;

    @Autowired
    public OutfitRestController(OutfitLevelService outfitLevelService) {
        this.outfitLevelService = outfitLevelService;
    }

    @ApiOperation(value = "Get an outfit suggestion for your position.", response = OutfitResultDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(path = "/now/{country}/{city}", produces = APPLICATION_JSON_VALUE)
    public OutfitResultDto weather(@Valid CityDto cityDto) {
        return outfitLevelService.getOutfitSuggestion(cityDto);
    }

}
