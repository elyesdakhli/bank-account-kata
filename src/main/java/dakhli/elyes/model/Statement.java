package dakhli.elyes.model;

import static dakhli.elyes.utils.CommonUtils.argumentNotNull;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class Statement {
	private static final int TOP_INDEX = 0;
	private static final String STATEMENT_HEADER = " |    Date    |  Operation |   Amount   |   Balance  |";
	
	private List<StatementEntry> entries = new LinkedList<>();
	private CurrencyEnum currency;
	
	public Statement(CurrencyEnum currency) {
		this.currency = currency;
	}

	public boolean isEmpty() {
		return CollectionUtils.isEmpty(entries);
	}
	
	public int size() {
		return entries.size();
	}
	
	public void addEntry(Transaction transaction, BigDecimal balance) {
		argumentNotNull(transaction, "Cannot add entry with a null transaction");
		argumentNotNull(balance, "Current account balance is required to add an entry to the statement");
		
		entries.add(TOP_INDEX, new StatementEntry(transaction, balance, currency));
	}
	
	public void print(PrintStream printer) {
		argumentNotNull(printer, "Printer is required");
		
		//printing statement header
		printer.println(STATEMENT_HEADER);
		for(StatementEntry entry : entries) {
			entry.print(printer);
		}
	}

}
