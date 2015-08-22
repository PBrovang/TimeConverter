package org.tool.time.convert;

import java.text.DateFormat;
import java.util.Date;

public class LongToDateFactory {
	
	private final DateFormat date;

	public LongToDateFactory(DateFormat date) {
		super();
		this.date = date;
	}

	public String getDate(long milliSeconds) {
		return date.format(new Date(milliSeconds));
	}

}
