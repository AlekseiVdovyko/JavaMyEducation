package ru.edu.lesson1;

import java.util.*;

public class HobbyTest {
    public static void main(String[] args) {

        Map<String, Hobby> map = new HashMap<>();

        Hobby aleks = new Hobby("1", "Aleksei", "Student");

        map.put(aleks.getId(), aleks);
        //System.out.println(aleks);

        map.put("2", new Hobby("2", "Vdovyko", "Student"));


        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println("key=" + key + " value=" + map.get(key));
        }
    }
}
