package org.tool.time;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.tool.time.parser.ConsumeManagerOutput;

public class OutputPrinter implements ConsumeManagerOutput {
	
	private final List<Object> consumed;
	private final PrintStream out;
	
	public OutputPrinter(PrintStream out) {
		super();
		this.out = out;
		this.consumed = new ArrayList<>(100);
	}

	public void printConsole() {
		for (Object object : consumed) {
			out.println(object);
		}
	}
	
	@Override
	public void consume(Object object) {
		consumed.add(object);		
	}

	@Override
	public void clear() {
		consumed.clear();		
	}

}