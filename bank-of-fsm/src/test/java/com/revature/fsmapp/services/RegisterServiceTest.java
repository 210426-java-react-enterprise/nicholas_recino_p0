package com.revature.fsmapp.services; 

import com.revature.fsmapp.daos.UserDAO;
import com.revature.fsmapp.models.AppUser;
import org.junit.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/** 
* RegisterService Tester. 
* 
* @author Nicholas Recino 
* @since May 13, 2021
* @version 1.0 
*/ 
public class RegisterServiceTest {

    private RegisterService sut;
    private UserDAO mockUserDAO;

    @Before
    public void before() {
        mockUserDAO = mock(UserDAO.class);
        sut = new RegisterService(mockUserDAO);
    }

    @After
    public void after(){
        mockUserDAO = null;
        sut = null;
    }

/** 
* 
* Method: validatePotentialUserInfo(String password, String username, String email) 
* 
*/ 
@Test
public void testValidatePotentialUserInfo(){
    when(mockUserDAO.findUserByUsernameAndPassword(anyString(),anyString())).thenReturn(new AppUser());
    when(mockUserDAO.findUserByEmail(anyString())).thenReturn(new AppUser());

    boolean test = sut.validatePotentialUserInfo("password!","JohnDoe123","nickrecino@gmail.com");

    assertTrue(test);
} 

/** 
* 
* Method: registerUser(AppUser user) 
* 
*/ 
@Test
public void testRegisterUser(){ 
//TODO: Test goes here... 
} 




} 
