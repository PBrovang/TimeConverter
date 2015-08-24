package org.tool.time.parser.strategy;

import java.sql.Date;

import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ConsumeOutput;

public class MillisecondIntoDateArgumentStrategy extends AbstractDateArgumentStrategy  {

	private static final String UTC_MILLISECONDS_EXCEPTION = "Argument %s is not UTC milliseconds";
	
	public MillisecondIntoDateArgumentStrategy(String symbols, ConsumeOutput output) throws IllegalArgumentException {
		super(symbols, output);
	}
	
	@Override
	protected ArgumentException getNumberFormatException(String text) {
		return new ArgumentException(String.format(UTC_MILLISECONDS_EXCEPTION, text));
	}

	protected Date createDate(String text) throws NumberFormatException {
		return new Date(Long.parseLong(text));
	}
}
