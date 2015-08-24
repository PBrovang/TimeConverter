package org.tool.time.parser;

import static java.lang.String.format;
import static org.tool.time.parser.FlagUtil.isFlag;


public abstract class AbstractArgumentStrategy implements ArgumentStrategy{
	
	protected static final String ARGUMENT_MISSING_EXCEPTION = "Required argument of -%s is missing";
	protected static final String ARGUMENTS_MISSING_EXCEPTION = "Required arguments for -%s are missing %d out of %d arguments";
	
	protected static final int HAHS_CODE_PRIME = 127;
	private static final String PREFIX = "-";
	private final String symbols;
	
	public AbstractArgumentStrategy(char symbol) throws IllegalArgumentException {
		this(new String(new char[]{symbol}));
	}
	
	public AbstractArgumentStrategy(String symbols) throws IllegalArgumentException {
		super();
		FlagUtil.validate(symbols);
		this.symbols = symbols;
	}

	@Override
	public String getSymbols() {
		return symbols;
	}
	
	@Override
	public int hashCode() {
		return HAHS_CODE_PRIME + this.symbols.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractArgumentStrategy other = (AbstractArgumentStrategy) obj;
		if (!symbols.equals(other.symbols))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(256)
			.append(PREFIX)
			.append(symbols)
			.append(" ")
			.append(getClass().getSimpleName());
		return sb.toString();
	}
	
	protected String readNextArgument(final int index, String[] arguments) throws ArgumentException {
		int fromIndex = index + 1;
		if (fromIndex  >= arguments.length) 
			throw new ArgumentException(format(ARGUMENT_MISSING_EXCEPTION, getSymbols()));
		return arguments[fromIndex];
	}

	public String[] readNextArguments(final int index, String[] arguments, final int length) throws ArgumentException {
		final int fromIndex = index + 1;
		if ((fromIndex + length) > arguments.length) {
			int no = length - (arguments.length - fromIndex);
			throw new ArgumentException(format(ARGUMENTS_MISSING_EXCEPTION, getSymbols(), no, length));
		}
		String[] next = new String[length];
		System.arraycopy(arguments, fromIndex, next, 0, length);
		return next;
	}

	public String[] readNextArgumentsUntilFlag(int index, String[] arguments) {
		final int fromIndex = index + 1;
		int length = 0;
		for (int i = fromIndex; i < arguments.length && !isFlag(arguments[i]); i++) 
			length ++;
		String[] nexts = new String[length];
		System.arraycopy(arguments, fromIndex, nexts, 0, length);
		return nexts;
	}
}
