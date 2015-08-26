package org.tool.time.parser.strategy;

import static java.lang.String.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.tool.time.parser.AbstractArgumentStrategy;
import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ConsumeOutput;

public class DateIntoLongArgumentStrategy extends AbstractArgumentStrategy {

	private static final String MISSING_ARGUMENTS_EXCEPTION = "Missing one or more arguments for %s"; 
	private static final String ARGUMENT_PARSE_EXCEPTION = "For pattern %s is argument %s not valid format";
	private final ConsumeOutput output;
	private final LongFactory factory;
	private final DateFormatSubject subject;
	
	public DateIntoLongArgumentStrategy(String symbols, ConsumeOutput output, LongFactory factory, DateFormatSubject subject) throws IllegalArgumentException {
		super(symbols);
		this.output = output;
		this.factory = factory;
		this.subject = subject;
	}

	@Override
	public int consume(final int index, String[] arguments) throws ArgumentException {
		final SimpleDateFormat sdf = subject.getDateFormat();
		String[] values = readNextArgumentsUntilFlag(index, arguments);
		if (values.length == 0)
			throw new ArgumentException(format(MISSING_ARGUMENTS_EXCEPTION, arguments[index]));
		String source = null;
		try {
			for (int i = 0; i < values.length; i++) {
				source = values[i];
				Date date = sdf.parse(source);
				output.consume(factory.create(date))	;
			}
			return values.length;
		} catch (ParseException e) {
			String message = format(ARGUMENT_PARSE_EXCEPTION, sdf.toPattern(), source);
			throw new ArgumentException(message);
		}
	}
}