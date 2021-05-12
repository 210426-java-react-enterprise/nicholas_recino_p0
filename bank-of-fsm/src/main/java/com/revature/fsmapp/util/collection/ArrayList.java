package com.revature.fsmapp.util.collection;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {
    private E[] arrayList;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        int DEFAULT_CAPACITY = 10;
        this.arrayList = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        this.arrayList = (E[])new Object[capacity];
    }

    /**
     * Adds an element to the back of the array. Doubles the size of the array if the size is full.
     * @param objectToAdd The data to be added
     */
    public void add(E objectToAdd) {
        if (size == arrayList.length)
            capacityIncrease();
        arrayList[size++] = objectToAdd;
    }

    @Override
    public boolean contains(E object) {
        boolean identical = false;
        for(int i=0;i<size;i++){
            if(arrayList[i].equals(object)) {
                identical = true;
                break;
            }
        }
        return identical;
    }

    /**
     * Increases the size of the array to allow for additional elements
     */
    private void capacityIncrease() {
        int expandedArray = arrayList.length * 2;
        this.arrayList = Arrays.copyOf(arrayList, expandedArray);
    }

    /**
     *
     * @return Current size of the array
     */
    public int size() {
        return this.size;
    }

    /**
     * Gets the element at the specified index.
     * @param index Positional Index of Element in the array
     * @return The Data contained inside the Element at index.
     * @exception  IndexOutOfBoundsException if the Index is not contained in the array list
     */
    public E get(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException(String.format("Index: %d Size: %d", index, size));
        return (E) arrayList[index];
    }


    /**
     * Removes the Element at index in the array
     * @param index The index of the Element to be removed
     */
    @SuppressWarnings("unchecked")
    public void removeAt(int index) {
        E[] arrayCopy = (E[]) new Object[arrayList.length - 1];

        for (int i = 0, j = 0; i < arrayList.length; i++) {
            if (i != index)
                arrayCopy[j++] = arrayList[i];
        }
        arrayList = arrayCopy;
        size--;
    }

}

