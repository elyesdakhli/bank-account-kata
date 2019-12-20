package dakhli.elyes.model;

import static dakhli.elyes.utils.CommonUtils.format;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author Elyes
 *
 */
public class Transaction {
	/**
	 *Transaction date 
	 */
	private Date date;
	/**
	 * Transaction amount
	 */
	private BigDecimal amount;
	/**
	 * Transaction type {@link TransactionTypeEnum}
	 */
	private TransactionTypeEnum type;
	
	public Transaction(Date date, BigDecimal amount, TransactionTypeEnum type) {
		super();
		this.date = date;
		this.amount = amount;
		this.type = type;
	}
	/**
	 * Formats the transaction date to the configured formatter
	 * @return formatted date
	 */
	public String formatDate() {
		return format(date);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public TransactionTypeEnum getType() {
		return type;
	}
	public void setType(TransactionTypeEnum type) {
		this.type = type;
	}
}
