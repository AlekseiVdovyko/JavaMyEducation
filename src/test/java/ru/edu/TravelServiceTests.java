package ru.edu;

import org.junit.Assert;
import org.junit.Test;

public class TravelServiceTests {

    private TravelService travServ = new TravelService();


    @Test
    public void addTest() {

        travServ.add((new CityInfo("Moscow",
                new GeoPosition("55(45'7.9'')", "37(36'56.2''"))));
        Assert.assertEquals("TravelService{cities=[CityInfo{name='Moscow', " +
                        "position=GeoPosition{latitude = 0.973059358, longitude = 0.656516264}}]}",
                travServ.toString());

        travServ.add(new CityInfo("Saint-Petersburg",
                new GeoPosition("59(56'19'')", "30(18'50.8''")));
        Assert.assertEquals("TravelService{cities=[CityInfo{name='Moscow', " +
                        "position=GeoPosition{latitude = 0.973059358, longitude = 0.656516264}}, " +
                        "CityInfo{name='Saint-Petersburg', " +
                        "position=GeoPosition{latitude = 1.046126113, longitude = 0.529081049}}]}",
                travServ.toString());

        travServ.add(new CityInfo("Vladivostok",
                new GeoPosition("43(6'20.2'')", "131(52'26.4''")));
        Assert.assertEquals("TravelService{cities=[CityInfo{name='Moscow', " +
                        "position=GeoPosition{latitude = 0.973059358, longitude = 0.656516264}}, " +
                        "CityInfo{name='Saint-Petersburg', " +
                        "position=GeoPosition{latitude = 1.046126113, longitude = 0.529081049}}, " +
                        "CityInfo{name='Vladivostok', " +
                        "position=GeoPosition{latitude = 0.75233484, longitude = 2.301635498}}]}",
                travServ.toString());

        try {
            travServ.add((new CityInfo("Moscow",
                    new GeoPosition("55(45'7.9'')", "37(36'56.2''"))));
        } catch (IllegalArgumentException ex) {

            Assert.assertEquals("City \"CityInfo{name='Moscow', " +
                    "position=GeoPosition{latitude = 0.973059358, longitude = 0.656516264}}\" " +
                    "already exists", ex.getMessage());
        }
    }

    @Test
    public void removeTest() {

        travServ.add(new CityInfo("Moscow",
                new GeoPosition("55(45'7.9'')", "37(36'56.2''")));
        travServ.add(new CityInfo("Saint-Petersburg",
                new GeoPosition("59(56'19'')", "30(18'50.8''")));
        travServ.add(new CityInfo("Vladivostok",
                new GeoPosition("43(6'20.2'')", "131(52'26.4''")));
        travServ.add(new CityInfo("New York",
                new GeoPosition("40(42'51.5'')", "-74(0'21.6''")));
        travServ.add(new CityInfo("Sydney",
                new GeoPosition("-33(52'4.4'')", "151(12'25.2''")));

        Assert.assertEquals("TravelService{cities=[CityInfo{name='Moscow', " +
                        "position=GeoPosition{latitude = 0.973059358, longitude = 0.656516264}}, " +
                        "CityInfo{name='Saint-Petersburg', " +
                        "position=GeoPosition{latitude = 1.046126113, longitude = 0.529081049}}, " +
                        "CityInfo{name='Vladivostok', " +
                        "position=GeoPosition{latitude = 0.75233484, longitude = 2.301635498}}, " +
                        "CityInfo{name='New York', " +
                        "position=GeoPosition{latitude = 0.710598685, longitude = -1.291438927}}, " +
                        "CityInfo{name='Sydney', " +
                        "position=GeoPosition{latitude = -0.560811135, longitude = 2.639060002}}]}",
                travServ.toString());

        travServ.remove("Moscow");
        travServ.remove("Saint-Petersburg");
        travServ.remove("Vladivostok");
        travServ.remove("New York");

        Assert.assertEquals("TravelService{cities=[CityInfo{name='Sydney', " +
                        "position=GeoPosition{latitude = -0.560811135, longitude = 2.639060002}}]}",
                travServ.toString());

        try {
            travServ.remove("Moscow");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("City \"Moscow\" doesn't exist", ex.getMessage());
        }
    }

    @Test
    public void citiesNamesTest() {

        travServ.add(new CityInfo("Moscow",
                new GeoPosition("55(45'7.9'')", "37(36'56.2''")));
        travServ.add(new CityInfo("Saint-Petersburg",
                new GeoPosition("59(56'19'')", "30(18'50.8''")));
        travServ.add(new CityInfo("Vladivostok",
                new GeoPosition("43(6'20.2'')", "131(52'26.4''")));
        travServ.add(new CityInfo("New York",
                new GeoPosition("40(42'51.5'')", "-74(0'21.6''")));
        travServ.add(new CityInfo("Sydney",
                new GeoPosition("-33(52'4.4'')", "151(12'25.2''")));

        Assert.assertEquals("[Moscow, Saint-Petersburg, Vladivostok, New York, Sydney]",
                travServ.citiesNames().toString());

        Assert.assertTrue(travServ.citiesNames().contains("Moscow"));
        Assert.assertTrue(travServ.citiesNames().contains("Saint-Petersburg"));
        Assert.assertTrue(travServ.citiesNames().contains("Vladivostok"));
        Assert.assertTrue(travServ.citiesNames().contains("New York"));
        Assert.assertTrue(travServ.citiesNames().contains("Sydney"));
    }

    @Test
    public void getDistanceTest() {

        travServ.add(new CityInfo("Moscow",
                new GeoPosition("55(45'7.9'')", "37(36'56.2''")));
        travServ.add(new CityInfo("Saint-Petersburg",
                new GeoPosition("59(56'19'')", "30(18'50.8''")));
        travServ.add(new CityInfo("Vladivostok",
                new GeoPosition("43(6'20.2'')", "131(52'26.4''")));
        travServ.add(new CityInfo("New York",
                new GeoPosition("40(42'51.5'')", "-74(0'21.6''")));
        travServ.add(new CityInfo("Sydney",
                new GeoPosition("-33(52'4.4'')", "151(12'25.2''")));


        Assert.assertEquals(634,
                travServ.getDistance("Moscow", "Saint-Petersburg"));
        Assert.assertEquals(0,
                travServ.getDistance("Moscow", "Moscow"));
        Assert.assertEquals(6417,
                travServ.getDistance("Moscow", "Vladivostok"));
        Assert.assertEquals(15911,
                travServ.getDistance("New York", "Sydney"));

        try {
            travServ.getDistance("Moscow", "Maiami");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("Source or destination city doesn't exist", ex.getMessage());
        }

        try {
            travServ.getDistance("Petrograd", "Saint-Petersburg");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("Source or destination city doesn't exist", ex.getMessage());
        }
    }

    @Test
    public void getCitiesNearTest() {

        travServ.add(new CityInfo("Moscow",
                new GeoPosition("55(45'7.9'')", "37(36'56.2''")));
        travServ.add(new CityInfo("Saint-Petersburg",
                new GeoPosition("59(56'19'')", "30(18'50.8''")));
        travServ.add(new CityInfo("Vladivostok",
                new GeoPosition("43(6'20.2'')", "131(52'26.4''")));
        travServ.add(new CityInfo("New York",
                new GeoPosition("40(42'51.5'')", "-74(0'21.6''")));
        travServ.add(new CityInfo("Sydney",
                new GeoPosition("-33(52'4.4'')", "151(12'25.2''")));

        Assert.assertEquals("[Saint-Petersburg]",
                travServ.getCitiesNear("Moscow", 1000).toString());
        Assert.assertEquals("[Saint-Petersburg, Vladivostok]",
                travServ.getCitiesNear("Moscow", 6500).toString());
        Assert.assertEquals("[Saint-Petersburg, Vladivostok, New York]",
                travServ.getCitiesNear("Moscow", 10000).toString());
        Assert.assertEquals("[Saint-Petersburg, Vladivostok, New York, Sydney]",
                travServ.getCitiesNear("Moscow", 15000).toString());

        try {
            travServ.getCitiesNear("Magadan", 10000);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("City with \"Magadan\" city doesn't exist", ex.getMessage());
        }
    }
}
