package org.tool.time.convert;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DateToLongFactoryTest extends AbstractDateTest {
	private DateToLongFactory factory;
	
	@Before
	public void setUp() throws Exception {
		factory = new DateToLongFactory(df);
	}
	
	
	@Test
	public void testGetValue() throws Exception {
		String source = "2007-05-28 12:15:30";
		Date date = df.parse(source);
		String expected = String.valueOf(date.getTime());
		String actual   = factory.getValue(source);
		assertEquals(expected, actual);
	}
}
