### Требования:

1. Отрефакторить классы WeatherProvider, WeatherCache c помощью фреймворка Spring

2. Для конфигурирования использовать Java конфигурацию.
   Необходимо создать класс MyConfig, в котором будут определяться бины

3. Создать бин с типом RestTemplate

4. Создать бин, который реализует интерфейс WeatherProvider.

   - в данный бин необходимимо через setter-injection внедрить бин из пункта 3
   
   - в данный бин необходимимо через setter-injection внедрить свойство appKey. (key для сайта)
     Значение данного свойства поместить в файл src/main/resources/app.properties

5. Создать бин, который реализует интерфейс WeatherCache.

   - в данный бин необходимимо через setter-injection внедрить бин из пункта 4

## Критерии приемки:

!!! Обратите внимание на отсутствие конструктора ```WeatherCache(WeatherProvider provider)```.
<br>!!! Дополнительный конструктор не вводить

1. Бины создавать только с помощью Java конфигурации внутри класса-конфигурации MyConfig

2. Класс MyConfig содержит три бина

3. Следующий код работает и выводит надпись GOOD! в консоль

```java
public static void main(String[] args) {
   ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

   WeatherCache cache = context.getBean(WeatherCache.class);

   WeatherInfo weatherInfo = cache.getWeatherInfo("OMSK");
   System.out.println("GOOD! weather=" + weatherInfo);
}
```

5. Для загрузки данных из интернета пользоваться только Spring RestTemplate
	
6. Создать ПР из ветки feature/WeatherCacheImpl в ветку feature/WeatherCache

7. Структуру WeatherInfo, WeatherProvider, WeatherCache менять запрещено