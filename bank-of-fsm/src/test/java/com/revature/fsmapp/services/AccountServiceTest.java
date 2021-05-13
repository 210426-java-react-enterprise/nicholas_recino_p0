package com.revature.fsmapp.services; 

import com.revature.fsmapp.daos.AccountDAO;
import com.revature.fsmapp.exceptions.AccountNotFoundException;
import com.revature.fsmapp.models.Account;
import com.revature.fsmapp.models.AppUser;
import com.revature.fsmapp.util.collection.ArrayList;
import com.revature.fsmapp.util.collection.List;
import org.junit.*;

import javax.naming.InsufficientResourcesException;

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
* Method: linkAccount(AppUser appUser, int accountNumber)
*
*/
@Test
public void testLinkAccount(){
    when(mockAccountDAO.linkAccount(anyInt(),anyInt())).thenReturn(true);
    when(mockAccountDAO.accountOpen(anyInt())).thenReturn(true);

    boolean test = sut.linkAccount(new AppUser(),1);

    assertTrue(test);
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
@Test(expected = AccountNotFoundException.class)
public void testValidateAccount() throws AccountNotFoundException {
    List<Account> accounts = new ArrayList<>();
    Account account = new Account();
    account.setAccountId(1,"4");
    accounts.add(account);

    Account capturedAccount = sut.validateAccount(accounts,1);

    assertSame(account,capturedAccount);

} 

/**
*
* Method: accountExists(int accountID)
*
*/
@Test
public void testAccountExists(){
    when(mockAccountDAO.accountExists(anyInt())).thenReturn(true);
    boolean test = sut.accountExists(1);
    assertTrue(test);

}

/** 
* 
* Method: subtractBalance(double amount, int accountID, Account account) 
* 
*/ 
@Test(expected = InsufficientResourcesException.class )
public void testSubtractBalance() throws InsufficientResourcesException {
    sut.subtractBalance(100,1,new Account());
}

@Test
public void testSubtractWithFunds() throws InsufficientResourcesException {
    Account account = new Account();
    account.setBalance(200);
    double amount = 100;
    sut.subtractBalance(amount,1,account);

    assertEquals(account.getBalance(),amount,0);
    }

    /**
* 
* Method: addBalance(double amount, int recipientID, Account account) 
* 
*/ 
@Test
public void testAddBalance(){ 
    sut.addBalance(100,1,new Account());
} 

} 
