package org.difly.owmsite;

import org.difly.owmsite.service.JSONConverter;
import org.difly.owmsite.weather.WeatherClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class StartApp {
    public static void main(String[] args) throws IOException {
        System.out.println("Start APP OWM-Site");
        SpringApplication.run(StartApp.class, args);

        WeatherClient weatherClient = new WeatherClient();
        weatherClient.createURI();
        weatherClient.createClient();
        JSONConverter jsonConverter = new JSONConverter(weatherClient.getJsonString());
        jsonConverter.readJSON();

        System.out.println("Close APP OWM-Site");
    }
}
