package org.tool.time.parser;

public interface ArgumentStrategy {
	
	public String getSymbols();
	
	public int consume(int index, String[] arguments) throws ArgumentException;
	
}
