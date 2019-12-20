package dakhli.elyes.model;

import static dakhli.elyes.utils.CommonUtils.date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {

	private Account account;

	@Before
	public void init() {
		account = new Account(CurrencyEnum.EURO);
	}

	@Test
	public void freshAccountBalanceEqualsToZero() {
		assertEquals("Account balance must be 0", new BigDecimal(0), account.getBalance());
	}

	@Test
	public void makeADepositInTheAccount() throws ParseException {
		BigDecimal newBalance = account.deposit(new BigDecimal(200), date("13/12/2019"));

		assertEquals("Account balance diff must be equal to deposit's amount", newBalance, new BigDecimal(200));
	}

	@Test
	public void makeWithdrawalFromAccount() {
		BigDecimal newBalance = account.withdrawal(new BigDecimal(300), date("12/12/2019"));

		assertEquals("Account balance diff must be equal to withdrawal's amount", newBalance, new BigDecimal(-300));
	}

	@Test
	public void seeTheHistoryOfTheAccount() throws IOException {
		account.deposit(new BigDecimal(1500), date("13/12/2019"));
		account.withdrawal(new BigDecimal(350), date("15/12/2019"));

		assertFalse("Account statement should not be empty", account.isEmptyStatement());
		assertEquals("Account should contains two statement entries", 2, account.statementSize());

		ByteArrayOutputStream content = new ByteArrayOutputStream();
		PrintStream printer = new PrintStream(content, false, "UTF-8");
		account.printStatement(printer);

		ByteArrayOutputStream expectedContent = getExpectedStatementContent();

		assertTrue("Statement not printed", content != null && content.size() > 0);
		String expectedString = new String(expectedContent.toByteArray(), StandardCharsets.UTF_8).trim();
		String actualString = new String(content.toByteArray(), StandardCharsets.UTF_8).trim();
		assertTrue("Wrong account statement content printed",expectedString.equals(actualString));
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwWhenStatementPrintWithoutPrinter() {
		account.printStatement(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwIfmakingDepositWithNoAmount() {
		account.deposit(null, new Date());
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwIfmakingDepositWithNoDate() {
		account.deposit(new BigDecimal(1000), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwIfmakingWithdrawalWithNoAmount() {
		account.withdrawal(null, new Date());
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwIfmakingWithdrawalWithNoDate() {
		account.withdrawal(new BigDecimal(1000), null);
	}

	/**
	 * Loads statement expected output from the resource : statement.out
	 * 
	 * @return
	 * @throws IOException
	 */
	private ByteArrayOutputStream getExpectedStatementContent() throws IOException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("statement.out");
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[1024];

		while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}

		buffer.flush();
		return buffer;
	}
}
