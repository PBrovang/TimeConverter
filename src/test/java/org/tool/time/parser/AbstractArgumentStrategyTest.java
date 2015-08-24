package org.tool.time.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AbstractArgumentStrategyTest {
	
	private static class TesStubAbstractArgumentStrategy extends AbstractArgumentStrategy {
		public TesStubAbstractArgumentStrategy(char symbol) throws IllegalArgumentException {
			super(symbol);
		}
		public TesStubAbstractArgumentStrategy(String symbols) throws IllegalArgumentException {
			super(symbols);
		}
		@Override
		public int consume(int index, String[] arguments) throws ArgumentException {
			throw new RuntimeException("Test Stub consume method should be called");
		}
	}
	
	private AbstractArgumentStrategy strategy;
	private char symbol;
		
	@Before
	public void setUp() throws Exception {
		symbol = 'c';
		strategy = new TesStubAbstractArgumentStrategy(symbol);
		Character.isLetter(symbol);
	}

	@Test
	public void testGetSymbols() throws Exception {
		String expected = "" + symbol;
		assertEquals(expected, strategy.getSymbols());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCharFlag_IllegalSymbol() throws Exception {
		symbol = '#';
		strategy = new TesStubAbstractArgumentStrategy(symbol);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCharFlag_EmptySymbols() throws Exception {
		String symbols = "";
		strategy = new TesStubAbstractArgumentStrategy(symbols);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCharFlag_IllegalSymbols() throws Exception {
		String symbols = "S A";
		strategy = new TesStubAbstractArgumentStrategy(symbols);
	}

	
	@Test
	public void testIsLetter() throws Exception {
		assertFalse("at sign", FlagUtil.isLetter('@'));
		assertFalse("Left Square Bracket", FlagUtil.isLetter('['));
		assertFalse("Left Curly Bracket", FlagUtil.isLetter('{'));
		
		assertTrue("Lower a", FlagUtil.isLetter('a'));
		assertTrue("Lower z", FlagUtil.isLetter('z'));
		assertTrue("Upper A", FlagUtil.isLetter('A'));
		assertTrue("Upper Z", FlagUtil.isLetter('Z'));
	}
	
	@Test
	public void testHashCode() throws Exception {
		int expected = symbol + AbstractArgumentStrategy.HAHS_CODE_PRIME;
		int actual = strategy.hashCode();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEqualsSame() throws Exception {
		assertTrue(strategy.equals(strategy));
	}
	
	@Test
	public void testEqualsNull() throws Exception {
		assertFalse(strategy.equals(null));
	}
	
	@Test
	public void testEqualsWrongClass() throws Exception {
		assertFalse(strategy.equals(226));
	}
	
	@Test
	public void testEqualsDifferentSymbol() throws Exception {
		symbol ++;
		assertFalse(strategy.equals(new TesStubAbstractArgumentStrategy(symbol)));
	}
	
	@Test
	public void testEqualsSameSymbol() throws Exception {
		AbstractArgumentStrategy other = new TesStubAbstractArgumentStrategy(symbol);
		assertTrue(strategy.equals(other));
	}
	
	@Test
	public void testToString() throws Exception {
		String expected = "-c " + TesStubAbstractArgumentStrategy.class.getSimpleName();
		assertEquals(expected, strategy.toString());
	}

	@Test
	public void testReadNextArgument() throws Exception {
		String expected = "delta";
		String[] arguments = {"-c", expected};
		String actual = strategy.readNextArgument(0, arguments);
		assertEquals(expected, actual);
	}
	
	@Test(expected = ArgumentException.class)
	public void testReadNextArgument_NoMoreArguments() throws Exception {
		String expected = "delta";
		String[] arguments = {"-c", expected};
		strategy.readNextArgument(1, arguments);
	}
	
	@Test
	public void testReadNextArguments() throws Exception {
		String s1 = "Delta";
		String s2 = "Lima";
		String s3 = "Charlie";
		String[] expecteds = {s1, s2, s3};
		String[] arguments = {"-E", "-c", s1, s2, s3};
		String[] actuals = strategy.readNextArguments(1, arguments, 3);
		assertArrayEquals(expecteds, actuals);
	}
	
	@Test(expected = ArgumentException.class)
	public void testReadNextMissingArguments() throws Exception {
		String[] arguments = {"-E", "-c"};
		strategy.readNextArguments(1, arguments, 3);
	}
	
	@Test(expected = ArgumentException.class)
	public void testReadNextArguments_MissingLastArgument() throws Exception {
		String s1 = "Delta";
		String s2 = "Lima";
		String[] arguments = {"-E", "-c", s1, s2};
		strategy.readNextArguments(1, arguments, 3);
	}
	
	@Test(expected = ArgumentException.class)
	public void testReadNextArguments_MissingTwoArguments() throws Exception {
		String s1 = "Delta";
		String[] arguments = {"-E", "-c", s1};
		strategy.readNextArguments(1, arguments, 3);
	}
	
	@Test
	public void testReadNextUntilFlag() throws Exception {
		String s1 = "Golf";
		String s2 = "Holte";
		String s3 = "Kilo";
		String flag = "-c";
		String nextFlag = "-v";
		String[] expecteds = {s1, s2, s3};
		String[] arguments = {flag, s1, s2, s3, nextFlag};
		int index = 0;
		String[] actuals = strategy.readNextArgumentsUntilFlag(index, arguments);
		assertArrayEquals(expecteds, actuals);
	}
}
