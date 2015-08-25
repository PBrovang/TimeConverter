package org.tool.time.parser.strategy;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tool.time.parser.ArgumentException;

public class DateSecondsFactoryTest {
	
	private static SimpleDateFormat sdf;
	private DateSecondsFactory factory;

	@BeforeClass
	public static void setUpDateFormat() throws Exception {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	@AfterClass
	public static void tearDownDateFormat() throws Exception {
		sdf = null;
	}
	
	@Before
	public void setUp() throws Exception {
		factory = new DateSecondsFactory();
	}

	@Test
	public void testCreateDate() throws Exception {
		Date expected = sdf.parse("2014-10-25");
		long seconds = expected.getTime() / 1000;
		Date actual = factory.createDate(String.valueOf(seconds));
		assertEquals(expected, actual);
	}
	
	@Test(expected = ArgumentException.class)
	public void testCreateDate_Exception() throws Exception {
		String value = "HELO";
		factory.createDate(value);
	}
}
