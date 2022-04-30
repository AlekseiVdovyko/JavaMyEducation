package ru.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.edu.config.Config;
import ru.edu.dao.CityRepository;
import ru.edu.error.CustomException;
import ru.edu.error.Errors;
import ru.edu.service.image_and_geo.ImageAndGeoProvider;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static java.util.Objects.isNull;

@Component
public class CityService {

    private CityRepository repository;
    private ImageAndGeoProvider imageAndGeoProvider;
    Logger logger = LoggerFactory.getLogger(CityService.class);

    @Autowired
    public void setRepository(CityRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setImageProvider(ImageAndGeoProvider imageAndGeoProvider) {
        this.imageAndGeoProvider = imageAndGeoProvider;
    }

    /**
     * Get all.
     *
     * @author -  Филатов Руслан
     */
    public List<CityInfo> getAll() {
        logger.info("Method .getAll started.");
        List<CityInfo> cities = repository.getAll();
        logger.info("Method .getAll finished.");
        logger.info("Cities count is " + cities.size());
        return cities;
    }

    /**
     * Get city by id. Returns null if not found.
     *
     * @param cityId - city id
     * @throws ru.edu.error.CustomException with code CITY_NOT_FOUND if city doesn't exist
     * @author - Руслан Сейфетдинов
     */
    public CityInfo getCity(String cityId) {
        // System.out.println("CityService.getCity started");

        String temp = cityId;
        if (repository.getCity(cityId) == null) {
            throw new CustomException("Failed to CityRepository.getCity for cityId = " + temp, Errors.CITY_NOT_FOUND);
        }

        CityInfo cityInfo = repository.getCity(cityId);

        // System.out.println("CityService.getCity finished");

        return cityInfo;
    }

    /**
     * Create new city.
     *
     * @throws ru.edu.error.CustomException with code CITY_ALREADY_EXISTS if city with current id already exists
     * @throws ru.edu.error.CustomException with code ARGUMENT_ERROR if info is incorrect
     * @author - Борисов Захар
     */
    public CityInfo create(CityInfo info) {
        // проверям на присутсвие создаваемого города в базе
        CityInfo findCity = findCity(info);
            if ( Objects.nonNull(findCity) ) {
                throw new CustomException("Can not create() city with name=" + info.getName(), Errors.CITY_ALREADY_EXISTS);
            }

        if (cityInfoIsCorrect(info)) {
            throw new CustomException("Can not create() ERROR", Errors.ARGUMENT_ERROR);
        }
        // отдаем город на создание репозиторию
       repository.create(info);

       return findCity(info);
    }

    /**
     * Update existing city. Don't change id
     *
     * @throws ru.edu.error.CustomException with code CITY_NOT_FOUND if city doesn't exist
     * @throws ru.edu.error.CustomException with code ARGUMENT_ERROR if info is incorrect
     * @author - Соловьев Александр
     */
    public CityInfo update(CityInfo info) {

        System.out.println("Method CityService.update started");

        if (isNull(getCity(info.getId()))) {
            throw new CustomException("City " + info.getName() + " not found in database ERROR", Errors.CITY_NOT_FOUND);
        }
        if (info.getName().isEmpty()) throw new CustomException("ERROR: City " + info.getName() + " illegal argument for update 'Name' ", Errors.ARGUMENT_ERROR);
        if (info.getDescription().isEmpty()) throw new CustomException("ERROR: City " + info.getName() + " illegal argument for update 'Description' ", Errors.ARGUMENT_ERROR);
        if (info.getClimate().isEmpty()) throw new CustomException("ERROR: City " + info.getName() + " illegal argument for update 'Climate' ", Errors.ARGUMENT_ERROR);
        if (info.getPopulation() <= 0) throw new CustomException("ERROR: City " + info.getName() + " illegal argument for update 'Population' ", Errors.ARGUMENT_ERROR);
        if ( info.getLatitude() < -Math.PI / 2 || info.getLatitude() > Math.PI / 2 ) {
            throw new CustomException("ERROR: City " + info.getName() + " illegal argument for update 'Latitude' ", Errors.ARGUMENT_ERROR);
        }
        if ( info.getLongitude() < -Math.PI || info.getLongitude() > Math.PI ) {
            throw new CustomException("ERROR: City " + info.getName() + " illegal argument for update 'Longitude' ", Errors.ARGUMENT_ERROR);
        }
        // обновление info в репозитарии
        repository.update(info);

        System.out.println("Method CityService.update finished");

        return getCity(info.getId());
    }

    /**
     * Delete city by id
     *
     * @throws ru.edu.error.CustomException with code CITY_NOT_FOUND if city doesn't exist
     * @author - Вдовыко Алексей
     */
    public CityInfo delete(String cityId) {

        CityInfo info;

        if (Objects.isNull(repository.getCity(cityId))) {
            throw new CustomException("Город c ID=" + cityId + " отсутствует в базе данных", Errors.DELETECITYSERVICE_ERROR);
        }

        info = getCity(cityId);
        repository.delete(cityId);

        return info;
    }

    /**
     * Get city weather
     *
     * @throws ru.edu.error.CustomException with code CITY_NOT_FOUND if city doesn't exist
     * @author - Вдовыко Алексей
     */
    public String getWeather(String cityId) {

        if (Objects.isNull(repository.getCity(cityId))) {
            throw new CustomException("Город " + cityId + " отсутствует в базе данных", Errors.WEATHERCITYSERVICE_ERROR);
        }

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        WeatherCache cache = context.getBean(WeatherCache.class);

        CityInfo info = getCity(cityId);
        String cityName = info.getName();

        WeatherInfo weatherInfo = cache.getWeatherInfo(cityName);

        return "" + weatherInfo.getTemperature();
    }

    /**
     * Get city distance
     *
     * @throws ru.edu.error.CustomException with code CITY_NOT_FOUND if city doesn't exist
     * @author - Вдовыко Алексей
     */
    public String getDistance(String fromCityId, String toCityId) {

        if (Objects.isNull(repository.getCity(fromCityId)) || Objects.isNull(repository.getCity(toCityId))) {
            throw new CustomException("неверные значения в методе .getDistance", Errors.GETCITY_ERROR);
        }

        int distInt = 0;

        try {

            Double latitudeSource = repository.getCity(fromCityId).getLatitude();
            Double longitudeSource = repository.getCity(fromCityId).getLongitude();

            Double latitudeDest = repository.getCity(toCityId).getLatitude();
            Double longitudeDest = repository.getCity(toCityId).getLongitude();

            int earthRadius = 6372795;
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
            distInt = (functdoubToInt.apply(dist) / 1000);

            System.out.println(distInt);

        } catch (Exception ex) {
            throw new CustomException("Ошибка, не удалось вычислить расстояние между городами", Errors.GETDISTANCE_ERROR);
        }

        return distInt + "";
    }

    private boolean cityInfoIsCorrect(CityInfo info) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        WeatherProvider provider = context.getBean(WeatherProvider.class);

        return provider.get(info.getName()) == null ||
                info.getPopulation() < 0 ||
                (info.getLatitude() < -Math.PI / 2 || info.getLatitude() > Math.PI / 2) ||
                (info.getLongitude() < -Math.PI || info.getLongitude() > Math.PI);
    }

     public CityInfo findCity(CityInfo city) {
        List<CityInfo> ListOfCities = repository.getAll();
        for (CityInfo itemCity : ListOfCities) {
            if (city.equals(itemCity)) return itemCity;
        }
        return null;
    }

    public String getImageReference(String cityName) {

        return imageAndGeoProvider.getImageReference(cityName);
    }

    public double[] getGeoCode(String cityName) {

        return imageAndGeoProvider.getGeoCode(cityName);
    }
}