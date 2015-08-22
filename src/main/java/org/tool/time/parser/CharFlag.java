package org.tool.time.parser;

public class CharFlag implements Flag {
	private static final String ILLEGAL_SYMBOL_EXCEPTION = "Illegal flag symbol u%04X character '%c'";
	protected static final int HAHS_CODE_PRIME = 127;
	private final char symbol;
	
	public CharFlag(char symbol) throws IllegalArgumentException {
		super();
		if (!isLetter(symbol))
			throw new IllegalArgumentException(String.format(ILLEGAL_SYMBOL_EXCEPTION, (int)symbol, symbol));
		this.symbol = symbol;
	}

	protected static boolean isLetter(char ch) {
		if (ch >= 'a' && ch <= 'z')
			return true;
		if (ch >= 'A' && ch <= 'Z')
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return HAHS_CODE_PRIME + this.symbol;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CharFlag other = (CharFlag) obj;
		if (symbol != other.symbol)
			return false;
		return true;
	}



	@Override
	public String getFlag() {
		char[] chars = {PREFIX, this.symbol};
		return new String(chars);
	}

	@Override
	public boolean isFlag(String value) {
		return value.equals(getFlag());
	}

	@Override
	public String toString() {
		return getFlag();
	}
}
