package org.tool.time.parser;

import static org.tool.time.parser.FlagUtil.decodeSymbol2Flag;

import java.util.Map;

public class ArgumentConsumer implements ArgumentParser {
	
	private static final String NO_STRATEGY_EXCEPTION = "Argument-Consumer requires one or more strategies";
	static final String ILLEGAL_FLAG_EXCEPTION = "Argument '%s' is not a valid flag";
	private static final String FLAG_NOT_CONTAINED_EXCEPTION = "AbstractArgumentStrategy '%s' is not contained";
	
	private final Help help;
	private final Map<Flag, ArgumentStrategy> strategies;
	
	public ArgumentConsumer(Help help, Map<Flag, ArgumentStrategy> strategies) throws IllegalArgumentException{
		super();
		if (strategies.isEmpty())
			throw new IllegalArgumentException(NO_STRATEGY_EXCEPTION);
		this.help = help;
		this.strategies = strategies;
	}
	
	@Override
	public Help getHelp() {
		return this.help;
	}

	@Override
	public void execute(String[] arguments) throws ArgumentException {
		for (int index = 0; index < arguments.length; index++) {
			String argument = arguments[index];
			Flag flag = decodeSymbol2Flag(argument);
			ArgumentStrategy strategy = this.strategies.get(flag);
			if (strategy == null)
				throw new ArgumentException(String.format(FLAG_NOT_CONTAINED_EXCEPTION, argument));
			index += strategy.consume(index, arguments);
		}
	}
}
