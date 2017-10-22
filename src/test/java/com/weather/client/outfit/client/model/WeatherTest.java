package com.weather.client.outfit.client.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.client.outfit.utils.ResourceUtils;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class WeatherTest {

    @Test
    public void testCreateInstance() throws IOException {
        Weather weather = new ObjectMapper()
                .readValue(ResourceUtils.read("classpath:weather/stuttgart_de_DE.json"), Weather.class);

        assertThat(weather.getId(), is(2825297));
        assertThat(weather.getName(), is("Stuttgart"));
        assertThat(weather.getWind(), notNullValue());
        assertThat(weather.getWind(), is(new Weather.Wind(4.6, 290.0)));
        assertThat(weather.getActual(), notNullValue());
        assertThat(weather.getInfo(), notNullValue());
        assertThat(weather.getInfo(), hasSize(1));
        assertThat(weather.getGeo(), notNullValue());
        assertThat(weather.getGeo(), is(new Weather.Geo(48.78, 9.18)));
        assertThat(weather.getInternal(), notNullValue());
    }

}
