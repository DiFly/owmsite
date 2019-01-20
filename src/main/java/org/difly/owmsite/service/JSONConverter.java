package org.difly.owmsite.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.difly.owmsite.domain.owm.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

public class JSONConverter {
    private String inString;
    private WeatherObject weatherObject;

    public void readJSON() throws IOException {
        weatherObject = new WeatherObject();
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        //read JSON like DOM Parser
        JsonNode rootNode = objectMapper.readTree(inString);

        //coord
        JsonNode coordNode = rootNode.path("coord");
        JsonNode lonNode = coordNode.path("lon");
        JsonNode latNode = coordNode.path("lat");
        weatherObject.setCoord(new Coord(lonNode.asDouble(), latNode.asDouble()));
        System.out.println("coord = " + weatherObject.getCoord().toString());

        //weather
        JsonNode weatherNode = rootNode.path("weather");
//        System.out.println("WEATHER NODE" + weatherNode.toString()); //OWM Api json has "[{" in node 'weather', why they do it... don't understand, I think this is illogical
        Iterator<JsonNode> nodeIterator = weatherNode.elements();
        JsonNode weatherNodeIterator = nodeIterator.next();
//        System.out.println("WEATHER NODE ITERATOR" + weatherNodeIterator.toString());
        JsonNode weatherIdNode = weatherNodeIterator.path("id");
        JsonNode weatherMainNode = weatherNodeIterator.path("main");
        JsonNode weatherDescriptionNode = weatherNodeIterator.path("description");
        JsonNode weatherIconNode = weatherNodeIterator.path("icon");
        weatherObject.setWeather(new Weather(weatherIdNode.asInt(), weatherMainNode.asText(), weatherDescriptionNode.asText(), weatherIconNode.asText()));
        System.out.println("Weather = " + weatherObject.getWeather().toString());

        //private String base;
        JsonNode baseNode = rootNode.path("base");
        System.out.println("base = " + baseNode.asText());
        weatherObject.setBase(baseNode.asText());

        //private Main main;
        JsonNode mainNode = rootNode.path("main");
        JsonNode mainTempNode = mainNode.path("temp");
        JsonNode mainPressureNode = mainNode.path("pressure");
        JsonNode mainHumidityNode = mainNode.path("humidity");
        JsonNode mainTemp_minNode = mainNode.path("temp_min");
        JsonNode mainTemp_maxNode = mainNode.path("temp_max");
        weatherObject.setMain(new Main(mainTempNode.asDouble(), mainPressureNode.asInt(), mainHumidityNode.asInt(), mainTemp_minNode.asDouble(), mainTemp_maxNode.asDouble()));
        System.out.println("main = " + weatherObject.getMain().toString());

        //private int visibility;
        JsonNode visibilityNode = rootNode.path("visibility");
        weatherObject.setVisibility(visibilityNode.asInt());
        System.out.println("visibility = " + weatherObject.getVisibility());

//        private Wind wind;
        JsonNode windNode = rootNode.path("wind");
        JsonNode windSpeedNode = windNode.path("speed");
        JsonNode windDegNode = windNode.path("deg");
        weatherObject.setWind(new Wind(windSpeedNode.asDouble(), windDegNode.asInt()));
        System.out.println("wind = " + weatherObject.getWind().toString());

//        private Clouds clouds;
        JsonNode cloudsNode = rootNode.path("clouds");
        JsonNode cloudsAllNode = cloudsNode.path("all");
        weatherObject.setClouds(new Clouds(cloudsAllNode.asInt()));
        System.out.println("clouds = " + weatherObject.getClouds().toString());

//        private Rain rain;
        JsonNode rainNode = rootNode.path("rain");
        JsonNode cloudsH1Node = rainNode.path("1h");
        JsonNode cloudsH3Node = rainNode.path("3h");
        Rain rain = new Rain();
        rain.setH1(cloudsH1Node.asInt());
        rain.setH3(cloudsH3Node.asInt());
        weatherObject.setRain(rain);
        System.out.println("rain = " + weatherObject.getRain().toString());

//        private Snow snow;
        JsonNode snowNode = rootNode.path("snow");
        JsonNode snowH1Node = snowNode.path("1h");
        JsonNode snowH3Node = snowNode.path("3h");
        Snow snow = new Snow();
        snow.setH1(snowH1Node.asInt());
        snow.setH3(snowH3Node.asInt());
        weatherObject.setSnow(snow);
        System.out.println("snow = " + weatherObject.getSnow().toString());

//        private String dt; Time of data calculation, unix, UTC
        JsonNode dtNode = rootNode.path("dt");
        weatherObject.setDt(dtNode.asLong());
        System.out.println("dt = " + weatherObject.getDt() + " "
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(DateConverter.date(weatherObject.getDt())));

//        private Sys sys;
        JsonNode sysNode = rootNode.path("sys");
        JsonNode sysTypeNode = sysNode.path("type");
        JsonNode sysIdNode = sysNode.path("id");
        JsonNode sysMessageNode = sysNode.path("message");
        JsonNode sysCountryNode = sysNode.path("country");
        JsonNode sysSunriseNode = sysNode.path("sunrise");
        JsonNode sysSunsetNode = sysNode.path("sunset");


        weatherObject.setSys(new Sys(sysTypeNode.asInt(), sysIdNode.asInt(),sysMessageNode.asDouble(), sysCountryNode.asText(), sysSunriseNode.asLong(), sysSunsetNode.asLong()));
        System.out.println("sys = " + weatherObject.getSys().toString());

//        private long id; city ID
        JsonNode idNode = rootNode.path("id");
        weatherObject.setId(idNode.asLong());
        System.out.println("id = " + weatherObject.getId());

//        private String name;
        JsonNode nameNode = rootNode.path("name");
        weatherObject.setName(nameNode.asText());
        System.out.println("name = " + weatherObject.getName());

//        private int cod;
        JsonNode codNode = rootNode.path("cod");
        weatherObject.setCod(codNode.asInt());
        System.out.println("cod = " + weatherObject.getCod());

    }

    public WeatherObject getWeatherObject() {
        return weatherObject;
    }

    public JSONConverter(String inString) {
        this.inString = inString;
    }
}
