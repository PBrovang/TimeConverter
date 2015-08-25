package org.tool.time.parser.strategy;

import static org.tool.time.parser.strategy.DateFormatObserver.NULL;

import java.text.SimpleDateFormat;

import org.tool.time.parser.AbstractArgumentStrategy;
import org.tool.time.parser.ArgumentException;

public class DateFormatArgumentStrategy extends AbstractArgumentStrategy {

	private static final String INVALID_DATE_FORMAT_PATTERN_EXCEPTION = "Invalid date pattern %s found at index %d";
	private DateFormatObserver observer;
	
	public DateFormatArgumentStrategy(String symbols, DateFormatObserver observer) throws IllegalArgumentException {
		super(symbols);
		this.setObserver(observer);
	}

	@Override
	public int consume(int index, String[] arguments) throws ArgumentException {
		String pattern = readNextArgument(index, arguments);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			observer.update(sdf);
			return 1;
		} catch (Exception e) {
			throw new ArgumentException(String.format(INVALID_DATE_FORMAT_PATTERN_EXCEPTION, pattern, index));
		}
	}

	protected Object getObserver() {
		return this.observer;
	}

	public void setObserver(DateFormatObserver observer) {
		this.observer = (observer == null ? NULL : observer);
	}
}