package org.tool.time.parser;

public class ArgumentParserBuilderException extends Exception {

	private static final long serialVersionUID = -7302363057414534646L;

	public ArgumentParserBuilderException(String message) {
		this(message, null);
	}

	public ArgumentParserBuilderException(String message, Throwable cause) {
		super(message, cause);
	}
}
