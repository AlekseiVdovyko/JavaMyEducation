package ru.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.config.Config;
import ru.edu.error.CustomException;
import ru.edu.error.Errors;
import ru.edu.service.*;
import ru.edu.utilities.FullCityInfo;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/travel")
public class CityController {

    private CityService cityService;
    private int distance;

    @Autowired
    public void setCityService(CityService cityService) {this.cityService = cityService;}

    /**
     * Get all cities view.
     *
     * @author -  Филатов Руслан
     */
    @GetMapping
    public ModelAndView getAllCitiesView() {
        ModelAndView modelAndView = new ModelAndView("travel");

        List<CityInfo> cities = cityService.getAll();
        ArrayList<FullCityInfo> citiesFullInfo = new ArrayList<>();

        for (CityInfo city: cities) {
            String temperature = cityService.getWeather(city.getId());
            citiesFullInfo.add(new FullCityInfo(city, temperature));
        }
        modelAndView.addObject("citiesFullInfo", citiesFullInfo);
        modelAndView.addObject("buttonName", "Подробнее");
        modelAndView.addObject("cityService", cityService);
        return modelAndView;
    }

    /**
     * Get city view.
     *
     * @param cityId - city id
     * @author - Руслан Сейфетдинов
     */
    @GetMapping("/city")
    public ModelAndView getCityView(@RequestParam("cityId") String cityId) {
        // System.out.println("CityController.getCityView started");

        CityInfo cityInfo = cityService.getCity(cityId);

        String lat = radiansToGradus(cityInfo.getLatitude());
        String lon = radiansToGradus(cityInfo.getLongitude());
		
		if (Math.signum(cityInfo.getLatitude()) == -1.0){
            lat = lat.substring(1) + " ю.ш.";
        }
        else {
            lat = lat + " с.ш.";
        }
        if (Math.signum(cityInfo.getLongitude()) == -1.0){
            lon = lon.substring(1) + " з.д.";
        }
        else {
            lon = lon + " в.д.";
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", cityInfo.getId());
        modelAndView.addObject("name", cityInfo.getName());
        modelAndView.addObject("description", cityInfo.getDescription());
        modelAndView.addObject("climate", cityInfo.getClimate());
        modelAndView.addObject("weather", cityService.getWeather(cityId));
        modelAndView.addObject("population", cityInfo.getPopulation());
        modelAndView.addObject("latitude", lat);
        modelAndView.addObject("longitude", lon);
        modelAndView.addObject("distance", distance);

        /*
        Поиск городов поблизости
         */

        String temp = ""; // Переменная temp для хранения вывода из метода CityService.getDistance();

        List<CityProvider> cityProviderList = new ArrayList<>();

        int citiesSize = cityService.getAll().size(); // Получение количества городов в БД

        List<Integer> listOfCityIds = new ArrayList<>(); // Создание списка ID городов из БД

        for(int j = 0; j < citiesSize; j++){
            if(Integer.valueOf(cityService.getAll().get(j).getId()) == null){
                continue;
            }
            if(Integer.valueOf(cityService.getAll().get(j).getId()) == Integer.parseInt(cityId)){ //исключение самого себя
                continue;
            }
            listOfCityIds.add(Integer.valueOf(cityService.getAll().get(j).getId()));
        }

        for(int i = 0; i < listOfCityIds.size() - 1; i++){
            int сityInListId = listOfCityIds.get(i);

            temp = cityService.getDistance(cityId, String.valueOf(listOfCityIds.get(i + 1)));
            /*
            Создаём объект CityProvider для занесения в список cityProviderList
             */

            CityProvider cityProvider = CityProvider.build()
                    .setCityFromId(cityId)
                    .setCityToId(String.valueOf(listOfCityIds.get(i + 1)))
                    .setCityFromName(cityService.getCity(cityId).getName())
                    .setCityToName(cityService.getCity(String.valueOf(listOfCityIds.get(i + 1))).getName())
                    .setDistance(String.valueOf(Integer.parseInt(temp)))
                    .build();

            /*
            Заносим в лист только города ближе 1000 км
             */

            if(Integer.parseInt(temp) < distance) {
                cityProviderList.add(cityProvider);
            }
        }


        modelAndView.addObject("cityProviderList", cityProviderList);
        modelAndView.setViewName("/cityInfo");
        /**
         * Строки 148-152 добавил временно для тестирования получения изображений города
         */
        String imgSrc = cityService.getImageReference(cityService.getCity(cityId).getName());
        modelAndView.addObject("imgSrc", imgSrc);

        // System.out.println("CityController.getCityView finished");
        return modelAndView;
    }

    /**
     * Update city.
     *
     * @author - Соловьев Александр
     */
    @GetMapping("/edit")
    public ModelAndView getCityEdit(@RequestParam("cityId") String cityId, String message) {

        String msg = message;
        CityInfo cityInfo = cityService.getCity(cityId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", cityInfo.getId());
        modelAndView.addObject("name", cityInfo.getName());
        modelAndView.addObject("description", cityInfo.getDescription());
        modelAndView.addObject("climate", cityInfo.getClimate());
        modelAndView.addObject("population", (cityInfo.getPopulation()));
        modelAndView.addObject("latitude", cityInfo.getLatitude());
        modelAndView.addObject("longitude", cityInfo.getLongitude());
        modelAndView.addObject("msg", msg);

        modelAndView.setViewName("/cityEdit");
        return modelAndView;
    }
    /**
     * Update city.
     *
     * @author - Соловьев Александр
     */
    @PostMapping("/edit")
    public ModelAndView editCity(CityInfo cityInfo) {
        String msg = "";
        CityInfo newCity = null;
        try {
            newCity = cityService.update(cityInfo);
        } catch (CustomException ex){
            msg = ex.getMessage();
            return getCityEdit(cityInfo.getId(), msg);
        }
        return getCityView(newCity.getId());
    }

    /**
     * Get create new city view.
     *
     * @author - Борисов Захар
     */
    @GetMapping("/create")
    public ModelAndView getCreateCityView() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create");
        return modelAndView;
    }

    /**
     * Create new city.
     *
     * @author - Соловьев Александр
     */
    @PostMapping("/create")
    public ModelAndView createCity(CityInfo info, @RequestParam("population_str") String populationStr) {

        ModelAndView modelAndView = new ModelAndView();
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        WeatherCache cache = context.getBean(WeatherCache.class);

        WeatherInfo weatherInfo = cache.getWeatherInfo(info.getName());

        if (weatherInfo == null) {
            modelAndView.addObject("cityName", info.getName());
            modelAndView.setViewName("error_1");
            return modelAndView;
        }else if (populationStr.equals("")) {
            modelAndView.setViewName("error_3");
            return modelAndView;
        } else if (!populationStr.matches("\\d+") || Integer.parseInt(populationStr) < 0) {
            modelAndView.addObject("population", populationStr);
            modelAndView.setViewName("error_2");
            return modelAndView;
        }

        double latitude = cityService.getGeoCode(info.getName())[0];
        double longitude = cityService.getGeoCode(info.getName())[1];

        info.setLatitude(Math.toRadians(latitude));
        info.setLongitude(Math.toRadians(longitude));
        info.setPopulation(Integer.parseInt(populationStr));
        CityInfo newCity = cityService.create(info);

        return getCityView(newCity.getId());
    }

    @PostMapping("/delete")
    public ModelAndView delete(String cityId) {

        cityService.delete(cityId);
        return getAllCitiesView();
    }

    @Value("${cityController.distance}")
    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Double gradusToRadians(String gradusStr){
        if(gradusStr.replaceAll("[^0-9]", "").length() < 2){
            throw new CustomException("Not a coordinates = " + gradusStr, Errors.ARGUMENT_ERROR);
        }
        String marker = ".";
        String numberOnly = gradusStr.replaceAll("[^0-9]", marker);
        while(numberOnly.substring(0, 1).equals(marker)){ //отпиливаем лишние символы в начале
            numberOnly = numberOnly.substring(1);
        }
        while(numberOnly.substring(numberOnly.length() - 1).equals(marker)){ //отпиливаем лишние символы в конце
            numberOnly = numberOnly.substring(0, numberOnly.length() - 1);
        }
        numberOnly = numberOnly + marker; // добавляем в конец строки заделитель для цикла парсинга

        Double totalLatitude = null;

        Double a = null;
        Double b = null;
        Double c = null;

        String temp;

        for (int i = 0; i < 3; i++){
            if(numberOnly.length() == 0){
                break;
            }
            int strClose = numberOnly.indexOf(marker);
            temp = numberOnly.substring(0, strClose);
            if(i == 0){
                a = Double.parseDouble(temp);
            }
            if(i == 1){
                b = Double.parseDouble(temp);
            }
            if(i == 2) {
                c = Double.parseDouble(temp);
            }
            numberOnly = numberOnly.substring(strClose + 1);
        }
//            totalLatitude = a + (b / 60) + (c / 3600);

        if(a != null){
            totalLatitude = a;
        }
        if(b != null){
            totalLatitude = totalLatitude + (b / 60);
        }
        if(c != null){
            totalLatitude = totalLatitude + (c / 3600);
        }
        return  totalLatitude * 3.1415926535 / 180;
    }

    public String radiansToGradus(Double radians){
        Double PI = 3.1415926535;

        String lat;
        String tmp;

        Double latitude = (180 / PI * radians);
        tmp = String.valueOf((int) (latitude - latitude%1));
        lat = tmp + "\u00B0";
        latitude = latitude%1;
        latitude = latitude * 60;
        tmp = String.valueOf((int)(latitude - latitude%1));
        if(tmp.length() < 2){
            tmp = "0" + tmp;
        }
        lat = lat + tmp + "\u0027";
        latitude = latitude%1;
        latitude = (latitude) * 60;
        tmp = String.valueOf((int)(latitude - latitude%1));
        if(tmp.length() < 2){
            tmp = "0" + tmp;
        }
        lat = lat + tmp + "\u0027" + "\u0027";

        return lat;
    }
    @ExceptionHandler
    public ModelAndView errorHandler(CustomException ex, HttpServletRequest request){

        String trace = ex.fillInStackTrace().getMessage();
        String httpStatus = HttpStatus.BAD_REQUEST.toString();
        return getErrorView(ex.getCode(), OffsetDateTime.now().toString(), httpStatus, request.getRequestURI(), trace);
    }

    private ModelAndView getErrorView(String message, String timestamp, String error, String url, String trace){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", message);
        modelAndView.addObject("timestamp", timestamp);
        modelAndView.addObject("error", error);
        modelAndView.addObject("url", url);
        modelAndView.addObject("trace", trace);
        return modelAndView;
    }
}
