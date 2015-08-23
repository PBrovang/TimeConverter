package org.tool.time.convert;

import java.text.DateFormat;
import java.util.Date;

public class UnixToDateConvertor {

	private final DateFormat date;

	public UnixToDateConvertor(DateFormat date) {
		super();
		this.date = date;
	}
	
	public String getValue(long seconds) {
		return date.format(new Date(seconds * 1000));
	}
	
	
}
