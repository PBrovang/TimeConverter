package org.tool.time.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ArgumentConsumerTest {

	private class MockArgumentStrategy implements ArgumentStrategy {
		private int eatOffset;
		private String symbols;
		
		public MockArgumentStrategy(String symbols) {
			this(symbols, 0);
		}
		
		public MockArgumentStrategy(String symbols, int eat) {
			super();
			this.eatOffset = eat;
			this.symbols = symbols;
		}
		
		@Override
		public int consume(int index, String[] arguments) {
			actualList.add(symbols);
			for (int i = index + 1; i < eatOffset + index + 1; i++) 
				actualList.add(arguments[i]);
			return eatOffset;
		}

		@Override
		public String getSymbols() {
			return this.symbols;
		}
	}
	
	private ArgumentParser consumer;
	private String[] arguments;
	private List<String> actualList;
	
	@Before
	public void setUpConsumer() throws Exception {
		actualList = new ArrayList<>(20);
		arguments = new String[]{
			"-a",
			"-b", "beta",
			"-c", "C1", "C2", "C3",
			"-d"
		};
		List<? extends ArgumentStrategy> strategyList = Arrays.asList(
			new MockArgumentStrategy("a"),
			new MockArgumentStrategy("b", 1),
			new MockArgumentStrategy("c", 3),
			new MockArgumentStrategy("d")
		);
		consumer = new ArgumentConsumer(strategyList);
	}
	
	@Test(expected = ArgumentException.class)
	public void testArgumentConsumer() throws Exception {
		List<? extends ArgumentStrategy> list = Collections.emptyList();
		consumer = new ArgumentConsumer(list);
	}
	
	@Test
	public void testExecute() throws Exception {
		List<String> expectedList = Arrays.asList(
			"a",
			"b", "beta",
			"c", "C1", "C2", "C3",
			"d"
		);
		consumer.execute(arguments);
		assertArrayEquals(expectedList.toArray(), actualList.toArray());	
	}
	
	@Test(expected = ArgumentException.class)
	public void testExecute_GutterArguments() throws Exception {
		arguments = new String[] {"-bum", "-bim"};
		consumer.execute(arguments);
	}
}