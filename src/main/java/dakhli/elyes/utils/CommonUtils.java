package dakhli.elyes.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {
	private static String DATE_PATTERN = "dd/MM/yyyy";
	private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
	
	public static Date date(String date) {
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String format(Date date) {
		return sdf.format(date);
	}
	
	public static String rightPad(String str ,int size) {
		argumentNotNull(str, "Input string is required to right pad");
		return StringUtils.rightPad(str.toString(), size);
	}
	
	public static void argumentNotNull(Object toValidate, String message) {
		if(Objects.isNull(toValidate)) {
			throw Objects.isNull(message) ?  new IllegalArgumentException() : new IllegalArgumentException(message);
		}
	}

}
