package org.tool.time.parser.strategy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.tool.time.parser.AbstractArgumentStrategy;
import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ConsumeOutput;

public class ToDateArgumentStrategy extends AbstractArgumentStrategy {

	private final ConsumeOutput output;
	private final DateFactory factory;
	private final DateFormatSubject subject;
	
	public ToDateArgumentStrategy(String symbols, ConsumeOutput output, DateFactory factory, DateFormatSubject subject) throws IllegalArgumentException {
		super(symbols);
		this.output = output;
		this.factory = factory;
		this.subject = subject;
	}

	@Override
	public int consume(int index, String[] arguments) throws ArgumentException {
		final SimpleDateFormat sdf = subject.getDateFormat();
		List<String> list = new ArrayList<>(20);
		list.add(readNextArgument(index, arguments));
		list.addAll(Arrays.asList(readNextArgumentsUntilFlag(index + 1, arguments)));
		for (String value : list) {
			Date date = factory.createDate(value);
			output.consume(sdf.format(date));			
		}
		return list.size();
	}
}
