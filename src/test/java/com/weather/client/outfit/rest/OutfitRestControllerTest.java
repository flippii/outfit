package com.weather.client.outfit.rest;

import com.weather.client.outfit.dto.OutfitResultDto;
import com.weather.client.outfit.dto.CityDto;
import com.weather.client.outfit.service.OutfitLevelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.weather.client.outfit.suggestion.OutfitLevel.LEVEL_4;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OutfitRestController.class)
public class OutfitRestControllerTest {

    private static final String URL_TEMPLATE = "/api/v1/outfit/now/%s/%s";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OutfitLevelService outfitLevelService;

    @Test
    public void testActualeWeather() throws Exception {
        OutfitResultDto expectedWeatherResult = OutfitResultDto.builder()
                .temperature("12")
                .outfitLevel(LEVEL_4.getLevel())
                .build();

        given(outfitLevelService.getOutfitSuggestion(any(CityDto.class)))
                .willReturn(expectedWeatherResult);

        mvc.perform(get(createUrlTemplate())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature", is("12")))
                .andExpect(jsonPath("$.outfitLevel", is(LEVEL_4.getLevel())));
    }

    private String createUrlTemplate() {
        return String.format(URL_TEMPLATE, "Stuttgart", "de_DE");
    }

}
