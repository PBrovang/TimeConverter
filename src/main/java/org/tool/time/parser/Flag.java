package org.tool.time.parser;

public class Flag {
	
	protected static final int HAHS_CODE_PRIME = 127;
	private static final String PREFIX = "-";
	private final String symbols;
	
	public Flag(char symbol) throws IllegalArgumentException {
		this(new String(new char[]{symbol}));
	}
	
	public Flag(String symbols) throws IllegalArgumentException {
		super();
		FlagUtil.validate(symbols);
		this.symbols = symbols;
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
		Flag other = (Flag) obj;
		if (!symbols.equals(other.symbols))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return PREFIX.concat(this.symbols);
	}
}
