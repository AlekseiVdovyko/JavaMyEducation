package ru.edu;

import org.junit.Assert;
import org.junit.Test;

public class DigitHandleTest {

    DigitHandler dh1 = new DigitHandler(324);
    DigitHandler dh2 = new DigitHandler(12);
    DigitHandler dh3 = new DigitHandler(12);
    DigitHandler dh4 = new DigitHandler(324);

    @Test
    public void hashTest() {

        Assert.assertEquals(355, dh1.hashCode());
        Assert.assertEquals(43, dh2.hashCode());
        Assert.assertEquals(43, dh3.hashCode());
        Assert.assertEquals(355, dh4.hashCode());

    }

    @Test
    public void equalsTest() {

        Assert.assertEquals(false, dh1.equals(dh2));
        Assert.assertEquals(true, dh2.equals(dh3));
        Assert.assertEquals(false, dh1.equals(dh3));
        Assert.assertEquals(true, dh4.equals(dh1));

    }
}
