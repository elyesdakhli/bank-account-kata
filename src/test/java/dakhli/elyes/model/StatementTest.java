package dakhli.elyes.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class StatementTest {
	private Statement statement;
	
	@Before
	public void init() {
		this.statement = new Statement(CurrencyEnum.USDOLLAR);
	}
	
	@Test
	public void shouldAddEntryToStatement() {
		assertTrue("Statement must be empty after init", statement.isEmpty());
		
		Transaction transaction = new Transaction(new Date(), new BigDecimal(500), TransactionTypeEnum.CREDIT);
		statement.addEntry(transaction, new BigDecimal(0));
		assertFalse("Statement must not be empty after adding a transaction", statement.isEmpty());
		assertEquals("Statement size must be equal to 1", 1, statement.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void throwWhenAddingStatementEntryWithNoTransaction() {
		statement.addEntry(null, new BigDecimal(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void throwWhenAddingStatementEntryWithNoBalance() {
		statement.addEntry(new Transaction(new Date(), new BigDecimal(500), TransactionTypeEnum.CREDIT), null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void throwWhenAddingStatementEntryWithNoBalanceAndNoTransaction() {
		statement.addEntry(null, null);
	}

}
