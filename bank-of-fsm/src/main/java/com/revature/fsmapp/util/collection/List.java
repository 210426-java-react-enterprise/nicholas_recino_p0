package com.revature.fsmapp.util.collection;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface List<E> extends Iterable<E>{

    default Stream<E> stream() { //we streamin' now bois
        return StreamSupport.stream(spliterator(), false);
    }
    void add(E object);
    boolean contains(E object);
    int size();
    E get(int index);
    void removeAt(int index);

}
