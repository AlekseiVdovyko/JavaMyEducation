Участники проекта TravelApp

- Филатов Руслан Анатольевич
- Руслан Юсипович Сейфетдинов
- Борисов Захар Андреевич
- Соловьев Александр Иванович
- Вдовыко Алексей Игоревич

CityInfo

String id
String name
String description
double latitude;  // radians
double longitude; // radians
int population;

ТЗ

1. TravelController path="/travel".

@GetMapping
ModelAndView getAll() {...}

@GetMapping("/city")
ModelAndView getCityInfo(@RequestParam("cityId") String cityId) {...}

@GetMapping("/city/create")
ModelAndView getCreateCityView() {...}

@PostMapping("/city/create")
ModelAndView createCity(@RequestBody CityInfo info) {...}

2. CityRestController path="/api/city"

@GetMapping("/all")
List<CityInfo> getAll() {...}

@GetMapping
CityInfo getCity(@RequestParam("cityId") String cityId)

@PostMapping("/create")
CityInfo create(@RequestBody CityInfo city)

@PostMapping("/update")
CityInfo update(@RequestBody CityInfo city)

@PostMapping("/delete")
CityInfo delete(@RequestParam("cityId") String cityId)

3. CityService (бизнес логика)

List<CityInfo> getAll() {...}

CityInfo getCity(String cityId)

CityInfo create(CityInfo city)

CityInfo update(CityInfo city)

CityInfo delete(String cityId)

*** String getWeather(String cityId)

*** String getDistance(String fromCityId, String toCityId)

4. CityRepository (Работа с БД)

List<CityInfo> getAll() {...}

CityInfo getCity(String cityId)

CityInfo create(CityInfo city)

CityInfo update(CityInfo city)

CityInfo delete(String cityId)

Критерии приемки

1. Оценка 3 - Spring MVC or SpringBoot + JSP

- Реализованы классы CityRepository + CityService + TravelService (без учета ***)

- Тестовые значения хранятся в БД. Значения можно менять только в БД

2. Оценка 4 - CityRestController

Значения можно менять через REST (Postman, SoapUI, ...)

3. Оценка 5 - Страницы TravelApp содержат дополнительную информацию

- информацию о погоде
- информацию о городах поблизости (1000 км). Километраж настраивается в конфигурационном файле
