package ru.edu.lesson1;

public class ConverterTest {
    public static void main(String[] args) {

        DigitConverterImpl convert = new DigitConverterImpl();
        System.out.println(convert.convert(12, 3));

        DigitConverterImpl convert2 = new DigitConverterImpl();
        System.out.println(convert2.convert(0.12, 2, 11));
    }
}
