package org.tool.time.parser.strategy;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnixSecondsIntoDateArgumentStrategyTest {
	
	private static SimpleDateFormat df;
	private UnixSecondsIntoDateArgumentStrategy strategy;

	@BeforeClass
	public static void setUpDateFormat() throws Exception {
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	@Before
	public void setUp() throws Exception {
		this.strategy = new UnixSecondsIntoDateArgumentStrategy("c", null);
	}

	@Test
	public void testGetNumberFormatException() {
		Exception object = strategy.getNumberFormatException("Hejsa");
		assertNotNull(object);
	}

	@Test
	public void testCreateDate() throws Exception {
		Date expected = df.parse("2041-01-31 23:45:55");
		long seconds = expected.getTime() / 1000;
		String text = String.valueOf(seconds);
		assertEquals(expected, strategy.createDate(text));
	}
}