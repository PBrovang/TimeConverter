package org.tool.time.parser.strategy;

import java.util.Date;

import org.tool.time.parser.ArgumentException;

public class DateSecondsFactory implements DateFactory {
	private static final String UNIX_SECONDS_EXCEPTION = "Argument %s is not UNIX seconds";

	@Override
	public Date createDate(String value) throws ArgumentException {
		try {
			Long date = Long.parseLong(value) * 1000;
			return new Date(date);
		} catch (NumberFormatException e) {
			throw new ArgumentException(String.format(UNIX_SECONDS_EXCEPTION, value));
		}
	}

}
