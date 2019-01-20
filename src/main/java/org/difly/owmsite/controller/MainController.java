package org.difly.owmsite.controller;

import org.difly.owmsite.domain.owm.WeatherObject;
import org.difly.owmsite.service.DateConverter;
import org.difly.owmsite.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DecimalFormat;
import java.text.NumberFormat;

@Controller
public class MainController {
    @Autowired
    WeatherService weatherService;

    @GetMapping("/")
    public String weather(Model model){
        weatherService.start();
        WeatherObject weatherObject = weatherService.getWeatherObject();

        model.addAttribute("cityname", weatherObject.getName());

        double k = (weatherObject.getMain().getTemp_min() + weatherObject.getMain().getTemp_max())/2.0;
//        double celsius = ((f - 32.0)*5.0)/9.0; // fahrenhiet to celsium
        double celsius = k - 273.0;
        NumberFormat nf = new DecimalFormat("#.##");
        String cStr = nf.format(celsius) + " C";
        model.addAttribute("citytemp", cStr);
        model.addAttribute("date", DateConverter.simpleDate(weatherObject.getDt()));

        return "weather";
    }


}
