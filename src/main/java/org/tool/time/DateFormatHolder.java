package org.tool.time;

import java.text.SimpleDateFormat;

import org.tool.time.parser.strategy.DateFormatObserver;
import org.tool.time.parser.strategy.DateFormatSubject;

public class DateFormatHolder implements DateFormatObserver, DateFormatSubject {

	private SimpleDateFormat dateFormat;
	
	public DateFormatHolder(SimpleDateFormat defaultDateFormat) {
		super();
		this.dateFormat = defaultDateFormat;
	}

	@Override
	public SimpleDateFormat getDateFormat() {
		return this.dateFormat;
	}

	@Override
	public void update(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;		
	}
}
