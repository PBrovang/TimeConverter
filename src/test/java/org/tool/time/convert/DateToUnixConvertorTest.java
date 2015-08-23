package org.tool.time.convert;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DateToUnixConvertorTest extends AbstractConvertorTest{
	
	private DateToUnixConvertor factory;
	
	@Before
	public void setUp() throws Exception {
		factory = new DateToUnixConvertor(df);
	}
	
	@Test
	public void testGetValue() throws Exception {
		String source = "2010-05-06 23:45:45";
		String expected = String.valueOf(df.parse(source).getTime() / 1000);
		String actual   = factory.getValue(source);
		assertEquals(expected, actual);
		
	}
}
