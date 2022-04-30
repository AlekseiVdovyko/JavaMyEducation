package ru.edu;

import java.util.function.Function;

/**
 * Geo position.
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    /**
     * Ctor.
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        this.latitude = converter(latitudeGradus);
        this.longitude = converter(longitudeGradus);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private double converter(String position) {
        String[] txt = position.split("\\(|\\)|'|''");

        Function<String, Double> function = (String text) -> {
            return Double.valueOf(text);
        };

        Double gradus = 0.0;
        gradus = function.apply(txt[0]);

        Double minutes = 0.0;
        for (int i = 1; i < txt.length-1; i ++) {
            minutes = function.apply(txt[i]);
        }

        Double seconds = 0.0;
        for (int i = 2; i < txt.length; i ++) {
            seconds = function.apply(txt[i]);
        }

        double radian = (((gradus + ((minutes + (seconds / 60))/60)) * Math.PI) / 180);
        radian = Math.round(radian * 1000000000.0) / 1000000000.0;

        return radian;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude = " + latitude +
                ", longitude = " + longitude +
                '}';
    }
}
