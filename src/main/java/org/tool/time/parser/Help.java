package org.tool.time.parser;

import static org.tool.time.arguments.HelpTexts.HELP_TITLE;

import java.util.Arrays;
import java.util.List;

public class Help implements ArgumentStrategy {
	private static final String NEW_LINE = "\n"; 
	private static final char SPACE = ' ';
	private static final String TAB = "    ";
	private static final String HELP_SYMBOLS = "h|H";
	private ConsumeManagerOutput out;
	private List<HelpMessage> messages;
	
	public Help(List<HelpMessage> messages, ConsumeManagerOutput output) {
		super();
		this.messages = messages;
		this.out = output;
	}

	@Override
	public String getSymbols() {
		return HELP_SYMBOLS;
	}
	
	@Override
	public int consume(int index, String[] arguments) {
		final String lineFormat = buildLineFormat(longestFlag() + 1);
		final String nextLineFormat = buildPadding(lineFormat).concat("%s");
		out.clear();
		out.consume(HELP_TITLE);
		for(HelpMessage help : messages) {
			String flag = help.getFlag().toString();
			String text = help.getText();
			String[] lines = text.split(NEW_LINE);
			for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
				if(lineIndex == 0) {
					out.consume(String.format(lineFormat, flag, lines[lineIndex]));
				} else {
					out.consume(String.format(nextLineFormat, lines[lineIndex].trim()));
				}
			}
		}
		return arguments.length;
	}
		
	private String buildPadding(final String lineFormat) {
		int paddingLength = String.format(lineFormat, "", "").length();
		char[] spaces = new char[paddingLength];
		Arrays.fill(spaces, SPACE);
		return new String(spaces);
	}

	private String buildLineFormat(int max) {
		StringBuilder line = new StringBuilder(124)
			.append(TAB)
			.append("%-").append(max).append("s")
			.append(TAB)
			.append("%s");
		return line.toString();
	}
	
	private int longestFlag() {
		int result = getSymbols().length();
		for (HelpMessage help : messages) 
			result = Math.max(result, help.getFlag().toString().length());
		return result;
	}

}
