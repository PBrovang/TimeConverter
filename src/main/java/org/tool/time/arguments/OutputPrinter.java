package org.tool.time.arguments;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.tool.time.parser.ConsumeManagerOutput;

public class OutputPrinter implements ConsumeManagerOutput {
	
	private final List<Object> consumed;
	private final PrintStream out;
	private String error;
	
	
	public OutputPrinter(PrintStream out) {
		super();
		this.out = out;
		this.consumed = new ArrayList<>(100);
	}

	@Override
	public void setError(String message) {
		this.error = message;
	}
	
	@Override
	public void consume(Object object) {
		consumed.add(object);		
	}

	@Override
	public void clear() {
		consumed.clear();		
	}

	@Override
	public void executeConsole() {
		if (error != null)
			out.println(error);
		for (Object object : consumed) {
			out.println(object);
		}
	}
}