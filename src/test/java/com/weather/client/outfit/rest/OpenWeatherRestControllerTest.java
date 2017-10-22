package com.weather.client.outfit.rest;

import com.weather.client.outfit.client.model.Weather;
import com.weather.client.outfit.dto.CityDto;
import com.weather.client.outfit.dto.CoordinatesDto;
import com.weather.client.outfit.exception.WeatherNotFoundException;
import com.weather.client.outfit.service.OpenWeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.weather.client.outfit.utils.ModelStubUtils.createWeatherStub;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OpenWeatherRestController.class)
public class OpenWeatherRestControllerTest {

    private static final String CITY_URL_TEMPLATE = "/api/v1/weather/city/%s/%s";
    private static final String COORDINATES_URL_TEMPLATE = "/api/v1/weather/coordinates/%s/%s";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OpenWeatherService openWeatherService;

    @Test
    public void testActualWeatherByCityName() throws Exception {
        Weather expectedWeather = createWeatherStub(12.0, "Stuttgart");

        given(openWeatherService.getWeatherByCity(any(CityDto.class)))
                .willReturn(expectedWeather);

        mvc.perform(get(createCityUrlTemplate())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Stuttgart")));
    }

    @Test
    public void testActualWeatherByCityNameFailure() throws Exception {
        String expectedExceptionMsg = "Dummy Text.";

        given(openWeatherService.getWeatherByCity(any(CityDto.class)))
                .willThrow(new WeatherNotFoundException(expectedExceptionMsg));

        mvc.perform(get(createCityUrlTemplate())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.exception", is("com.weather.client.outfit.exception.WeatherNotFoundException")))
                .andExpect(jsonPath("$.message", is(expectedExceptionMsg)));
    }

    private String createCityUrlTemplate() {
        return String.format(CITY_URL_TEMPLATE, "Stuttgart", "de_DE");
    }

    @Test
    public void testActualWeatherByCoordinates() throws Exception {
        Weather expectedWeather = createWeatherStub(12.0, "Stuttgart");

        given(openWeatherService.getWeatherByCoordinates(any(CoordinatesDto.class)))
                .willReturn(expectedWeather);

        mvc.perform(get(createCoordinatesUrlTemplate())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Stuttgart")));
    }

    private String createCoordinatesUrlTemplate() {
        return String.format(COORDINATES_URL_TEMPLATE, 12.0, 10.0);
    }

}
