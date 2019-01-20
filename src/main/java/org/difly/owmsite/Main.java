package org.difly.owmsite;

import org.difly.owmsite.weather.WeatherClient;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start APP OWM-Site");

        WeatherClient weatherClient = new WeatherClient();

        System.out.println("Close APP OWM-Site");
    }
}
