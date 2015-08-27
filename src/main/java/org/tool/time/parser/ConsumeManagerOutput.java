package org.tool.time.parser;

public interface ConsumeManagerOutput extends ConsumeOutput {
	
	public void setError(String message);
	
	public void clear();
	
	public void executeConsole();
}
