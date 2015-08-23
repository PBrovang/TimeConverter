package org.tool.time.convert;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class UnixToDateConvertorTest extends AbstractConvertorTest {

	private UnixToDateConvertor factory;

	@Before
	public void setUp() throws Exception {
		factory = new UnixToDateConvertor(df);
	}
	
	@Test
	public void testGetValue() throws Exception {
		String expected = "2008-02-29 15:16:25";
		Date date = df.parse(expected);
		String actual   = factory.getValue(date.getTime() / 1000);
		assertEquals(expected, actual);
	}
}
