package ru.edu;

import java.util.Objects;

public class Person {

    private String name;
    private String city;
    private Integer age;

    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!this.getClass().equals(obj.getClass())){
            return false;
        }

        Person other = (Person) obj;
        return name.equalsIgnoreCase(other.name) && city.equalsIgnoreCase(other.city) && age.equals(other.age);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.name.toLowerCase(), this.city.toLowerCase(), this.age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

}
