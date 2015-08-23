package org.tool.time.convert;

import java.text.DateFormat;
import java.util.Date;

public class LongToDateConvertor {
	
	private final DateFormat date;

	public LongToDateConvertor(DateFormat date) {
		super();
		this.date = date;
	}

	public String getValue(long milliSeconds) {
		return date.format(new Date(milliSeconds));
	}

}
