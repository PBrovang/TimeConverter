package org.tool.time.parser.strategy;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ConsumeOutput;

public class DateIntoLongArgumentStrategyTest implements ConsumeOutput, DateFormatSubject {

	private static SimpleDateFormat sdf;
	private DateIntoLongArgumentStrategy strategy;
	private List<Object> actualOutput;
	private String s1, s2, s3;

	@BeforeClass
	public static void setUpDateFormat() throws Exception {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}

	@AfterClass
	public static void tearDownDateFormat() throws Exception {
		sdf = null;
	}
	
	private static long parse(String source) throws Exception {
		Date date = sdf.parse(source);
		return date.getTime();
	}
	
	@Override
	public SimpleDateFormat getDateFormat() {
		return sdf;
	}
	
	@Override
	public void consume(Object object) {
		this.actualOutput.add(object);
	}

	@Before
	public void setUp() throws Exception {
		actualOutput = new ArrayList<>(5);
		s1 = "2014-01-01";
		s2 = "2015-12-30";
		s3 = "2008-02-29";
		ConsumeOutput output = this;
		DateFormatSubject subject = this;
		strategy = new DateIntoLongArgumentStrategy("F", output, new MilliSecondsFactory(), subject);
	}

	@Test(expected = ArgumentException.class)
	public void testConsume_MissingArguments() throws Exception {
		String[] arguments = {"-F"};
		strategy.consume(0, arguments);
	}
	
	@Test(expected = ArgumentException.class)
	public void testConsume_FaultyDateValue() throws Exception {
		String[] arguments = {"-F", "2015-BUM-25"};
		strategy.consume(0, arguments);
	}

	
	@Test
	public void testConsumeOneOrMany() throws Exception {
		String[] arguments = {"-F", s1, s2, s3};
		Object[] expecteds = {parse(s1), parse(s2), parse(s3)};
		assertEquals("Offset", 3, strategy.consume(0, arguments));
		assertArrayEquals(expecteds, actualOutput.toArray());
	}
}
