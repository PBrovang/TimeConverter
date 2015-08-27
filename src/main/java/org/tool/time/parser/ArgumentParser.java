package org.tool.time.parser;


public interface ArgumentParser {

	public abstract Help getHelp();
	
	public abstract void execute(String[] arguments) throws ArgumentException;

}
