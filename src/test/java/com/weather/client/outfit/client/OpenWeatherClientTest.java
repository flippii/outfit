package com.weather.client.outfit.client;

import com.weather.client.outfit.client.model.Weather;
import com.weather.client.outfit.configuration.ApplicationProperties;
import com.weather.client.outfit.utils.ResourceUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static com.weather.client.outfit.utils.ModelStubUtils.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(OpenWeatherClient.class)
public class OpenWeatherClientTest {

    private static final String CITY_URL_TEMPLATE = "http://localhost:8089/weather?q=%s,%s&APPID=some-test-api-key&units=metric";
    private static final String COORDINATES_URL_TEMPLATE = "http://localhost:8089/weather?lat=%s&lon=%s&APPID=some-test-api-key&units=metric";

    @Autowired
    private OpenWeatherClient openWeatherClient;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void testGetByCity() {
        server.expect(requestTo(String.format(CITY_URL_TEMPLATE, "Stuttgart", "de_DE")))
                .andRespond(withSuccess(ResourceUtils.read("classpath:weather/stuttgart_de_DE.json"), APPLICATION_JSON));

        Weather weather = openWeatherClient.getByCity(createCityStub("Stuttgart", "de_DE"));

        assertWeather(weather);
    }

    @Test
    public void testGetByCoordinates() {
        server.expect(requestTo(String.format(COORDINATES_URL_TEMPLATE, 12.0, 10.0)))
                .andRespond(withSuccess(ResourceUtils.read("classpath:weather/stuttgart_de_DE.json"), APPLICATION_JSON));

        Weather weather = openWeatherClient.getByCoordinates(createCoordinatesStub(12.0, 10.0));

        assertWeather(weather);
    }

    private void assertWeather(Weather weather) {
        assertThat(weather.getName(), is("Stuttgart"));
        assertThat(weather.getActual().getTemp(), is(12.0));
    }

    @TestConfiguration
    public static class OpenWeatherClientTestConfiguration {

        @Bean
        public OpenWeatherUrlCreator openWeatherUrlCreator(ApplicationProperties applicationProperties) {
            return new OpenWeatherUrlCreator(applicationProperties);
        }

    }

}
