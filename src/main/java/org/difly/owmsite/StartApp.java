package org.difly.owmsite;

import org.difly.owmsite.service.JSONConverter;
import org.difly.owmsite.weather.WeatherClient;

import java.io.IOException;

public class StartApp {
    public static void main(String[] args) throws IOException {
        System.out.println("Start APP OWM-Site");

        WeatherClient weatherClient = new WeatherClient();
        weatherClient.createURI();
        weatherClient.createClient();
        JSONConverter jsonConverter = new JSONConverter(weatherClient.getJsonString());
        jsonConverter.readJSON();

        System.out.println("Close APP OWM-Site");
    }
}
