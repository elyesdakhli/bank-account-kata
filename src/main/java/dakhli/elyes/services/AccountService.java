package dakhli.elyes.services;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Date;

import dakhli.elyes.model.Account;

public interface AccountService {

	/**
	 * Withdrawal an amount from an account
	 * @param account the account
	 * @param amount withdrawal amount
	 * @param date withdrawal date
	 * @return the new balance of the account
	 */
	BigDecimal withdrawal(Account account, BigDecimal amount, Date date);

	/**
	 * Deposit an amount to an account
	 * @param account the account
	 * @param amount Deposit amount 
	 * @param date deposit date
	 * @return the new balance of the account
	 */
	BigDecimal deposit(Account account, BigDecimal amount, Date date);
	
	/**
	 * Print the actual statement of the account
	 * @param account Account to print its statement
	 * @param printer Statement printer
	 */
	void printStatement(Account account, PrintStream printer);

}
