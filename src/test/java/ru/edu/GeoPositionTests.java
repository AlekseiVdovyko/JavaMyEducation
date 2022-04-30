package ru.edu;

import org.junit.Test;
import org.junit.Assert;

public class GeoPositionTests {

    GeoPosition geoPosition1 = new GeoPosition("55(45'07'')", "12(02'07'')");
    GeoPosition geoPosition2 = new GeoPosition("55", "12");
    GeoPosition geoPosition3 = new GeoPosition("-55(00'02'')", "12(00'00'')");
    GeoPosition geoPosition4 = new GeoPosition("55", "-12");
    GeoPosition geoPosition5 = new GeoPosition("-59(57'00'')", "-59(57'00'')");

    @Test
    public void converterTest() {

        Assert.assertEquals("GeoPosition{latitude = 0.973054995, longitude = 0.210055224}", geoPosition1.toString());
        Assert.assertEquals("GeoPosition{latitude = 0.959931089, longitude = 0.20943951}", geoPosition2.toString());
        Assert.assertEquals("GeoPosition{latitude = -0.959921392, longitude = 0.20943951}", geoPosition3.toString());
        Assert.assertEquals("GeoPosition{latitude = 0.959931089, longitude = -0.20943951}", geoPosition4.toString());
        Assert.assertEquals("GeoPosition{latitude = -1.013163631, longitude = -1.013163631}", geoPosition5.toString());

    }
}
