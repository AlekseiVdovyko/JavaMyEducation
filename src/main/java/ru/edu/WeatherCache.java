package ru.edu;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Weather cache.
 */
public class WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private WeatherProvider weatherProvider;

    /**
     * Default constructor.
     */

    public WeatherCache() {
    }

    /**
     * Get ACTUAL weather info for current city or null if current city not found.
     * If cache doesn't contain weather info OR contains NOT ACTUAL info then we should download info
     * If you download weather info then you should set expiry time now() plus 5 minutes.
     * If you can't download weather info then remove weather info for current city from cache.
     *
     * @param city - city
     * @return actual weather info
     */
    public WeatherInfo getWeatherInfo(String city) {
        city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();

        try {
            synchronized (cache) {

                if (cache.get(city) == null) {
                    setCache(city);
                }
                if (LocalDateTime.now().isAfter(cache.get(city).getExpiryTime())) {
                    setCache(city);
                }
                if (setCache(city).isEmpty()) {
                    removeWeatherInfo(city);
                }
            }
        } catch (Exception ex) {
            System.out.println("City with name \"" + city + "\" do not exist");
            return null;
        }

        return cache.get(city);
    }
	
	/**
	* Remove weather info from cache.
	**/
	public void removeWeatherInfo(String city) {
        city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
        cache.remove(city);
        System.out.println("success remove city = " + city);
    }

    public Map<String, WeatherInfo> getCache() {
        synchronized (cache) {
            return cache;
        }
    }

    private Map<String, WeatherInfo> setCache(String city) {
        cache.put(weatherProvider.get(city).getCity(), weatherProvider.get(city));
        return cache;
    }

    @Autowired
    public void setWeatherProvider(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public WeatherProvider getWeatherProvider() {
        return weatherProvider;
    }

    public String toString() {
        return "WeatherCache{" +
                "cache=" + cache +
                ", weatherProvider=" + weatherProvider +
                '}';
    }
}
