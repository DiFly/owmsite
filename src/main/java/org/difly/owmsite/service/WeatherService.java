package org.difly.owmsite.service;

import org.difly.owmsite.domain.owm.WeatherObject;
import org.difly.owmsite.weather.WeatherClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {
    private WeatherObject weatherObject;

    public void start(){
        System.out.println("Start WeatherService APP OWM-Site");

        WeatherClient weatherClient = new WeatherClient();
        weatherClient.createURI();
        try {
            weatherClient.createClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONConverter jsonConverter = new JSONConverter(weatherClient.getJsonString());
        try {
            jsonConverter.readJSON();
            this.weatherObject = jsonConverter.getWeatherObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Close WeatherService APP OWM-Site");
    }

    public WeatherObject getWeatherObject() {
        return weatherObject;
    }

    public void setWeatherObject(WeatherObject weatherObject) {
        this.weatherObject = weatherObject;
    }
}
