package ru.edu.utilities;

import ru.edu.service.CityInfo;
import ru.edu.service.WeatherInfo;

final public class FullCityInfo {
    final private CityInfo cityInfo;
    final private String temperature;
    public FullCityInfo(CityInfo cityInfo, String temperature) {
       this.cityInfo = cityInfo;
       this.temperature = temperature;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public String getTemperature() {
        return temperature;
    }
}
