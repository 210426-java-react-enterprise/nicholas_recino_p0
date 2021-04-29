package com.revature.fsmapp.util.collection;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class ArrayList implements Collection {

    private Object[] arrayList;
    private int currentSize;

    public ArrayList(){
        super();
        arrayList = new Object[currentSize = 10];
    }

    public ArrayList(int sizeRequired){
        super();
        arrayList = new Object[currentSize=sizeRequired];
    }

    public int size(){
        return currentSize;
    }
//  As the list
    @Override
    public boolean isEmpty() {
        return (arrayList[0]==null);
    }

    @Override
    public boolean contains(Object o) {
        for(Object search:arrayList){
            if(search.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public boolean add(Object o) {
        int index = 0;
        for(Object o1:arrayList){
            if(o1==null){
                arrayList[index] = o;
                return true;
            }else{
                index++;
            }
        }
        index = 0;
        Object[] tempArr = new Object[currentSize*2];
        for(Object o1:arrayList){
             tempArr[index++] = o1;
            }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = 0;
        boolean success = false;
        for(Object o1:arrayList){
            if(o1.equals(o1)){
                arrayList[index] = null;
                success = true;
                for (int i = index; i < currentSize; i++) {

                }
            }else{
                index++;
            }
        }
        return success;
    }

    @Override
    public void clear() {
        arrayList = new Object[currentSize=10];
    }

//    Methods below deprecated for this application

    @Override
    public boolean addAll(Collection c) {
        return false;
    }
    @Override
    public boolean retainAll(Collection c) {
        return false;
    }
    @Override
    public boolean removeAll(Collection c) {
        return false;
    }
    @Override
    public boolean containsAll(Collection c) {
        return false;
    }
    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
    @Override
    public Object[] toArray() {
        return new Object[0];
    }
    @Override
    public Iterator iterator() {
        return null;
    }
}
