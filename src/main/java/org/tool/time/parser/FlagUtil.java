package org.tool.time.parser;

public class FlagUtil implements FlagConstants{

	private static final char[] dashes = { 
		SOFT_HYPHEN, 
		HYPHEN_MINUS, 
		HYPHEN, 
		NON_BREAKING_HYPHEN, 
		FIGURE_DASH, 
		EN_DASH, 
		EM_DASH, 
		SMALL_EM_DASH, 
		SMALL_HYPHEN_MINUS, 
		FULLWIDTH_HYPHEN_MINUS 
	};
	
	private static final String FLAG_HAS_ILLEGAL_SYMBOL_EXCEPTION = "Illegal flag symbols u%04X character '%c' in %s" ;
	private static final String FLAG_HAS_NO_SYMBOLS_EXCEPTION = "Empty string without symbols";

	public static void validate(String symbols) throws IllegalArgumentException {
		if (symbols.isEmpty())
			throw new IllegalArgumentException(FLAG_HAS_NO_SYMBOLS_EXCEPTION);
		for (int index = 0; index < symbols.length(); index++) {
			char ch = symbols.charAt(index);
			if (!isLetter(ch))
				throw new IllegalArgumentException(String.format(FLAG_HAS_ILLEGAL_SYMBOL_EXCEPTION, (int)ch, ch, symbols));
		}
	}
	
	public static boolean isLetter(char ch) {
		if (ch >= 'a' && ch <= 'z')
			return true;
		if (ch >= 'A' && ch <= 'Z')
			return true;
		return false;
	}

	public static String decodeSymbol(String argument) throws ArgumentException {
		if (isFlag(argument))
			return argument.substring(1);
		throw new ArgumentException(String.format(ArgumentConsumer.ILLEGAL_FLAG_EXCEPTION, argument));
	}

	private static boolean isValidDash(char ch) {
		for (int i = 0; i < FlagUtil.dashes.length; i++) {
			if (FlagUtil.dashes[i] == ch)
				return true;
		}
		return false;
	}

	public static boolean isFlag(String argument) {
		if (argument.length() > 1) {
			char ch = argument.charAt(0);
			if (isValidDash(ch)) 
				return true;
		}
		return false;
	}
}