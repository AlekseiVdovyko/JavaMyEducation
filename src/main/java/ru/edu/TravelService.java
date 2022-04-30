package ru.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        if (citiesNames().contains(cityInfo.getName())) {
            throw new IllegalArgumentException ("City \"" + cityInfo + "\" already exists");
        }
        cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {

        if (!citiesNames().contains(cityName)) {
            throw new IllegalArgumentException ("City \"" + cityName + "\" doesn't exist");
        }

        Predicate<CityInfo> predicate = (CityInfo list) -> {
            if (list.getName().equalsIgnoreCase(cityName)) {
                return true;
            } else {
                return false;
            }
        };

        cities.removeIf(predicate);
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {

        List<String> citiesName = cities.stream()
                .filter((CityInfo list) -> {
                    if (list.getName().contains("")) {
                        return true;
                    } else {
                        return false;
                    }
                }).map((CityInfo list) -> {
                    return list.getName();
                })
                .collect(Collectors.toList());

        return citiesName;
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public int getDistance(String srcCityName, String destCityName) {

        if (!citiesNames().contains(srcCityName) || !citiesNames().contains(destCityName)) {
            throw new IllegalArgumentException ("Source or destination city doesn't exist");
        }

        int earthRadius = 6372795;

        Function<String, Double> function = (String text) -> {
            return Double.valueOf(text);
        };

        List<String> sourceLatitudeList = cities.stream()
                .filter(txt -> txt.getName().equalsIgnoreCase(srcCityName))
                .map(txt -> "" + txt.getPosition().getLatitude())
                .collect(Collectors.toList());
        Double latitudeSource = function.apply(sourceLatitudeList.get(0));

        List<String> sourceLongitudeList = cities.stream()
                .filter(txt -> txt.getName().equalsIgnoreCase(srcCityName))
                .map(txt -> "" + txt.getPosition().getLongitude())
                .collect(Collectors.toList());
        Double longitudeSource = function.apply(sourceLongitudeList.get(0));

        List<String> destLatitudeList = cities.stream()
                .filter(txt -> txt.getName().equalsIgnoreCase(destCityName))
                .map(txt -> "" + txt.getPosition().getLatitude())
                .collect(Collectors.toList());
        Double latitudeDest = function.apply(destLatitudeList.get(0));

        List<String> destLongitudeList = cities.stream()
                .filter(txt -> txt.getName().equalsIgnoreCase(destCityName))
                .map(txt -> "" + txt.getPosition().getLongitude())
                .collect(Collectors.toList());
        Double longitudeDest = function.apply(destLongitudeList.get(0));

        double cosLat = Math.cos(latitudeSource);
        double cosLong = Math.cos(latitudeDest);
        double sinLat = Math.sin(latitudeSource);
        double sinLong = Math.sin(latitudeDest);
        double delta = longitudeDest - longitudeSource;
        double cosDelta = Math.cos(delta);
        double sinDelta = Math.sin(delta);

        double y = Math.sqrt(Math.pow(cosLong * sinDelta, 2) +
                Math.pow(cosLat * sinLong - sinLat * cosLong * cosDelta, 2));

        double x = sinLat * sinLong + cosLat * cosLong * cosDelta;

        double ad = Math.atan2(y, x);
        double dist = ad * earthRadius;

        Function<Double, Integer> functdoubToInt = (Double db) -> {
            return Integer.valueOf(db.intValue());
        };
        int distInt = (functdoubToInt.apply(dist) / 1000);

        return distInt;
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {

        if (!citiesNames().contains(cityName)) {
            throw new IllegalArgumentException ("City with \"" + cityName + "\" city doesn't exist");
        }

        List<String> allCiti = citiesNames();
        List<Integer> allDistance = new ArrayList<>();
        List<Integer> getDistance = allCiti.stream()
                        .map((String nameCity) -> {
                            allDistance.add(getDistance(cityName, nameCity));
                            return 0;
                        })
                        .collect(Collectors.toList());
        List<String> allCities = cities.stream()
                .filter((CityInfo list) -> {
                    if (list.getName().contains("")) {
                        return true;
                    } else {
                        return false;
                    }
                }).map((CityInfo list) -> {
                    return list.getName();
                })
                .collect(Collectors.toList());
        List<String> cityList = new ArrayList<>();
        List<Integer> distList = new ArrayList<>();
        for (int i = 0; i < allCities.size(); i++) {
            distList.add(getDistance(cityName, allCities.get(i)));
            if (distList.get(i) <= radius && distList.get(i) != 0) {
                cityList.add(allCities.get(i));
            }
        }
        return cityList;
    }

    @Override
    public String toString() {
        return "TravelService{" +
                "cities=" + cities +
                '}';
    }

}
