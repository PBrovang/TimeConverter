package org.tool.time;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.tool.time.parser.ArgumentConsumer;
import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ArgumentParser;
import org.tool.time.parser.ArgumentStrategy;
import org.tool.time.parser.ConsumeManagerOutput;
import org.tool.time.parser.Help;
import org.tool.time.parser.HelpMessage;
import org.tool.time.parser.strategy.DateFormatArgumentStrategy;
import org.tool.time.parser.strategy.DateFormatObserver;
import org.tool.time.parser.strategy.DateFormatSubject;
import org.tool.time.parser.strategy.DateIntoLongArgumentStrategy;
import org.tool.time.parser.strategy.DateMillisecondsFactory;
import org.tool.time.parser.strategy.DateSecondsFactory;
import org.tool.time.parser.strategy.MilliSecondsFactory;
import org.tool.time.parser.strategy.SecondsFactory;
import org.tool.time.parser.strategy.ToDateArgumentStrategy;



public class Main {
	
	private static final String DATE_FORMAT_TEXT = "Default date-format is 'yyyy-MM-dd HH:mm:ss', to change the input/output format apply this flag.\n "
			+ "The flag can be applied multiple times.";
	private static final String CONVERT_DATE_INTO_SECONDS = "Print out the date-format into seconds";
	private static final String CONVERT_DATE_INTO_MILLISECONDS = "Print out the date-format into milliseconds";
	private static final String CONVERT_SECONDS = "Convert one or more seconds values into date, defined by the date-format";
	private static final String CONVERT_MILLISECONDS = "Convert one or more milliseconds values into date, defined by the date-format";

	public Main() {
		super();
	}

	
	protected void execute(String[] arguments) throws ArgumentException {
		DateFormatHolder holder = new DateFormatHolder(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		OutputPrinter printer = new OutputPrinter(System.out);
		DateFormatSubject subject   = holder;
		DateFormatObserver observer = holder;
		ConsumeManagerOutput output = printer;
		List<HelpMessage> messages  = Arrays.asList(
			new HelpMessage("f", DATE_FORMAT_TEXT),
			new HelpMessage("u", CONVERT_MILLISECONDS),
			new HelpMessage("x", CONVERT_SECONDS),
			new HelpMessage("l", CONVERT_DATE_INTO_MILLISECONDS),
			new HelpMessage("i", CONVERT_DATE_INTO_SECONDS)
		);
		
		Help help = new Help(messages, output);
		ArgumentStrategy dateFormat         = new DateFormatArgumentStrategy("f", observer);
		ToDateArgumentStrategy dateToMSec       = new ToDateArgumentStrategy("u", output, new DateMillisecondsFactory(), subject);
		ToDateArgumentStrategy dateToSeconds    = new ToDateArgumentStrategy("x", output, new DateSecondsFactory(), subject);
		DateIntoLongArgumentStrategy utc  = new DateIntoLongArgumentStrategy("l", output, new MilliSecondsFactory(), subject);
		DateIntoLongArgumentStrategy unix = new DateIntoLongArgumentStrategy("i", output, new SecondsFactory(), subject);
		
		List<ArgumentStrategy> strategies = Arrays.asList(
			help,
			dateFormat,
			dateToMSec,
			dateToSeconds,
			utc,
			unix
		);
		
		HashMap<String, ArgumentStrategy> map = new HashMap<>(strategies.size());
		for (ArgumentStrategy strategy : strategies) 
			map.put(strategy.getSymbols(), strategy);
		
		ArgumentParser parser = new ArgumentConsumer(map);
		parser.execute(arguments);
		
		
	}
	
	public static void main(String[] arguments) {
		System.out.println(Arrays.toString(arguments));
		try {
			Main main = new Main();
			main.execute(arguments);
		} catch (ArgumentException e) {
			System.out.println("Unexpected error: " + e.getMessage());
		}
	}
}