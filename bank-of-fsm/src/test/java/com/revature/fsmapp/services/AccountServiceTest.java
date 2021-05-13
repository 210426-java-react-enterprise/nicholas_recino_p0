package com.revature.fsmapp.services; 

import com.revature.fsmapp.daos.AccountDAO;
import com.revature.fsmapp.models.Account;
import com.revature.fsmapp.models.AppUser;
import org.junit.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

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
    String pin = "1234";
    AppUser user = new AppUser();
    double amount = 100;
    Account expectedAccount = new Account(amount,-1,pin);

    Account actualAccount = null;
    when(mockAccountDAO.accountExists(anyInt())).thenReturn(true);
    when(mockAccountDAO.accountOpen(anyInt())).thenReturn(true);

    actualAccount = sut.openAccount(user,pin,amount);

    assertEquals(expectedAccount.getAccountId(),actualAccount.getAccountId());


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

} 
