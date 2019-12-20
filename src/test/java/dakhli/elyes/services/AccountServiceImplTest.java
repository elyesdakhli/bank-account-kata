package dakhli.elyes.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import dakhli.elyes.model.Account;
import dakhli.elyes.model.CurrencyEnum;

public class AccountServiceImplTest {
	
	private AccountService accountService = new AccountServiceImpl();
	
	
	@Test
	public void makeADepositInTheAccount() {
		Account account = new Account(CurrencyEnum.EURO);
		BigDecimal depositAmount = new BigDecimal(200);
		BigDecimal balance = accountService.deposit(account, depositAmount, new Date());
		
		assertNotNull("Account must not be null after deposit", account);
		assertEquals("Wrong balance value after deposit", depositAmount, balance);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionIfDepositWithoutAccount() {
		accountService.deposit(null, new BigDecimal(500), new Date());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionIfDepositWithoutAmount() {
		accountService.deposit(new Account(CurrencyEnum.EURO),null, new Date());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionIfDepositWithoutDate() {
		accountService.deposit(new Account(CurrencyEnum.EURO), new BigDecimal(500), null);
	}
	
	@Test
	public void makeAWithdrawalFromTheAccount() {
		Account account = new Account(CurrencyEnum.EURO);
		BigDecimal withdrawalAmount = new BigDecimal(700);
		BigDecimal balance = accountService.withdrawal(account, withdrawalAmount, new Date());
		
		assertNotNull("Account must not be null after deposit", account);
		assertEquals("Wrong balance value after deposit", withdrawalAmount.multiply(new BigDecimal(-1)), balance);
	}
	
	@Test
	public void makeWithdrawalAndDeposit() {
		Account account = new Account(CurrencyEnum.EURO);
		accountService.deposit(account, new BigDecimal(1500), new Date());
		
		BigDecimal balance = accountService.withdrawal(account, new BigDecimal(1000), new Date());
		assertEquals("Account balance is worng",new BigDecimal(500), balance);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionIfWithdrawalWithoutAccount() {
		accountService.withdrawal(null, new BigDecimal(500), new Date());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionIfWithdrawalWithoutAmount() {
		accountService.withdrawal(new Account(CurrencyEnum.EURO),null, new Date());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionIfWithdrawalWithoutDate() {
		accountService.withdrawal(new Account(CurrencyEnum.EURO), new BigDecimal(500), null);
	}
	
	@Test
	public void printTheAccountStatement() {
		Account mockedAccount = mock(Account.class);
		
		accountService.printStatement(mockedAccount, System.out);
		verify(mockedAccount).printStatement(System.out);
		
	}

}
