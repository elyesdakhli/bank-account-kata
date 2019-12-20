package dakhli.elyes.model;

import static dakhli.elyes.utils.CommonUtils.argumentNotNull;
import static dakhli.elyes.utils.CommonUtils.rightPad;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class StatementEntry {
	/**Statement print column separator */
	private static final String PRINT_SEPARATOR = " | ";
	/**Statement column size*/
	private static final int COLUMN_SIZE = 10;

	/** Currency to be printed in statement*/
	private CurrencyEnum currency;
	/**Transaction of the statement entry*/
	private Transaction transaction;
	/**balance after transaction*/
	private BigDecimal balance;

	/**
	 * Parameterized class constructor
	 * @param transaction Statement entry transaction
	 * @param balance Statement entry balance 
	 * @param currency statement entry currency
	 */
	public StatementEntry(Transaction transaction, BigDecimal balance, CurrencyEnum currency) {
		super();
		this.currency = currency;
		this.transaction = transaction;
		this.balance = balance;
	}
	/**
	 * Formats entry's transaction amount
	 * @return
	 */
	private String formatTransactionAmount() {
		String out = null;
		if (transaction != null) {
			out = formatAmount(transaction.getAmount());
		}
		return out;
	}

	/**
	 * Formats entry's balance
	 * @return
	 */
	private String formatBalance() {
		return balance != null ? formatAmount(balance) : null;
	}

	/**
	 * Formats an amount and right pad with spaces to reach statement column size
	 * @param amount amount to format
	 * @return
	 */
	private String formatAmount(BigDecimal amount) {
		argumentNotNull(amount, "Amount cannot be null");
		amount.setScale(2, RoundingMode.DOWN);
		return rightPad(new StringBuilder(amount.toPlainString()).append(currency.toString()).toString(), COLUMN_SIZE);
	}

	/**
	 * Prints a statement entry
	 * @param printer output printer
	 */
	public void print(PrintStream printer) {
		if (printer != null) {
			StringBuilder statementEntryLine = new StringBuilder(PRINT_SEPARATOR)
					.append(rightPad(transaction.formatDate(), COLUMN_SIZE)).append(PRINT_SEPARATOR)
					.append(rightPad(transaction.getType().getLabel(), COLUMN_SIZE)).append(PRINT_SEPARATOR)
					.append(formatTransactionAmount()).append(PRINT_SEPARATOR).append(formatBalance())
					.append(PRINT_SEPARATOR);
			printer.println(statementEntryLine);
		}

	}

	//Getters and setters
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
