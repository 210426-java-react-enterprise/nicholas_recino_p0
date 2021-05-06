package com.revature.fsmapp.util.collection; 

import org.junit.*;

/** 
* ArrayList Tester. 
* 
* @author Nicholas Recino 
* @since May 5, 2021
* @version 1.0 
*/ 
public class ArrayListTest {

    private ArrayList<String> sut;

@Before
public void before() {
   sut = new ArrayList<>();
} 

@After
public void after(){
    sut = null;
} 

/** 
* 
* Method: add(E objectToAdd) 
* 
*/ 
@Test
public void testAdd(){ 
    //Arrange
    int expectedSize = 1;
    //Act
    sut.add("Test");
    //Assert
    Assert.assertEquals(expectedSize,sut.size());
} 

/** 
* 
* Method: contains(E object) 
* 
*/ 
@Test
public void testActuallyContains(){
    //Arrange
    sut.add("Test3");
    sut.add("Test4");
    sut.add("Test1");
    sut.add("Test2");
    sut.add("Test3");
    sut.add("Test4");
    //Act
    boolean contained = sut.contains("Test3");
    //Assert
    Assert.assertTrue(contained);
}
    /**
     *
     * Method: contains(E object)
     *
     */
    @Test
    public void testNotContains(){
        //Arrange
        sut.add("Test3");
        sut.add("Test4");
        sut.add("Test1");
        sut.add("Test2");
        sut.add("Test3");
        sut.add("Test4");
        //Act
        boolean contained = sut.contains("Test7");
        //Assert
        Assert.assertFalse(contained);
    }

    /**
* 
* Method: size() 
* 
*/ 
@Test
public void testSize(){
    //Arrange
    int expectedSize = 4;
    sut.add("Test1");
    sut.add("Test2");
    sut.add("Test3");
    sut.add("Test4");
    //Act
    int currentSize = sut.size();
    //Assert
    Assert.assertEquals(expectedSize,currentSize);
} 

/** 
* 
* Method: get(int index) 
* 
*/ 
@Test(expected = IndexOutOfBoundsException.class)
public void testGetWithIndexPastBounds(){
    //Arrange
    int incorrectIndex = 5;
    sut.add("Test1");
    sut.add("Test2");
    sut.add("Test3");
    sut.add("Test4");
    //Act
    sut.get(incorrectIndex);
    //Assert

}
/**
 *
 * Method: get(int index)
 *
 */
@Test
public void testGet(){
    //Arrange
    String expectedElement = "Test3";
    sut.add("Test1");
    sut.add("Test2");
    sut.add("Test3");
    sut.add("Test4");
    //Act
    String actualElement = sut.get(2);
    //Assert
    Assert.assertEquals(expectedElement,actualElement);
}

/**
* 
* Method: removeAt(int index) 
* 
*/ 
@Test
public void testRemoveAt(){
    //Arrange
    sut.add("Test1");
    sut.add("Test2");
    sut.add("Test3");
    sut.add("Test4");
    int expectedSize = sut.size()-1;
    //Act
    sut.removeAt(3);
    int currentSize = sut.size();
    //Assert
    Assert.assertEquals(expectedSize,currentSize);
} 


/** 
* 
* Method: increaseCapacity() 
* 
*/ 
@Test
public void testIncreaseCapacity(){
    //Arrange
    sut.add("Test1");
    sut.add("Test2");
    sut.add("Test3");
    sut.add("Test4");
    sut.add("Test5");
    sut.add("Test6");
    sut.add("Test7");
    sut.add("Test8");
    sut.add("Test9");
    sut.add("Test10");
    int expectedSize = 11;
    //Act
    sut.add("Test11");
    int currentSize = sut.size();
    //Assert
    Assert.assertEquals(expectedSize,currentSize);

} 

} 
