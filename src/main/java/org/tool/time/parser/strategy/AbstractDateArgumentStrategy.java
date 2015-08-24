package org.tool.time.parser.strategy;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.tool.time.parser.AbstractArgumentStrategy;
import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ConsumeOutput;

public abstract class AbstractDateArgumentStrategy extends AbstractArgumentStrategy implements DateFormatObserver{

	private final List<Date> values;
	private DateFormat dateFormat;
	private final ConsumeOutput output;
	
	public AbstractDateArgumentStrategy(String symbols, ConsumeOutput output) throws IllegalArgumentException {
		super(symbols);
		this.output = output;
		values = new ArrayList<>(50);
	}

	@Override
	public int consume(int index, String[] arguments) throws ArgumentException {
		List<String> list = new ArrayList<>(20);
		list.add(readNextArgument(index, arguments));
		list.addAll(Arrays.asList(readNextArgumentsUntilFlag(index + 1, arguments)));
		String text = null;
		try {
			for (int i = 0; i < list.size(); i++) {
				text = list.get(i);
				values.add(createDate(text));
			}
		} catch (NumberFormatException e) {
			throw getNumberFormatException(text);
		}
		process();
		return values.size();
	}

	protected abstract ArgumentException getNumberFormatException(String text);
	
	protected abstract Date createDate(String text) throws NumberFormatException;

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
