package ru.edu.service;

import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public class WeatherProvider {

    private RestTemplate restTemplate;
    private long time;
    private String appKey;

    public WeatherProvider() {
    }

    public WeatherProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherInfo get(String cityName) {
        cityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1).toLowerCase();

        int temperature = 0;
        LocalDateTime expiryTime = LocalDateTime.now();

        try {

            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + appKey;
            RestTemplate restTemplate = new RestTemplate();
            String answerServer = restTemplate.getForObject(url, String.class);

            LinkedHashMap<String, Object> map = JsonPath.parse(answerServer).read("$.weather[0]");

            final double KELVIN = 273.15;
            String temper = answerServer.substring(answerServer.lastIndexOf("\"temp\":") + 7);
            temper = temper.split(",")[0];
            double tempdouble = (Double.parseDouble(temper)) - KELVIN;
            temperature = (int) Math.round(tempdouble);

            expiryTime = LocalDateTime.now().plusMinutes(time);

        } catch (Exception ex) {
            return null;
        }

        WeatherInfo weatherInfo = WeatherInfo.builder()
                .setCityName(cityName)
                .setTemperature(temperature)
                .setExpiryTime(expiryTime)
                .build();

        return weatherInfo;
    }

    @Value("${weatherProvider.time}")
    public void setTime(long time) {
        this.time = time;
    }

    @Value("${weatherProvider.appKey}")
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
