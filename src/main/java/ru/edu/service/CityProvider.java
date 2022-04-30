package ru.edu.service;

public class CityProvider {
    private String cityFromId;
    private String cityToId;
    private String cityFromName;
    private String cityToName;
    private String distance;

    public static Builder build(){
        return new Builder();
    }

    public static class Builder{

        private CityProvider obj = new CityProvider();

        public Builder setCityFromId(String cityFromId){
            obj.cityFromId = cityFromId;
            return this;
        }

        public Builder setCityToId(String cityToId){
            obj.cityToId = cityToId;
            return this;
        }

        public Builder setCityFromName(String cityFromName){
            obj.cityFromName = cityFromName;
            return this;
        }

        public Builder setCityToName(String cityToName){
            obj.cityToName = cityToName;
            return this;
        }

        public Builder setDistance(String distance){
            obj.distance = distance;
            return this;
        }

        public CityProvider build(){
            return obj;
        }

    }

    public String getCityFromId() {
        return cityFromId;
    }

    public void setCityFromId(String cityFromId) {
        this.cityFromId = cityFromId;
    }

    public String getCityToId() {
        return cityToId;
    }

    public void setCityToId(String cityToId) {
        this.cityToId = cityToId;
    }

    public String getCityFromName() {
        return cityFromName;
    }

    public void setCityFromName(String cityFromName) {
        this.cityFromName = cityFromName;
    }

    public String getCityToName() {
        return cityToName;
    }

    public void setCityToName(String cityToName) {
        this.cityToName = cityToName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "CityProvider{" +
                "cityFromId='" + cityFromId + '\'' +
                ", cityToId='" + cityToId + '\'' +
                ", cityFromName='" + cityFromName + '\'' +
                ", cityToName='" + cityToName + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
