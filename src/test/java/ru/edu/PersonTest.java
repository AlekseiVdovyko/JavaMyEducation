package ru.edu;

import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

    public Person person1 = new Person("Aleksei", "Saint-Petersburg", 42);
    public Person person2 = new Person("AleKSei", "Saint-PETERsburg", 42);
    public Person person3 = new Person("Maksim", "Saint-Petersburg", 42);
    public Person person4 = new Person("Aleksei", "Moscow", 42);
    public Person person5 = new Person("MAKSIM", "SAINT-PETERSBURG", 42);

    @Test
    public void toStringTest() {

        Assert.assertEquals("Person{name='Aleksei', city='Saint-Petersburg', age='42'}", person1.toString());
        Assert.assertEquals("Person{name='MAKSIM', city='SAINT-PETERSBURG', age='42'}", person5.toString());
    }

    @Test
    public void equalsTest() {

        Assert.assertEquals(true, person1.equals(person2));
        Assert.assertEquals(false, person1.equals(person3));
        Assert.assertEquals(false, person1.equals(person4));
        Assert.assertEquals(false, person3.equals(person4));
        Assert.assertEquals(true, person5.equals(person3));
    }

    @Test
    public void hashTest() {

        Assert.assertEquals(-1349134732, person1.hashCode());
        Assert.assertEquals(-1349134732, person2.hashCode());
        Assert.assertEquals(368174958, person3.hashCode());
        Assert.assertEquals(-422459883, person4.hashCode());
        Assert.assertEquals(368174958, person5.hashCode());

    }
}
