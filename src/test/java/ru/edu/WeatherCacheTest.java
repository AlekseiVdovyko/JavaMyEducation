package ru.edu;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import java.time.LocalDateTime;

import static org.mockito.Matchers.anyString;

/**
 * Unit test for simple App.
 */
public class WeatherCacheTest {

    @Test
    public void wrongCity() {

        WeatherProvider provider = new WeatherProvider();
        WeatherCache cache = new WeatherCache(provider);

        Assert.assertEquals(null, cache.getWeatherInfo("aaaa"));
        Assert.assertEquals(null, cache.getWeatherInfo("  "));
        Assert.assertEquals(null, cache.getWeatherInfo("1234"));


    }

    @Test
    public void getInfo() {

        WeatherProvider repoMock = Mockito.mock(WeatherProvider.class);
        WeatherCache cache = new WeatherCache(repoMock);

        Mockito.when(repoMock.get(anyString())).thenReturn(new WeatherInfo().builder().
                setCity("Moscow").
                setShortDescription("Clouds").
                setDescription("overcast clouds").
                setTemperature(-10.45).
                setFeelsLikeTemperature(-17.45).
                setWindSpeed(6.72).
                setPressure(760.0).
                setExpiryTime(LocalDateTime.now()).
                build());

        WeatherInfo info2 = cache.getWeatherInfo("Moscow");

        Assert.assertFalse(info2.toString().isEmpty());
        Assert.assertTrue(cache.getCache().containsKey("Moscow"));
        Assert.assertFalse(cache.getCache().containsKey("Anapa"));
        Assert.assertFalse(cache.getCache().containsKey("Tomsk"));

        Assert.assertEquals(null, cache.getWeatherInfo("Tomsk"));

        Mockito.when(repoMock.get(anyString())).thenReturn(new WeatherInfo().builder().
                setCity("Tomsk").
                setShortDescription("Clouds").
                setDescription("overcast clouds").
                setTemperature(-5.45).
                setFeelsLikeTemperature(-7.45).
                setWindSpeed(1.5).
                setPressure(746.0).
                setExpiryTime(LocalDateTime.now()).
                build());
        WeatherInfo info4 = cache.getWeatherInfo("Tomsk");

        Assert.assertFalse(info4.toString().isEmpty());
        Assert.assertTrue(cache.getCache().containsKey("Tomsk"));
        Assert.assertTrue(cache.getCache().containsKey("Moscow"));
        Assert.assertFalse(cache.getCache().containsKey("Anapa"));
    }

    @Test
    public void updateInfo() {
        WeatherProvider repoMock = Mockito.mock(WeatherProvider.class);
        WeatherCache cache = new WeatherCache(repoMock);

        Mockito.when(repoMock.get(anyString())).thenReturn(new WeatherInfo().builder().
                setCity("Moscow").
                setShortDescription("Clouds").
                setDescription("overcast clouds").
                setTemperature(-10.45).
                setFeelsLikeTemperature(-17.45).
                setWindSpeed(6.72).
                setPressure(760.0).
                setExpiryTime(LocalDateTime.now()).
                build());
        WeatherInfo info = cache.getWeatherInfo("Moscow");

        Mockito.when(repoMock.get(anyString())).thenReturn(new WeatherInfo().builder().
                setCity("Tomsk").
                setShortDescription("Clouds").
                setDescription("overcast clouds").
                setTemperature(-5.45).
                setFeelsLikeTemperature(-7.45).
                setWindSpeed(1.5).
                setPressure(746.0).
                setExpiryTime(LocalDateTime.now()).
                build());
        WeatherInfo info1 = cache.getWeatherInfo("Tomsk");

        Mockito.when(repoMock.get(anyString())).thenReturn(new WeatherInfo().builder().
                setCity("Tomsk").
                setShortDescription("Clear").
                setDescription("clear").
                setTemperature(-5.45).
                setFeelsLikeTemperature(-7.45).
                setWindSpeed(1.5).
                setPressure(746.0).
                setExpiryTime(LocalDateTime.now().minusMinutes(4)).
                build());
        WeatherInfo info2 = cache.getWeatherInfo("Tomsk");

        Assert.assertEquals(LocalDateTime.now().minusMinutes(4), info2.getExpiryTime());

    }

    @Test
    public void removeCity() {
        WeatherProvider repoMock = Mockito.mock(WeatherProvider.class);
        WeatherCache cache = new WeatherCache(repoMock);

        Mockito.when(repoMock.get(anyString())).thenReturn(new WeatherInfo().builder().
                setCity("Moscow").
                setShortDescription("Clouds").
                setDescription("overcast clouds").
                setTemperature(-10.45).
                setFeelsLikeTemperature(-17.45).
                setWindSpeed(6.72).
                setPressure(760.0).
                setExpiryTime(LocalDateTime.now()).
                build());
        WeatherInfo info = cache.getWeatherInfo("Moscow");

        Mockito.when(repoMock.get(anyString())).thenReturn(new WeatherInfo().builder().
                setCity("Tomsk").
                setShortDescription("Clouds").
                setDescription("overcast clouds").
                setTemperature(-5.45).
                setFeelsLikeTemperature(-7.45).
                setWindSpeed(1.5).
                setPressure(746.0).
                setExpiryTime(LocalDateTime.now()).
                build());
        WeatherInfo info1 = cache.getWeatherInfo("Tomsk");

        Mockito.when(repoMock.get(anyString())).thenReturn(new WeatherInfo().builder().
                setCity("Tomsk").
                setShortDescription("").
                setDescription("").
                setTemperature(0).
                setFeelsLikeTemperature(0).
                setWindSpeed(0).
                setPressure(0).
                setExpiryTime(LocalDateTime.now().minusMinutes(4)).
                build());

        WeatherInfo info2 = cache.getWeatherInfo("Tomsk");

        Assert.assertFalse(info.toString().isEmpty());
        Assert.assertTrue(info2.toString().isEmpty());

    }
}
