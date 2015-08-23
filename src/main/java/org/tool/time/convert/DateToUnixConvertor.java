package org.tool.time.convert;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class DateToUnixConvertor {
	private final DateFormat date;

	public DateToUnixConvertor(DateFormat date) {
		super();
		this.date = date;
	}

	public String getValue(String source) throws ParseException {
		Date time = date.parse(source);
		return String.valueOf(time.getTime() / 1000);
	}
}