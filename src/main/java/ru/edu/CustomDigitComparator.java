package ru.edu;

import java.util.Comparator;

public class CustomDigitComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer lhs, Integer rhs) {

        if (lhs == null && rhs == null) {
            throw new IllegalArgumentException ("Items is null");
        }

        if (lhs.equals((rhs))) {
            return 0;
        }
        if (lhs < rhs) {
            return -1;
        }
        if ((lhs % 2) < (rhs % 2)) {
            return -1;
        } else {
            return 1;
        }
    }
}
