package com.weather.client.outfit.client;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.weather.client.outfit.client.model.Weather;
import com.weather.client.outfit.utils.ResourceUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.weather.client.outfit.utils.ModelStubUtils.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenWeatherClientConsumerTest {

    private static final String QUERY_TEMPLATE = "q=%s,%s&APPID=some-test-api-key&units=metric";

    @Autowired
    private OpenWeatherClient openWeatherClient;

    @Rule
    public PactProviderRuleMk2 weatherProvider = new PactProviderRuleMk2("weather_provider", "localhost", 8089, this);

    @Pact(consumer="weather_client")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws IOException {
        return builder
                .given("weather data")
                .uponReceiving("a request for a weather request for Stuttgart")
                    .path("/weather")
                    .query(String.format(QUERY_TEMPLATE, "Stuttgart", "de_DE"))
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .body(ResourceUtils.read("classpath:weather/stuttgart_de_DE.json"), APPLICATION_JSON_VALUE)
                .toPact();
    }

    @Test
    @PactVerification("weather_provider")
    public void testFetchWeatherInformation() {
        Weather weatherResponse = openWeatherClient.getByCity(createCityStub("Stuttgart", "de_DE"));

        assertThat(weatherResponse, notNullValue());
        assertThat(weatherResponse.getName(), is("Stuttgart"));
    }

}
