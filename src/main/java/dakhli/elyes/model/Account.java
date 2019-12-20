package dakhli.elyes.model;

import static dakhli.elyes.utils.CommonUtils.argumentNotNull;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Date;

public class Account {
	/**
	 * Account currency
	 */
	private CurrencyEnum currency;
	/**
	 * Account balance
	 */
	private BigDecimal balance;
	/**
	 * Account statement
	 */
	private Statement statement;

	/**
	 * Constructor
	 * 
	 * @param currency
	 */
	public Account(CurrencyEnum currency) {
		super();
		this.currency = currency;
		statement = new Statement(currency);
		balance = new BigDecimal(0);
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	/**
	 * Deposit an amount in the account.
	 * 
	 * @param amount deposit amount
	 * @param date   deposit date
	 * @return new account balance
	 */
	public BigDecimal deposit(BigDecimal amount, Date date) {
		argumentNotNull(amount, "Amount cannot be null");
		argumentNotNull(date, "Date cannot be null");

		Transaction transaction = new Transaction(date, amount, TransactionTypeEnum.CREDIT);
		applyTransaction(transaction);
		return balance;
	}

	/**
	 * Withdrawal an amount from the account.
	 * 
	 * @param amount withdrawal amount
	 * @param date   withdrawal date
	 * @return new account balance
	 */
	public BigDecimal withdrawal(BigDecimal amount, Date date) {
		argumentNotNull(amount, "Amount cannot be null");
		argumentNotNull(date, "Date cannot be null");

		Transaction transaction = new Transaction(date, amount, TransactionTypeEnum.DEBIT);
		applyTransaction(transaction);
		return balance;
	}

	/**
	 * Apply a {@link Transaction} to the account. A statement entry will be added
	 * to account statement.
	 * 
	 * @param transaction {@link Transaction} to apply.
	 */
	private void applyTransaction(Transaction transaction) {
		argumentNotNull(transaction, "Transaction cannot be null");

		applyTransactionToAmount(transaction);
		statement.addEntry(transaction, balance);
	}

	/**
	 * Adds or subtract a transaction amount from the account balance.<br>When
	 * {@link TransactionTypeEnum} is CREDIT transaction amount will be added to
	 * balance.<br>And when {@link TransactionTypeEnum} is DEBIT transaction amount
	 * will be subtracted from balance instead.
	 * 
	 * @param transaction
	 */
	private void applyTransactionToAmount(Transaction transaction) {
		argumentNotNull(transaction, "Transaction cannot be null");

		switch (transaction.getType()) {
		case CREDIT:
			balance = balance.add(transaction.getAmount());
			break;
		case DEBIT:
			balance = balance.subtract(transaction.getAmount());
			break;
		default:
			break;
		}
	}

	/**
	 * Prints the statement of the account.
	 * @param printer destination printer
	 */
	public void printStatement(PrintStream printer) {
		argumentNotNull(printer, "Printer is required to print statement");

		statement.print(printer);
	}

	// ***Getters and Setters
	public BigDecimal getBalance() {
		return balance;
	}

	public Statement getStatement() {
		return statement;
	}

	public boolean isEmptyStatement() {
		return statement.isEmpty();
	}

	public int statementSize() {
		return statement.size();
	}
}
