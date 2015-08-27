package org.tool.time;

import java.text.SimpleDateFormat;
import org.tool.time.arguments.ArgumentBuilder;
import org.tool.time.arguments.DateFormatHolder;
import org.tool.time.arguments.OutputPrinter;
import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ArgumentParser;
import org.tool.time.parser.ArgumentParserBuilder;
import org.tool.time.parser.ArgumentParserBuilderException;
import org.tool.time.parser.ConsumeManagerOutput;
import org.tool.time.parser.strategy.DateFormatObserver;
import org.tool.time.parser.strategy.DateFormatSubject;


public class Main {
	
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private ConsumeManagerOutput output;
	private ArgumentParserBuilder parserBuilder;

	
	protected Main(ConsumeManagerOutput output, ArgumentParserBuilder parserBuilder) {
		super();
		this.output = output;
		this.parserBuilder = parserBuilder;
	}
	
	private Main (ConsumeManagerOutput output) {
		this(output, createArgumentParser(output));
	}

	public Main() {
		this(new OutputPrinter(System.out));
	}
	
	private static ArgumentBuilder createArgumentParser(ConsumeManagerOutput output) {
		DateFormatHolder holder = new DateFormatHolder(new SimpleDateFormat(DEFAULT_DATE_FORMAT));
		DateFormatSubject subject = holder;
		DateFormatObserver observer = holder;
		return new ArgumentBuilder(subject, observer, output);
	}	
	
	protected void execute(String[] arguments) throws Exception {
		try {
			ArgumentParser parser = parserBuilder.build();
			try {
				parser.execute(arguments);
			} catch (ArgumentException e) {
				output.setError(e.getMessage());
				parser.getHelp().consume(0, arguments);
			}
		} catch (ArgumentParserBuilderException e) {
			output.setError(e.getMessage());
		} 
		output.executeConsole();
	}
	
	public static void main(String[] arguments) throws Exception {
		Main main = new Main();
		main.execute(arguments);
	}
}