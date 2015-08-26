package org.tool.time.parser.strategy;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ConsumeOutput;

public class ToDateArgumentStrategyTest implements ConsumeOutput, DateFormatSubject {
	private static SimpleDateFormat sdf;
	private ToDateArgumentStrategy strategy;
	private List<Object> actualOutputs;
	
	@BeforeClass
	public static void setUpDateFormat() throws Exception {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	@Override
	public SimpleDateFormat getDateFormat() {
		return sdf;
	}
	
	@Override
	public void consume(Object object) {
		actualOutputs.add(object);
	}

	private static Date parse(String source) throws Exception{
		return sdf.parse(source);
	}
	
	private static String valueOf(Date date) {
		return String.valueOf(date.getTime());
	}
	
	@Before
	public void setUp() throws Exception {
		actualOutputs = new ArrayList<>(4);
		ConsumeOutput output = this;
		DateFormatSubject subject = this;
		strategy = new ToDateArgumentStrategy("u", output, new DateMillisecondsFactory(), subject);
	}
	
	@Test(expected = ArgumentException.class)
	public void testConsume_FirstElementFault() throws Exception {
		String[] arguments = {"-u", "badness"};
		strategy.consume(0, arguments);
	}
	
	@Test
	public void testConsumeMany() throws Exception {
		/* 1 or many UTC millisecond */
		String s1 = "2014-10-29";
		String s2 = "2009-04-01";
		String s3 = "2011-12-24";
		Date l1 = parse(s1);
		Date l2 = parse(s2);
		Date l3 = parse(s3);
		String[] arguments = {"-u", valueOf(l1), valueOf(l2), valueOf(l3)};
		assertEquals("Offset", 3, strategy.consume(0, arguments));
		Object[] expecteds = {s1, s2, s3};
		Object[] actuals = actualOutputs.toArray();
		assertArrayEquals(expecteds, actuals);
	}
}