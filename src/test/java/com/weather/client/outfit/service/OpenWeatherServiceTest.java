package com.weather.client.outfit.service;

import com.weather.client.outfit.client.model.Weather;
import com.weather.client.outfit.client.OpenWeatherClient;
import com.weather.client.outfit.dto.CityDto;
import com.weather.client.outfit.dto.CoordinatesDto;
import com.weather.client.outfit.exception.WeatherNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.weather.client.outfit.utils.ModelStubUtils.createWeatherStub;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherServiceTest {

    @Mock
    private OpenWeatherClient openWeatherClient;

    private OpenWeatherService openWeatherService;

    @Before
    public void setUp() {
        openWeatherService = new OpenWeatherService(openWeatherClient);
    }

    @Test
    public void testGetWeatherByCity() {
        Weather expectedWeather = createWeatherStub(12.0, "Stuttgart");

        when(openWeatherClient.getByCity(any(CityDto.class))).thenReturn(expectedWeather);

        Weather actualWeather = openWeatherService.getWeatherByCity(CityDto.builder().build());

        assertThat(actualWeather)
                .isNotNull()
                .isEqualTo(expectedWeather);
    }

    @Test
    public void testGetWeatherByCoordinates() {
        Weather expectedWeather = createWeatherStub(12.0, "Stuttgart");

        when(openWeatherClient.getByCoordinates(any(CoordinatesDto.class))).thenReturn(expectedWeather);

        Weather actualWeather = openWeatherService.getWeatherByCoordinates(CoordinatesDto.builder().build());

        assertThat(actualWeather)
                .isNotNull()
                .isEqualTo(expectedWeather);
    }

    @Test(expected = WeatherNotFoundException.class)
    public void testFetchWeatherFailure() {
        when(openWeatherClient.getByCity(any(CityDto.class))).thenReturn(null);

        openWeatherService.getWeatherByCity(CityDto.builder().build());
    }

}
