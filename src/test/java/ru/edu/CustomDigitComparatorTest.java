package ru.edu;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomDigitComparatorTest {

    Comparator<Integer> comparator = new CustomDigitComparator();

    List<Integer> list = new ArrayList<>();

    @Test
    public void compareTest() {
        list.add(3);
        list.add(1);
        list.add(7);
        list.add(4);
        list.add(8);
        list.add(5);
        list.add(2);
        list.add(9);
        list.add(6);

        list.sort(comparator);

        Assert.assertEquals("[2, 4, 6, 8, 1, 3, 5, 7, 9]", list.toString());

    }
}
