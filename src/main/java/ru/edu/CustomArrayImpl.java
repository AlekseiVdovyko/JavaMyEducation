package ru.edu;

import java.util.Arrays;
import java.util.Collection;

public class CustomArrayImpl<T> implements CustomArray<T> {

    public int size = 0;
    private int capacity;
    private T[] list;

    CustomArrayImpl() {
        this.capacity = 0;
        this.list = (T[]) new Object[capacity];
        this.size = 0;
    }

    CustomArrayImpl(int capacity) {
        if (capacity <= 0) {
            System.out.println("Wrong capacity. Set default capacity.");
            capacity = 0;
        }
        else this.capacity = capacity;
        this.list = (T[]) new Object[capacity];
        this.size = 0;
    }

    CustomArrayImpl(Collection<T> c) {
        this.size = c.size();
        this.capacity = c.size();;
        this.list = (T[]) c.toArray();
    }

    @Override
    public int size() {
        if (size == capacity) {
            System.out.println("Capacity is full");
            ensureCapacity(capacity);
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            System.out.println("The list does not contain data");
        }
        return true;
    }

    /**
     * Add single item.
     */
    @Override
    public boolean add(T item) {
        size += 1;
        if (capacity <= 0) {
            capacity = 1;
            ensureCapacity(capacity);
        }
        list[size-1] = item;
        System.out.println("add:" + item);
        size();
        return true;
    }

    /**
     * Add all items.
     *
     * @throws IllegalArgumentException if parameter items is null
     */
    @Override
    public boolean addAll(T[] items) {
        T[] listAddAll = (T[]) new Object[items.length];
        if (items == null) {
            throw new IllegalArgumentException ("Items is null");
        }
        try {
            for (int i = 0; i < items.length; i++) {
                listAddAll[i] = items[i];
            }
        } catch (Exception ex) {
            System.out.println("Error: Incorrect index");
        }
        size = items.length;
        capacity = items.length;
        list = listAddAll;
        return true;
    }


    /**
     * Add all items.
     *
     * @throws IllegalArgumentException if parameter items is null
     */

    @Override
    public boolean addAll(Collection<T> items) {
        T[] listAddAll = (T[]) new Object[size + items.size()];
        T[] listAddAll2 = (T[]) items.toArray();
        for (int i = 0; i < size; i++) {
            listAddAll[i] = list[i];
        }
        for (int j = 0; j < items.size(); j++) {
            listAddAll[size+j] = listAddAll2[j];
        }
        list = listAddAll;
        size += items.size();
        return true;
    }

    /**
     * Add items to current place in array.
     *
     * @param index - index
     * @param items - items for insert
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     * @throws IllegalArgumentException       if parameter items is null
     */
    @Override
    public boolean addAll(int index, T[] items) {
        T[] listAddAll = (T[]) new Object[size + items.length];
        if (items == null) {
            throw new IllegalArgumentException ("Items is null");
        }
        if (list == null || index < 0) {
            throw new ArrayIndexOutOfBoundsException ("Index is out of bounds");
        }

        try {
            int a = 0;
            for (int i = 0; i < listAddAll.length; i++) {
                if (i == index) {
                    for (int j = 0; j < items.length; j++) {
                        listAddAll[a + j] = items[j];
                        i++;
                    }
                    i--;
                } else {
                    listAddAll[i] = list[a];
                    a++;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: Incorrect index");
        }
        size += items.length;
        capacity += items.length;
        list = listAddAll;
        return true;
    }

    /**
     * Get item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public T get(int index) {
        T listGetIndex = (T) new Object[0];
        if (index > size-1 || index > size-1 || list == null || index < 0) {
            throw new ArrayIndexOutOfBoundsException ("Index is out of bounds");
        }

        try {
            for (int i = 0; i < list.length; i++) {
                if (list[i] == list[index]) {
                    listGetIndex = list[i];
                    continue;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: Incorrect index");
        }
        return listGetIndex;
    }

    /**
     * Set item by index.
     *
     * @param index - index
     * @return old value
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public T set(int index, T item) {
        T[] listSet = (T[]) new Object[size];
        T oldValue = (T) new Object[1];
        if (index > size-1 || index > size-1 || list == null || index < 0) {
            throw new ArrayIndexOutOfBoundsException ("Index is out of bounds");
        }
        try {
            for (int i = 0, k = 0; i < size; i++) {
                if (i == index) {
                    oldValue = list[i];
                }
                if (i == index) {
                    listSet[i] = item;
                }
                else listSet[i] = list[i];
            }
        } catch (Exception ex) {
            System.out.println("Error: Incorrect index");
        }
        list = listSet;
        return oldValue;
    }

    /**
     * Remove item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public void remove(int index) {
        T[] listRemove = (T[]) new Object[size-1];
        if (index > size-1 || list == null || index < 0) {
            throw new ArrayIndexOutOfBoundsException ("Index is out of bounds");
        }
        try {
            for (int i = 0, k = 0; i < size; i++) {
                if (i == index) {
                    continue;
                }
                listRemove[k++] = list[i];
            }
            list = listRemove;
            size -= 1;
        } catch (Exception ex) {
            System.out.println("Error: Incorrect index");
        }
    }

    /**
     * Remove item by value. Remove first item occurrence.
     *
     * @param item - item
     * @return true if item was removed
     */
    @Override
    public boolean remove(T item) {
        T[] listRemove = null;
        for (int i = 0; i < size; i++) {
            if (list[i] == item) {
                listRemove = (T[]) new Object[size-1];
                for (int index = 0; index < i; index++) {
                    listRemove[index] = list[index];
                }
                for (int j = i; j < size-1; j++) {
                    listRemove[j] = list[j + 1];
                }
                break;
            }
        }
        list = listRemove;
        size -= 1;
        return true;
    }

    /**
     * Checks if item exists.
     *
     * @param item - item
     * @return true or false
     */
    @Override
    public boolean contains(T item) {
        boolean contain = false;
        for (int i = 0; i < size; i++) {
            if (list[i] == item) {
                contain = true;
            }
        }
        return contain;
    }

    /**
     * Index of item.
     *
     * @param item - item
     * @return index of element or -1 of list doesn't contain element
     */
    @Override
    public int indexOf(T item) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (list[i] == item) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Grow current capacity to store new elements if needed.
     *
     * @param newElementsCount - new elements count
     */
    @Override
    public void ensureCapacity(int newElementsCount) {
        capacity += newElementsCount;
        T[] listCapacity = (T[]) new Object[capacity];
        for (int i = 0; i < list.length; i++) {
            listCapacity[i] = list[i];
        }
        list = listCapacity;
        System.out.println("Current capacity growing at " + newElementsCount + " elements");

    }

    /**
     * Get current capacity.
     */
    @Override
    public int getCapacity() {
        System.out.print("Current capacity: ");
        return capacity;
    }

    /**
     * Reverse list.
     */
    @Override
    public void reverse() {
        T[] listReverse = (T[]) new Object[size];
        int a = 0;
        for (int i = size-1; i >= 0; i--) {
            listReverse[a] = list[i];
            a++;
        }
        list = listReverse;
    }

    /**
     * Get content in format '[ element1 element2 ... elenentN ] or [ ] if empty.
     */
    @Override
    public String toString() {
        String result = "[ ";
        for (int i = 0; i < size; i++) {
            result += list[i] + " ";
        }
        result += "]";
        return result;
    }

    /**
     * Get copy of current array.
     */
    @Override
    public Object[] toArray() {
        T[] copy = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            copy[i] = list[i];
        }
        return copy;
    }
}