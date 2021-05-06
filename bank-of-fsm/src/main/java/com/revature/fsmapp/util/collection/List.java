package com.revature.fsmapp.util.collection;
// TODO Implement Proper List that's not linked
public interface List<E> {
    void add(E object);
    boolean contains(E object);
    int size();
    E get(int index);
    void removeAt(int index);

}
