package ru.edu;

import com.jayway.jsonpath.JsonPath;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

/**
 * Weather provider
 */
public class WeatherProvider {

    private RestTemplate restTemplate;
    private String appKey;

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) {
        city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();

        String cityName = "";
        String shortDescription = "";
        String description = "";
        double temperature = 0.0;
        double feelsLikeTemperature = 0.0;
        double windSpeed = 0.0;
        double pressure = 0.0;
        LocalDateTime expiryTime = LocalDateTime.now();

        try {

            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + appKey;
            RestTemplate restTemplate = new RestTemplate();
            String answerServer = restTemplate.getForObject(url, String.class);

            cityName = JsonPath.parse(answerServer).read("$.name");

            LinkedHashMap<String, Object> map = JsonPath.parse(answerServer).read("$.weather[0]");
            shortDescription = (String) map.get("main");

            description = (String) map.get("description");

            final double KELVIN = 273.15;
            String temper = answerServer.substring(answerServer.lastIndexOf("\"temp\":") + 7);
            temper = temper.split(",")[0];
            temperature = (Double.parseDouble(temper)) - KELVIN;
            temperature = Math.round(temperature * 100.0) / 100.0;

            String feels = answerServer.substring(answerServer.lastIndexOf("\"feels_like\":") + 13);
            feels = feels.split(",")[0];
            feelsLikeTemperature = (Double.parseDouble(feels)) - KELVIN;
            feelsLikeTemperature = Math.round(feelsLikeTemperature * 100.0) / 100.0;

            String wind = answerServer.substring(answerServer.lastIndexOf("\"speed\":") + 8);
            wind = wind.split(",")[0];
            windSpeed = Double.parseDouble(wind);
            windSpeed = Math.round(windSpeed * 100.0) / 100.0;

            final double PRESSER = 0.073556127;
            int intPressure = JsonPath.parse(answerServer).read("$.main.pressure");
            pressure = (double) intPressure;
            pressure = pressure * (PRESSER * 10);
            pressure = Math.round(pressure * 100) / 100;

            expiryTime = LocalDateTime.now().plusMinutes(5);

        } catch (Exception ex) {
            return null;
        }

        WeatherInfo weatherInfo = WeatherInfo.builder()
                .setCity(cityName)
                .setShortDescription(shortDescription)
                .setDescription(description)
                .setTemperature(temperature)
                .setFeelsLikeTemperature(feelsLikeTemperature)
                .setWindSpeed(windSpeed)
                .setPressure(pressure)
                .setExpiryTime(expiryTime)
                .build();

        return weatherInfo;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
