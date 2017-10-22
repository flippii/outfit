package com.weather.client.outfit;

import com.weather.client.outfit.configuration.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class OutfitApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutfitApplication.class, args);
	}

}
