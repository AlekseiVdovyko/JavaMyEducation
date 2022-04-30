package ru.edu.lesson1;

/**
 * Digit converter to another view.
 */
public interface DigitConverter {

    /**
     * Convert digit to another view.
     *
     * @param digit - digit
     * @param radix - radix
     * @throws IllegalArgumentException if digit is negative
     * @throws IllegalArgumentException if radix is not positive
     */
    String convert(int digit, int radix);

    /**
     * Convert double digit to another view.
     *
     * @param digit     - digit
     * @param radix     - radix
     * @param precision - how many letters after '.'
     * @throws IllegalArgumentException if digit is negative
     * @throws IllegalArgumentException if radix is not positive
     */
    String convert(double digit, int radix, int precision);
}
