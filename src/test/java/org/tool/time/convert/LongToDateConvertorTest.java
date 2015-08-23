package org.tool.time.convert;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LongToDateConvertorTest extends AbstractConvertorTest {
	private LongToDateConvertor factory;
	
	@Before
	public void setUp() throws Exception {
		factory = new LongToDateConvertor(df);		
	}
	
	@Test
	public void testGetValue() throws Exception {
		String expected = "2001-05-04 10:15:13";
		String actual   = factory.getValue(df.parse(expected).getTime());
		assertEquals(expected, actual);
	}
}
