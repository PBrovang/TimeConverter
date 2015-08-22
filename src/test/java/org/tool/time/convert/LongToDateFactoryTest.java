package org.tool.time.convert;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LongToDateFactoryTest extends AbstractDateTest {
	private LongToDateFactory factory;
	
	@Before
	public void setUp() throws Exception {
		factory = new LongToDateFactory(df);		
	}
	
	@Test
	public void testGetDate() throws Exception {
		String expected = "2001-05-04 10:15:13";
		String actual   = factory.getDate(df.parse(expected).getTime());
		assertEquals(expected, actual);
	}
}
