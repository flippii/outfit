package com.weather.client.outfit.service;

import com.weather.client.outfit.dto.OutfitResultDto;
import com.weather.client.outfit.dto.CityDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.weather.client.outfit.suggestion.OutfitLevel.LEVEL_4;
import static com.weather.client.outfit.utils.ModelStubUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OutfitLevelServiceTest {

    @MockBean
    private OpenWeatherService openWeatherService;

    @Autowired
    private OutfitLevelService outfitLevelService;

    @Test
    public void testGetOutfitSuggestion() {
        OutfitResultDto expectedWeatherResult = createOutfitResultStub("12 \u2103", LEVEL_4);

        given(openWeatherService.getWeatherByCity(any(CityDto.class)))
                .willReturn(createWeatherStub(12.0, "Stuttgart"));

        OutfitResultDto actualWeatherResult = outfitLevelService.getOutfitSuggestion(createCityStub("Stuttgart", "de_DE"));

        assertThat(actualWeatherResult)
                .isNotNull()
                .isEqualTo(expectedWeatherResult);
    }

}
