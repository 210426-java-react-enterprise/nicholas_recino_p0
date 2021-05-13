package com.revature.fsmapp.util.collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayListTest {

    private ArrayList<Integer> sut;

    @Before
    public void setup() {
        sut = new ArrayList<>();
    }

    @After
    public void teardown() {
        sut = null;
    }

    @Test
    public void test_add() {
        //arrange;
        sut.add(1);
        sut.add(2);

        //Act
        sut.add(3);
        int expectedResult = 3;
        int actualResult = 0;
        actualResult = sut.size();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_getWithValidIndex() {
        //Arrange
        sut.add(1);
        sut.add(2);
        sut.add(3);


        //Act
        int expectedResult = 2; // the second element of the array
        int actualResult = 0;
        actualResult = sut.get(1); // zero-based

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_getWithInvalidIndexPositive() {
        //Arrange
        sut.add(1);
        sut.add(2);
        sut.add(3);

        try {
            sut.get(3); // get the 4th index that does not exist
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    @Test
    public void test_getWithInvalidIndexNegative() {
        //Arrange
        sut.add(1);
        sut.add(2);
        sut.add(3);

        try {
            sut.get(-1); //try to get a negative index
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    @Test
    public void test_removeAtWithValidIndex() {
        //Arrange
        sut.add(1);
        sut.add(2);
        sut.add(3);

        //Act
        int before = 0;
        int beforeSize = 0;
        beforeSize = sut.size();

        before = sut.get(2); //third index

        sut.removeAt(2);

        int afterSize = 0;
        afterSize = sut.size();

        try {
            sut.get(2); //should throw IndexOutOfBoundsException now that there is no third element.
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        } finally {
            assertEquals(3, before); //assert that there actually was a third index
            assertEquals(3, beforeSize); //size before removal
            assertEquals(2, afterSize); //size after removal
        }

    }

    @Test
    public void test_removeAtWithInvalidIndexPositive() {
        //Arrange
        sut.add(1);
        sut.add(2);
        sut.add(3);

        //Act
        try {
            sut.removeAt(3);
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    @Test
    public void test_removeAtWithInvalidIndexNegative() {
        //Arrange
        sut.add(1);
        sut.add(2);
        sut.add(3);

        //Act
        try {
            sut.removeAt(-1);
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    @Test
    public void test_increaseCapacity() {
        for(int i = 0; i < 15; i++) {
            sut.add(i);
        }

        assertEquals(15, sut.size());

    }

    @Test
    public void test_Iterator() {

        sut.add(1);
        sut.add(2);
        sut.add(3);

        Iterator<Integer> i = sut.iterator();

        assertNotNull(i);
        assertTrue(i.hasNext());
        assertEquals(1, (int) i.next());
        assertEquals(2, (int) i.next());
        assertEquals(3, (int) i.next());
        assertFalse(i.hasNext());
    }

}