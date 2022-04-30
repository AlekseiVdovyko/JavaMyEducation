package ru.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import ru.edu.error.CustomException;
import ru.edu.error.Errors;
import ru.edu.service.CityInfo;
import ru.edu.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityRestController {

    private CityService cityService;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Get all.
     *
     * @author -  Филатов Руслан
     */
    @GetMapping("/all")
    public List<CityInfo> getAll() {
        return cityService.getAll();
    }

    /**
     * Get city by id. Returns null if not found.
     *
     * @param cityId - item id
     * @author - Руслан Сейфетдинов
     */
    @GetMapping("/")
    public CityInfo getCity(@RequestParam("cityId") String cityId) {
        if(cityService.getCity(cityId) == null){
            return null;
        }
        return cityService.getCity(cityId);
    }

    /**
     * Create new city.
     *
     * @author - Борисов Захар
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CityInfo create(@RequestBody CityInfo info) {
        return cityService.create(info);
    }

    /**
     * Update existing city. Don't change id
     *
     * @author - Соловьев Александр
     */
    @PutMapping ("/update")
    public CityInfo update(@RequestBody CityInfo info) {
        return cityService.update(info);
    }

    /**
     * Delete city by id.
     *
     * @author - Вдовыко Алексей
     */
	@DeleteMapping("/")
    public CityInfo delete(@RequestParam("cityId") String cityId) {

        if (cityService.getCity(cityId) == null) {
            throw new CustomException("Город c ID="+ cityId +" отсутствует в базе данных", Errors.DELETECITYREST_ERROR);
        }

        return cityService.delete(cityId);
    }
}
