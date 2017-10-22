package com.weather.client.outfit.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenWeatherUrlCreatorTest {

    @Autowired
    private OpenWeatherUrlCreator openWeatherUrlCreator;

    @Test
    public void testCreateCityAndCountryUri() {
        URI actualUrl = openWeatherUrlCreator.createCityAndCountryUri("Stuttgart", "de_DE");

        assertThat(actualUrl, notNullValue());
        assertThat(actualUrl.toString(), containsString("Stuttgart"));
        assertThat(actualUrl.toString(), containsString("de_DE"));
    }

    @Test
    public void testCreateCoordinatesUri() {
        URI actualUrl = openWeatherUrlCreator.createCoordinatesUri(12.0, 10.0);

        assertThat(actualUrl, notNullValue());
        assertThat(actualUrl.toString(), containsString(String.valueOf(12.0)));
        assertThat(actualUrl.toString(), containsString(String.valueOf(10.0)));
    }

}
