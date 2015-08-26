package org.tool.time;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OutputPrinterTest {

	private List<Object> printed;
	private OutputPrinter printer;
	
	@Before
	public void setUp() throws Exception {
		printed = new ArrayList<>(20);
		PrintStream out = new PrintStream(new File("bummelum.xml")) {
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
		printer.printConsole();
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
		printer.printConsole();
		assertTrue(printed.isEmpty());
	}

}
