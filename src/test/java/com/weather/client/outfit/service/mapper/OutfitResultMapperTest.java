package com.weather.client.outfit.service.mapper;

import com.weather.client.outfit.client.model.Weather;
import com.weather.client.outfit.dto.OutfitResultDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static com.weather.client.outfit.suggestion.OutfitLevel.LEVEL_3;
import static com.weather.client.outfit.utils.ModelStubUtils.createWeatherStub;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OutfitResultMapperTest {

    @Mock
    private MessageSource messageSource;

    private OutfitResultMapper outfitResultMapper;

    @Before
    public void setUp() {
        outfitResultMapper = new OutfitResultMapper(messageSource);
    }

    @Test
    public void testMapToOutfitResult() {
        Weather weatherStub = createWeatherStub(12.0, "Stuttgart");

        when(messageSource.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("12");

        OutfitResultDto outfitResultDto = outfitResultMapper.mapToOutfitResult(weatherStub, LEVEL_3);

        assertThat(outfitResultDto, notNullValue());
        assertThat(outfitResultDto.getTemperature(), is("12"));
        assertThat(outfitResultDto.getOutfitLevel(), is(LEVEL_3.getLevel()));
    }

}
