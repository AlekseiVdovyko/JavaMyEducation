package ru.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private WeatherProvider weatherProvider;

    public WeatherCache() {
    }

    public WeatherInfo getWeatherInfo(String cityName) {

        cityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1).toLowerCase();

        synchronized (cache) {
            if (!cache.containsKey(cityName)) {
                cache.put(cityName, weatherProvider.get(cityName));
            } else {
                if (LocalDateTime.now().isAfter(cache.get(cityName).getExpiryTime())) {
                    removeWeatherInfo(cityName);
                    cache.put(cityName, weatherProvider.get(cityName));
                }
            }
            return cache.get(cityName);
        }
    }

	public void removeWeatherInfo(String cityName) {
        cityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1).toLowerCase();
        cache.remove(cityName);
        System.out.println("Success remove city = " + cityName);
    }

    private Map<String, WeatherInfo> setCache(String cityName) {
        cache.put(weatherProvider.get(cityName).getCityName(), weatherProvider.get(cityName));
        return cache;
    }

    @Autowired
    public void setWeatherProvider(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }
}
