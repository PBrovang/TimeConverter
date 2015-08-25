package org.tool.time.parser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelpMessageTest {
	
	private HelpMessage help;
	private String flag;
	private String text;

	@Before
	public void setUp() throws Exception {
		flag = "s";
		text = "Hello Sierra";
		help = new HelpMessage(flag, text);
	}

	@After
	public void tearDown() throws Exception {
		help = null;
		flag = null;
		text = null;
	}

	@Test
	public void testHashCode() {
		int expected = System.identityHashCode(help);
		assertEquals(expected, help.hashCode());
	}

	@Test
	public void testHelpMessage() {
		flag = "";
		text = "";
		help = new HelpMessage(null, null);
		testGetFlag();
		testGetText();
	}

	@Test
	public void testEqualsSame() {
		assertTrue(help.equals(help));
	}
	
	@Test
	public void testEquals() {
		assertTrue(help.equals(new HelpMessage(flag, text)));
	}
	
	@Test
	public void testEqualsNull() throws Exception {
		assertFalse(flag.equals(null));
	}
	
	@Test
	public void testEqualsDifferentFlag() throws Exception {
		assertFalse(flag.equals(new HelpMessage("a", text)));
	}
	
	@Test
	public void testEqualsDifferentText() throws Exception {
		assertFalse(flag.equals(new HelpMessage(flag, "bummelum")));
	}

	@Test
	public void testGetFlag() {
		assertSame(flag, help.getFlag());
	}

	@Test
	public void testGetText() {
		assertSame(text, help.getText());
	}

	@Test
	public void testToString() {
		String expected = "HelpMessage [-s, Hello Sierra]";
		assertEquals(expected, help.toString());
	}

}
