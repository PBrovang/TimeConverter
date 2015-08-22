package org.tool.time.parser;

public interface Flag {
	
	public static final char PREFIX = '-'; 
	
	public String getFlag();
	
	public boolean isFlag(String argument);
}
