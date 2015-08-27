package org.tool.time.parser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelpMessageTest {
	
	private HelpMessage help;
	private Flag flag;
	private String text;

	@Before
	public void setUp() throws Exception {
		flag = new Flag("s");
		text = "Hello Sierra";
		help = new HelpMessage(flag, text);
	}

	@After
	public void tearDown() throws Exception {
		help = null;
		flag = null;
		text = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorArgumentFlagIsNull() throws Exception {
		flag = null;
		help = new HelpMessage(flag, text);
	}
	
	@Test
	public void testHashCode() {
		int expected = System.identityHashCode(help);
		assertEquals(expected, help.hashCode());
	}

	@Test
	public void testHelpMessage() {
		text = "";
		help = new HelpMessage(flag, null);
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
		assertFalse(help.equals(null));
	}
	
	@Test
	public void testEqualsDifferentFlag() throws Exception {
		assertFalse(help.equals(new HelpMessage("a", text)));
	}
	
	@Test
	public void testEqualsDifferentText() throws Exception {
		assertFalse(help.equals(new HelpMessage(flag, "bummelum")));
	}

	@Test
	public void testEqualsDifferentClass() throws Exception {
		assertFalse(help.equals(new Object()));
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
