package ru.edu.service;

import java.time.LocalDateTime;

public class WeatherInfo {

    private String cityName;
    private int temperature;
    private LocalDateTime expiryTime;

    public static Builder builder() {
        return new Builder();
    }

    public WeatherInfo() {

    }

    public static class Builder {

        private WeatherInfo info = new WeatherInfo();

        public Builder setCityName(String cityName) {
            info.cityName = cityName;
            return this;
        }

        public Builder setTemperature(int temperature) {
            info.temperature = temperature;
            return this;
        }

        public Builder setExpiryTime(LocalDateTime expiryTime) {
            info.expiryTime = expiryTime;
            return this;
        }

        public WeatherInfo build() {
            return info;
        }

    }

    public String getCityName() {
        return cityName;
    }

    public int getTemperature() {
        return temperature;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public String toString() {
        return "WeatherInfo{" +
                "cityName='" + cityName + '\'' +
                ", temperature=" + temperature +
                ", expiryTime=" + expiryTime +
                '}';
    }
}
