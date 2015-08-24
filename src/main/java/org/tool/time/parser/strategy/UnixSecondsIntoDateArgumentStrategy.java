package org.tool.time.parser.strategy;

import java.sql.Date;

import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ConsumeOutput;

public class UnixSecondsIntoDateArgumentStrategy extends AbstractDateArgumentStrategy  {

	private static final String UNIX_SECONDS_EXCEPTION = "Argument %s is not UNIX seconds";
	
	public UnixSecondsIntoDateArgumentStrategy(String symbols, ConsumeOutput output) throws IllegalArgumentException {
		super(symbols, output);
	}
	
	@Override
	protected ArgumentException getNumberFormatException(String text) {
		return new ArgumentException(String.format(UNIX_SECONDS_EXCEPTION, text));
	}

	protected Date createDate(String text) throws NumberFormatException {
		long milliseconds = Long.parseLong(text) * 1000;
		return new Date(milliseconds);
	}
}
