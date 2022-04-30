package ru.edu.service;

import java.util.Locale;
import java.util.Objects;

public class CityInfo {

    private String id;
    private String name;
    private String description;
    private String climate;
    private int population;
    private double latitude;  // radians
    private double longitude; // radians

    public static Builder build() {
        return new Builder();
    }

    public CityInfo() {
    }

    public static class  Builder {

        private CityInfo cityInfo = new CityInfo();

        public Builder setId(String id) {
            cityInfo.id = id;
            return this;
        }

        public Builder setName(String name) {
            cityInfo.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            cityInfo.description = description;
            return  this;
        }

        public Builder setClimate(String climate) {
            cityInfo.climate = climate;
            return  this;
        }

        public Builder setPopulation(int population) {
            cityInfo.population = population;
            return this;
        }

        public Builder setLatitude(double latitude) {
            cityInfo.latitude = latitude;
            return this;
        }

        public Builder setLongitude(double longitude) {
            cityInfo.longitude = longitude;
            return this;
        }

        public CityInfo build() {
            return cityInfo;
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", climate='" + climate + '\'' +
                ", population=" + population +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {return false;}
        if (this == obj) {return true;}
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        CityInfo other = (CityInfo) obj;
        // сравнение по ключевому полю Name
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

}