package org.tool.time.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CharFlagTest {
	
	private CharFlag flag;
	private char symbol;
		
	@Before
	public void setUp() throws Exception {
		symbol = 'c';
		flag = new CharFlag(symbol);
		Character.isLetter(symbol);
	}

	@Test
	public void testCharFlag_UpperCase() throws Exception {
		symbol = 'F';
		flag = new CharFlag(symbol);
		testGetFlag();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCharFlag_IllegalSymbol() throws Exception {
		symbol = '#';
		flag = new CharFlag(symbol);
		testGetFlag();
	}
	
	@Test
	public void testIsLetter() throws Exception {
		assertFalse("at sign", CharFlag.isLetter('@'));
		assertFalse("Left Square Bracket", CharFlag.isLetter('['));
		assertFalse("Left Curly Bracket", CharFlag.isLetter('{'));
	}
	
	@Test
	public void testGetFlag() {
		String expected = "-" + symbol;
		String actual = flag.getFlag();
		assertEquals(expected, actual);
	}

	@Test
	public void testHashCode() throws Exception {
		int expected = symbol + CharFlag.HAHS_CODE_PRIME;
		int actual = flag.hashCode();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEqualsSame() throws Exception {
		assertTrue(flag.equals(flag));
	}
	
	@Test
	public void testEqualsNull() throws Exception {
		assertFalse(flag.equals(null));
	}
	
	@Test
	public void testEqualsWrongClass() throws Exception {
		assertFalse(flag.equals(226));
	}
	
	@Test
	public void testEqualsDifferentFlag() throws Exception {
		symbol ++;
		assertFalse(flag.equals(new CharFlag(symbol)));
	}
	
	@Test
	public void testEqualsSameSymbol() throws Exception {
		assertTrue(flag.equals(new CharFlag(symbol)));
	}
	
	@Test
	public void testIsFlag() {
		String value = "-c";
		assertTrue("The correct flag", flag.isFlag(value));
		value = "-d";
		assertFalse("The wrong flag", flag.isFlag(value));
	}
	
	@Test
	public void testToString() throws Exception {
		assertEquals(flag.getFlag(), flag.toString());
	}

}
