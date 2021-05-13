package com.revature.fsmapp.services; 

import com.revature.fsmapp.daos.AccountDAO;
import org.junit.*;
import static org.mockito.Mockito.*;

/** 
* AccountService Tester. 
* 
* @author Nicholas Recino 
* @since May 13, 2021
* @version 1.0 
*/ 
public class AccountServiceTest {
    private AccountService sut;
    private AccountDAO mockAccountDAO;

@Before
public void before() {
    mockAccountDAO = mock(AccountDAO.class);
    sut = new AccountService(mockAccountDAO);
} 

@After
public void after(){
    mockAccountDAO = null;
    sut = null;
} 

/** 
* 
* Method: openAccount(AppUser user, String pin, double balance) 
* 
*/ 
@Test
public void testOpenAccount(){ 
//TODO: Test goes here... 
} 

/** 
* 
* Method: linkAccount(AppUser appUser, int accountNumber) 
* 
*/ 
@Test
public void testLinkAccount(){ 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getUserAccounts(AppUser user) 
* 
*/ 
@Test
public void testGetUserAccounts(){ 
//TODO: Test goes here... 
} 

/** 
* 
* Method: validateAccount(List<Account> accounts, int accountNumber) 
* 
*/ 
@Test
public void testValidateAccount(){ 
//TODO: Test goes here... 
} 

/** 
* 
* Method: recordTransaction(String sender, int sendersID, int recipientID, double amount) 
* 
*/ 
@Test
public void testRecordTransaction(){ 
//TODO: Test goes here... 
} 

/** 
* 
* Method: accountExists(int accountID) 
* 
*/ 
@Test
public void testAccountExists(){ 
//TODO: Test goes here... 
} 

/** 
* 
* Method: subtractBalance(double amount, int accountID, Account account) 
* 
*/ 
@Test
public void testSubtractBalance(){ 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addBalance(double amount, int recipientID, Account account) 
* 
*/ 
@Test
public void testAddBalance(){ 
//TODO: Test goes here... 
} 


/** 
* 
* Method: verifyValidOpeningBalance(double balance) 
* 
*/ 
@Test
public void testVerifyValidOpeningBalance(){ 
//TODO: Test goes here... 
/* 
try { 
   Method method = AccountService.getClass().getMethod("verifyValidOpeningBalance", double.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: verifyValidPin(String pin) 
* 
*/ 
@Test
public void testVerifyValidPin(){ 
//TODO: Test goes here... 
/* 
try { 
   Method method = AccountService.getClass().getMethod("verifyValidPin", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
