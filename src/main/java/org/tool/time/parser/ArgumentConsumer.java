package org.tool.time.parser;

import static org.tool.time.parser.FlagUtil.decodeSymbol;

import java.util.Map;

public class ArgumentConsumer implements ArgumentParser {
	
	private static final String NO_STRATEGY_EXCEPTION = "Argument-Consumer requires one or more strategies";
	static final String ILLEGAL_FLAG_EXCEPTION = "Argument '%s' is not a valid flag";
	private static final String FLAG_NOT_CONTAINED_EXCEPTION = "AbstractArgumentStrategy '%s' is not contained";
	
	private final Map<String, ArgumentStrategy> strategies;

	public ArgumentConsumer(Map<String, ArgumentStrategy> strategies) throws ArgumentException {
		super();
		if (strategies.isEmpty())
			throw new ArgumentException(NO_STRATEGY_EXCEPTION);
		this.strategies = strategies;
	}

	@Override
	public void execute(String[] arguments) throws ArgumentException {
		for (int index = 0; index < arguments.length; index++) {
			String argument = arguments[index];
			String key = decodeSymbol(argument);
			ArgumentStrategy strategy = this.strategies.get(key);
			if (strategy == null)
				throw new ArgumentException(String.format(FLAG_NOT_CONTAINED_EXCEPTION, argument));
			index += strategy.consume(index, arguments);
		}
	}
}
