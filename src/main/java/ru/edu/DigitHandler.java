package ru.edu;

import java.util.Objects;

public class DigitHandler {

    private int value;

    public DigitHandler(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        DigitHandler other = (DigitHandler) obj;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        return "DigitHandler{" +
                "value=" + value +
                '}';
    }
}
