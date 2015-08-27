package org.tool.time.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlagUtilTest {
	
	@Test
	public void testCoverage() throws Exception {
		assertNotNull(new FlagUtil());
	}
	
	@Test
	public void testValidate() throws Exception {
		String symbols = "delta";
		FlagUtil.validate(symbols);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidate_InvalidEmpty() throws Exception {
		String symbols = "";
		FlagUtil.validate(symbols);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidate_IllegalCharacter() throws Exception {
		String symbols = "S&G";
		FlagUtil.validate(symbols);
	}
	
	@Test
	public void testDecodeSymbol() throws Exception {
		String argument = "-a";
		String expected = "a";
		String actual = FlagUtil.decodeSymbol(argument);
		assertEquals(expected, actual);
	}

	@Test(expected = ArgumentException.class)
	public void testDecodeSymbol_IllegalFlagOfOneSymbol() throws Exception {
		String argument = "D";
		FlagUtil.decodeSymbol(argument);
	}

	@Test(expected = ArgumentException.class)
	public void testDecodeSymbol_IllegalFlag() throws Exception {
		String argument = "invalid";
		FlagUtil.decodeSymbol(argument);
	}
	
	@Test(expected = ArgumentException.class)
	public void testDecodeSymbolsIntoFlag_IllegalCharacter() throws Exception {
		String argument = "-a@d";
		FlagUtil.decodeSymbol2Flag(argument);
	}
	
	@Test
	public void testIsFlag() throws Exception {
		assertFalse("Empty argument", FlagUtil.isFlag(""));
		assertTrue("Valid flag", FlagUtil.isFlag("-w"));
	}
	

}
