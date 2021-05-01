package com.revature.fsmapp.util;

import com.revature.fsmapp.util.collection.DoublyLinkedList;

public class LinkedListTest {

    private DoublyLinkedList<String> sut;

    public void test_add_withNull(){
        // Arrange Test
        sut = new DoublyLinkedList<>();
        // Act Test
        try{
            sut.add(null);
            System.err.println("Test: test_add_withNull did not pass!");
        }catch(IllegalArgumentException e){
            // Assert Test
            System.out.println("Test: test_add_withNull passed!");
        }
    }

    public void test_add_withNonNullValue(){
        // Arrange
        sut = new DoublyLinkedList<>();
        // Act
        sut.add("not null!!");
        // Assert
        if(sut.size()==1)
            System.out.println("Success");
        else
            System.err.println("Failed");

    }

}
