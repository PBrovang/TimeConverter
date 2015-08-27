package org.tool.time.arguments;

import static org.tool.time.arguments.HelpTexts.CONVERT_DATE_INTO_MILLISECONDS;
import static org.tool.time.arguments.HelpTexts.CONVERT_DATE_INTO_SECONDS;
import static org.tool.time.arguments.HelpTexts.CONVERT_MILLISECONDS;
import static org.tool.time.arguments.HelpTexts.CONVERT_SECONDS;
import static org.tool.time.arguments.HelpTexts.DATE_FORMAT_TEXT;
import static org.tool.time.arguments.HelpTexts.HELP_DESCRIPTION;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.tool.time.parser.ArgumentConsumer;
import org.tool.time.parser.ArgumentParser;
import org.tool.time.parser.ArgumentParserBuilder;
import org.tool.time.parser.ArgumentParserBuilderException;
import org.tool.time.parser.ArgumentStrategy;
import org.tool.time.parser.ConsumeManagerOutput;
import org.tool.time.parser.Flag;
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

public class ArgumentBuilder implements ArgumentParserBuilder {
	private DateFormatSubject subject;
	private DateFormatObserver observer;
	private ConsumeManagerOutput output;
	
	public ArgumentBuilder(DateFormatSubject subject, DateFormatObserver observer, ConsumeManagerOutput output) {
		super();
		this.subject  = subject;
		this.observer = observer;
		this.output = output;
	}

	@Override
	public ArgumentParser build() throws ArgumentParserBuilderException {
		try {
			Flag h = new Flag('h');
			Flag H = new Flag('h');
			Flag f = new Flag('f');
			Flag u = new Flag('u');
			Flag x = new Flag('x');
			Flag l = new Flag('l');
			Flag i = new Flag('i');
			List<HelpMessage> messages = Arrays.asList(
				new HelpMessage(h, HELP_DESCRIPTION),
				new HelpMessage(f, DATE_FORMAT_TEXT),
				new HelpMessage(u, CONVERT_MILLISECONDS),
				new HelpMessage(x, CONVERT_SECONDS),
				new HelpMessage(l, CONVERT_DATE_INTO_MILLISECONDS),
				new HelpMessage(i, CONVERT_DATE_INTO_SECONDS)
			);
			Help help = new Help(messages, output);
			HashMap<Flag, ArgumentStrategy> map = new HashMap<>(6);
			map.put(h, help);
			map.put(H, help);
			map.put(f, new DateFormatArgumentStrategy  ("f", observer));
			map.put(u, new ToDateArgumentStrategy      ("u", output, new DateMillisecondsFactory(), subject));
			map.put(x, new ToDateArgumentStrategy      ("x", output, new DateSecondsFactory(),      subject));
			map.put(l, new DateIntoLongArgumentStrategy("l", output, new MilliSecondsFactory(),     subject));
			map.put(i, new DateIntoLongArgumentStrategy("i", output, new SecondsFactory(),          subject));
			return new ArgumentConsumer(help, map);
		} catch (IllegalArgumentException e) {
			throw new ArgumentParserBuilderException(e.getMessage());
		}
	}
}