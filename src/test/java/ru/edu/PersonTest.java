package ru.edu;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class PersonTest {

    List<Person> list = new ArrayList<>();

    @Test
    public void compareToTest() {
        list.add(new Person("Denis", "Moscow", 24));
        list.add(new Person("Ivan", "Samara", 26));
        list.add(new Person("Aleksei", "Moscow", 42));
        list.add(new Person("Ivan", "Anapa", 33));
        list.add(new Person("Aleksei", "Samara", 42));

        list.sort(Person::compareTo);

        Assert.assertEquals("[Person{name='Ivan', city='Anapa', age='33'}, " +
                "Person{name='Aleksei', city='Moscow', age='42'}, " +
                "Person{name='Denis', city='Moscow', age='24'}, " +
                "Person{name='Aleksei', city='Samara', age='42'}, " +
                "Person{name='Ivan', city='Samara', age='26'}]",
                list.toString());
    }

    @Test
    public void equalsTest() {

        Person person1 = new Person("Aleksei", "Saint-Petersburg", 42);
        Person person2 = new Person("AlekSei", "Saint-Petersburg", 42);
        Person person3 = new Person("Alina", "Saint-Petersburg", 42);

        Assert.assertEquals(true, person1.equals(person2));
        Assert.assertEquals(false, person1.equals(person3));
        Assert.assertEquals(false, person3.equals(person2));
    }

    @Test
    public void hashTest() {
        Person person1 = new Person("Aleksei", "Saint-Petersburg", 42);
        Person person2 = new Person("AlekSei", "Saint-Petersburg", 42);
        Person person3 = new Person("Alina", "Saint-Petersburg", 42);

        Assert.assertEquals(-1349134732, person1.hashCode());
        Assert.assertEquals(-1349134732, person2.hashCode());
        Assert.assertEquals(-600059169, person3.hashCode());

    }
}
