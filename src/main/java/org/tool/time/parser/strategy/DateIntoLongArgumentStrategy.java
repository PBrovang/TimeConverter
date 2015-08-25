package org.tool.time.parser.strategy;

import static java.lang.String.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.tool.time.parser.AbstractArgumentStrategy;
import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ConsumeOutput;

public class DateIntoLongArgumentStrategy extends AbstractArgumentStrategy {

	private static final String INVALID_DATE_FORMAT_PATTERN_EXCEPTION = "Date-Pattern for %s found at index %d is not valid";
	private static final String MISSING_ARGUMENTS_EXCEPTION = "Flag -%s at argument %d requires one or more dates"; 
	private static final String ARGUMENT_PARSE_EXCEPTION = "For pattern %s is argument %s not valid format";
	private final ConsumeOutput output;
	private final LongFactory factory;
	
	public DateIntoLongArgumentStrategy(String symbols, ConsumeOutput output, LongFactory factory) throws IllegalArgumentException {
		super(symbols);
		this.output = output;
		this.factory = factory;
	}

	@Override
	public int consume(final int index, String[] arguments) throws ArgumentException {
		String pattern = readNextArgument(index, arguments);
		SimpleDateFormat sdf;
		try {
			sdf = new SimpleDateFormat(pattern);
		} catch (Exception e) {
			String message = format(INVALID_DATE_FORMAT_PATTERN_EXCEPTION, arguments[index], index);
			throw new ArgumentException(message);
		}
		String[] values = readNextArgumentsUntilFlag(index + 1, arguments);
		if (values.length == 0)
			throw new ArgumentException(format(MISSING_ARGUMENTS_EXCEPTION, arguments[index], index + 1));
		String source = null;
		try {
			for (int i = 0; i < values.length; i++) {
				source = values[i];
				Date date = sdf.parse(source);
				output.consume(factory.create(date))	;
			}
			return values.length + 1;
		} catch (ParseException e) {
			String message = format(ARGUMENT_PARSE_EXCEPTION, pattern, source);
			throw new ArgumentException(message);
		}
	}


}
