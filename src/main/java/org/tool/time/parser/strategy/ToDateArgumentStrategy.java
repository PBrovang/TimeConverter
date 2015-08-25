package org.tool.time.parser.strategy;

import java.util.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.tool.time.parser.AbstractArgumentStrategy;
import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ConsumeOutput;

public class ToDateArgumentStrategy extends AbstractArgumentStrategy implements DateFormatObserver{

	private final List<Date> values;
	private DateFormat dateFormat;
	private final ConsumeOutput output;
	private final DateFactory factory;
	
	public ToDateArgumentStrategy(String symbols, ConsumeOutput output, DateFactory factory) throws IllegalArgumentException {
		super(symbols);
		this.output = output;
		values = new ArrayList<>(50);
		this.factory = factory;
	}

	@Override
	public int consume(int index, String[] arguments) throws ArgumentException {
		List<String> list = new ArrayList<>(20);
		list.add(readNextArgument(index, arguments));
		list.addAll(Arrays.asList(readNextArgumentsUntilFlag(index + 1, arguments)));
		for (String value : list) 
			values.add(factory.createDate(value));
		process();
		return values.size();
	}

	protected Date[] getValues() {
		return values.toArray(new Date[values.size()]);
	}

	@Override
	public void update(DateFormat dateFormat) {
		this.dateFormat = dateFormat;		
		this.process();
	}

	private void process() {
		DateFormat df = this.dateFormat;
		if (df == null)
			return;
		Date[] values = getValues();
		for (Date date : values) {
			output.consume(df.format(date));
		}
	}
}
