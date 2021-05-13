package com.revature.fsmapp.services; 

import com.revature.fsmapp.daos.AccountDAO;
import com.revature.fsmapp.daos.UserDAO;
import com.revature.fsmapp.models.AppUser;
import org.junit.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/** 
* LoginService Tester. 
* 
* @author Nicholas Recino 
* @since May 13, 2021
* @version 1.0 
*/ 
public class LoginServiceTest {

    private LoginService sut;
    private UserDAO mockUserDAO;

    @Before
    public void before() {
        mockUserDAO = mock(UserDAO.class);
        sut = new LoginService(mockUserDAO);
    }

    @After
    public void after(){
        mockUserDAO = null;
        sut = null;
    }


    /**
* 
* Method: verify(String password, String username) 
* 
*/ 
@Test
public void testVerify(){
    String testUserName = "TestUserName";
    String testPassword = "testaccountpass";
    AppUser expectedUser = new AppUser();
    expectedUser.setUserName(testUserName);
    expectedUser.setPassword(testPassword);
    AppUser actualUser = null;
    when(mockUserDAO.findUserByUsernameAndPassword(anyString(),anyString())).thenReturn(expectedUser);

    actualUser = sut.verify(testPassword,testUserName);

    assertEquals(actualUser,expectedUser);
} 


} 
