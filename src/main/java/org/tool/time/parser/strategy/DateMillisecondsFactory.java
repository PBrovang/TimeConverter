package org.tool.time.parser.strategy;

import java.util.Date;

import org.tool.time.parser.ArgumentException;

public class DateMillisecondsFactory implements DateFactory {
	private static final String UTC_MILLISECONDS_EXCEPTION = "Argument %s is not UTC milliseconds";
	
	@Override
	public Date createDate(String value) throws ArgumentException {
		try {
			long date = Long.parseLong(value);
			return new Date(date);
		} catch (NumberFormatException e) {
			throw new ArgumentException(String.format(UTC_MILLISECONDS_EXCEPTION, value));
		}
	}

}
