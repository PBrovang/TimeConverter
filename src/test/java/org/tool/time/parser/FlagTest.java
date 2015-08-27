package org.tool.time.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FlagTest {
	

	private Flag flag;
	private char symbol;
		
	@Before
	public void setUp() throws Exception {
		symbol = 'c';
		flag = new Flag(symbol);		
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testCharFlag_IllegalSymbol() throws Exception {
		symbol = '#';
		flag = new Flag(symbol);
	}
	
	@Test
	public void testHashCode() throws Exception {
		int expected = symbol + Flag.HAHS_CODE_PRIME;
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
	public void testEqualsDifferentSymbol() throws Exception {
		symbol ++;
		assertFalse(flag.equals(new Flag(symbol)));
	}
	
	@Test
	public void testEqualsSameSymbol() throws Exception {
		Flag other = new Flag(symbol);
		assertTrue(flag.equals(other));
	}
	
	@Test
	public void testToString() throws Exception {
		String expected = "-c";
		assertEquals(expected, flag.toString());
	}
}
