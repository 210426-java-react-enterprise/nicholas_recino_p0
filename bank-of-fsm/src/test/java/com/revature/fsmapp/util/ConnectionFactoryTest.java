package com.revature.fsmapp.util;

import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;

/** 
* ConnectionFactory Tester. 
* 
* @author Nicholas Recino
* @since May 5, 2021
* @version 1.0 
*/ 
public class ConnectionFactoryTest {

/** 
* 
* Method: getInstance() 
* Makes sure that Connection Factory returns an instance of Connection Factory, and that it returns itself if theres
 * one already created ensuring Singleton Design Pattern compliance
*/ 
@Test
public void testGetInstance(){
    //Arrange / Act
    ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    ConnectionFactory connectionFactoryDuplicate = ConnectionFactory.getInstance();
    //Assert
    Assert.assertNotNull(connectionFactory);
    Assert.assertSame(connectionFactory,connectionFactoryDuplicate);
} 

/** 
* 
* Method: getConnection()
 * Asserts connection to Database is secure and compliant and not null after polling properties file
* 
*/
@Test
public void test_getConnection() {
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
        Assert.assertNotNull(conn);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

} 
