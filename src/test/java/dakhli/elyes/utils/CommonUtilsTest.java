package dakhli.elyes.utils;

import static dakhli.elyes.utils.CommonUtils.date;
import static dakhli.elyes.utils.CommonUtils.format;
import static dakhli.elyes.utils.CommonUtils.rightPad;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class CommonUtilsTest {
	
	
	@Test
	public void shouldParseDate() {
		Date parsedDate = date("31/12/2019");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parsedDate);
		
		assertNotNull("Parsed date should not be null", parsedDate);
		assertEquals("The day number must be 31", 31, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals("The month must be DECEMBER", Calendar.DECEMBER, calendar.get(Calendar.MONTH));
		assertEquals("The year must be 2019", 2019, calendar.get(Calendar.YEAR));
	}
	
	@Test
	public void nullDateWhenDateFormatIsWrong() {
		Date parsedDate = date("31-12-2019");
		assertNull("Date should be null if provided format is incorrect", parsedDate);
	}
	
	@Test
	public void shouldFormatDate() {
		
		Calendar toFormat = Calendar.getInstance();
		String formattedDate = format(toFormat.getTime());
		
		String expectedDay = String.valueOf(toFormat.get(Calendar.DAY_OF_MONTH));
		String expectedMonth = String.valueOf(toFormat.get(Calendar.MONTH)+1);
		String expectedYear = String.valueOf(toFormat.get(Calendar.YEAR));
		String[] formattedDateFields = formattedDate.split("/");
		
		assertTrue("Formatted date must not be null", StringUtils.isNotBlank(formattedDate));
		assertEquals("Formatted date must contain 3 fields", 3, formattedDateFields.length);
		assertEquals("The day of month is wrong ", expectedDay, formattedDateFields[0]);
		assertEquals("The month is wrong", expectedMonth, formattedDateFields[1]);
		assertEquals("The year is wrong", expectedYear, formattedDateFields[2]);
	}
	
	@Test
	public void shouldRightPadAGivenString() {
		String str = "ABC";
		String result = rightPad(str, 5);
		String expected = "ABC  ";
		
		assertTrue("Resulted string should not be empty or null", StringUtils.isNotBlank(result));
		assertEquals("Result string is wrong", expected, result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void rightPadWithNullString() {
		rightPad(null, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void argumentNotNullWithNullParameter() {
		CommonUtils.argumentNotNull(null, "exception message");
	}
	
	@Test()
	public void argumentNotNullWithNotNullParameter() {
		CommonUtils.argumentNotNull(new Object(), null);
	}

}
