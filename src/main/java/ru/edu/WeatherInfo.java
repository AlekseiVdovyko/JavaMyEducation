package ru.edu;

import java.time.LocalDateTime;

/**
 * Weather info.
 */
public class WeatherInfo {

    private String city;

    /**
     * Short weather description
     * Like 'sunny', 'clouds', 'raining', etc
     */
    private String shortDescription;

    /**
     * Weather description.
     * Like 'broken clouds', 'heavy raining', etc
     */
    private String description;

    /**
     * Temperature.
     */
    private double temperature;

    /**
     * Temperature that fells like.
     */
    private double feelsLikeTemperature;

    /**
     * Wind speed.
     */
    private double windSpeed;

    /**
     * Pressure.
     */
    private double pressure;

    /**
     * Expiry time of weather info.
     * If current time is above expiry time then current weather info is not actual!
     */
    private LocalDateTime expiryTime;

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {

        private WeatherInfo info = new WeatherInfo();

        public Builder setCity(String city) {
            info.city = city;
            return this;
        }

        public Builder setShortDescription(String shortDescription) {
            info.shortDescription = shortDescription;
            return this;
        }

        public Builder setDescription(String description) {
            info.description = description;
            return this;
        }

        public Builder setTemperature(double temperature) {
            info.temperature = temperature;
            return this;
        }

        public Builder setFeelsLikeTemperature(double feelsLikeTemperature) {
            info.feelsLikeTemperature = feelsLikeTemperature;
            return this;
        }

        public Builder setWindSpeed(double windSpeed) {
            info.windSpeed = windSpeed;
            return this;
        }

        public Builder setPressure(double pressure) {
            info.pressure = pressure;
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

    public String getCity() {
        return city;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getPressure() {
        return pressure;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "city='" + city + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", temperature=" + temperature +
                ", feelsLikeTemperature=" + feelsLikeTemperature +
                ", windSpeed=" + windSpeed +
                ", pressure=" + pressure +
                ", expiryTime=" + expiryTime +
                '}';
    }
}
