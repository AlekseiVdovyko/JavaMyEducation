package ru.edu.lesson1;

import java.util.ArrayList;
import java.util.List;

public class DigitConverterImpl implements DigitConverter {

    private int digit;
    private int radix;
    private int precision = 0;

    public DigitConverterImpl() {
    }

    public DigitConverterImpl(int digit, int radix) {
        this.digit = digit;
        this.radix = radix;
    }

    public DigitConverterImpl(int digit, int radix, int precision) {
        this.digit = digit;
        this.radix = radix;
        this.precision = precision;
    }

    /**
     * Convert digit to another view.
     *
     * @param digit - digit
     * @param radix - radix
     * @throws IllegalArgumentException if digit is negative
     * @throws IllegalArgumentException if radix is not positive
     */
    public String convert(int digit, int radix) {
        if (digit < 0) {
            throw new IllegalArgumentException("digit is negative: " +digit);
        }
        else if (radix < 0) {
            throw new IllegalArgumentException("radix is not positive: "+ radix);
        }

        List<Integer> list = new ArrayList<Integer>();

        do {
            int tmp = digit % radix;
            list.add(tmp);

            digit = digit / radix;

        } while (digit != 0);

        String txt  = "";

        for (int i = list.size()-1; i >= 0; --i) {
            txt = txt + list.get(i);
            //System.out.print(list.get(i));
        }

        return txt;
    }

    /**
     * Convert double digit to another view.
     *
     * @param digit     - digit
     * @param radix     - radix
     * @param precision - how many letters after '.'
     * @throws IllegalArgumentException if digit is negative
     * @throws IllegalArgumentException if radix is not positive
     */
    public String convert(double digit, int radix, int precision) {
        if (digit < 0) {
            throw new IllegalArgumentException("digit is negative: " + digit);
        }
        else if (radix < 0) {
            throw new IllegalArgumentException("radix is not positive: " + radix);
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= precision; ++i) {
            digit = digit * 2;
            list.add((int) digit);
            digit = digit - (int) digit;
        }
        String txt = "0.";
        for (Integer letter : list) {
            txt = txt + letter;
        }

        return txt;
    }

}