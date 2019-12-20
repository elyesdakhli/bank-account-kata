package dakhli.elyes.services;

import static dakhli.elyes.utils.CommonUtils.argumentNotNull;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Date;

import dakhli.elyes.model.Account;

public class AccountServiceImpl implements AccountService{
	
	@Override
	public BigDecimal withdrawal(Account account, BigDecimal amount, Date date) {
		argumentNotNull(account, "Account is required");
		argumentNotNull(amount, "Amount is required");
		
		if(amount.compareTo(new BigDecimal(0)) == 1) {
			account.withdrawal(amount, date);
		}
		return account.getBalance();
	}
	
	@Override
	public BigDecimal deposit(Account account, BigDecimal amount, Date date) {
		argumentNotNull(account, "Account is required");
		argumentNotNull(amount, "Amount is required");
		
		if(amount.compareTo(new BigDecimal(0)) == 1) {
			account.deposit(amount, date);
		}
		return account.getBalance();
	}

	@Override
	public void printStatement(Account account, PrintStream printer) {
		argumentNotNull(account, "Account is required");
		argumentNotNull(printer, "Printer is required");
		
		account.printStatement(printer);
	}

	

}
