package org.difly.owmsite;

import org.difly.owmsite.weather.WeatherClient;

import java.io.IOException;

public class StartApp {
    public static void main(String[] args) throws IOException {
        System.out.println("Start APP OWM-Site");

        WeatherClient weatherClient = new WeatherClient();
        weatherClient.createURI();
        weatherClient.createClient();

        System.out.println("Close APP OWM-Site");
    }
}
