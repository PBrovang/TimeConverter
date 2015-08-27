package org.tool.time.arguments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tool.test.mocks.java.io.MockOutputStream;
import org.tool.time.arguments.OutputPrinter;

public class OutputPrinterTest {

	private List<Object> printed;
	private OutputPrinter printer;
	
	@Before
	public void setUp() throws Exception {
		printed = new ArrayList<>(20);
		PrintStream out = new PrintStream(new MockOutputStream()){
			@Override
			public void println(Object x) {
				printed.add(x);
			}
		};
		printer = new OutputPrinter(out);
	}

	@Test
	public void testConsume() {
		String s1 = "Alpha";
		String s2 = "Bravo";
		String s3 = "Charlie";
		printer.consume(s1);
		printer.consume(s2);
		printer.consume(s3);
		printer.executeConsole();
		assertEquals(Arrays.asList(s1, s2, s3), printed);
	}

	@Test
	public void testClear() {
		String s1 = "Alpha";
		String s2 = "Bravo";
		String s3 = "Charlie";
		printer.consume(s1);
		printer.consume(s2);
		printer.consume(s3);
		printer.clear();
		printer.executeConsole();
		assertTrue(printed.isEmpty());
	}

}
