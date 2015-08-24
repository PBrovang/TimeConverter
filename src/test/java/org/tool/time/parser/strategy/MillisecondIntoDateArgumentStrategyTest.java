package org.tool.time.parser.strategy;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ConsumeOutput;

public class MillisecondIntoDateArgumentStrategyTest implements ConsumeOutput {
	private static DateFormat df;
	private MillisecondIntoDateArgumentStrategy strategy;
	private List<Object> actualOutputs;
	
	@BeforeClass
	public static void setUpDateFormat() throws Exception {
		df = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	@Override
	public void consume(Object object) {
		actualOutputs.add(object);
	}

	private static Date parse(String source) throws Exception{
		return df.parse(source);
	}
	
	private static String valueOf(Date date) {
		return String.valueOf(date.getTime());
	}
	
	@Before
	public void setUp() throws Exception {
		actualOutputs = new ArrayList<>(4);
		strategy = new MillisecondIntoDateArgumentStrategy("u", this);
	}

	@Test
	public void testConsumeOne() throws Exception {
		/* 1 or many UTC millisecond */
		Date l1 = parse("2014-10-29");
		String[] arguments = {"-u", valueOf(l1)};
		assertEquals("Offset", 1, strategy.consume(0, arguments));
		Object[] expecteds = {l1};
		Object[] actuals = strategy.getValues();
		assertArrayEquals(expecteds, actuals);
	}
	
	@Test(expected = ArgumentException.class)
	public void testConsume_FirstElementFault() throws Exception {
		String[] arguments = {"-u", "badness"};
		strategy.consume(0, arguments);
	}

	
	@Test
	public void testConsumeMany() throws Exception {
		/* 1 or many UTC millisecond */
		Date l1 = parse("2014-10-29");
		Date l2 = parse("2009-04-01");
		Date l3 = parse("2011-12-24");
		String[] arguments = {"-u", valueOf(l1), valueOf(l2), valueOf(l3)};
		assertEquals("Offset", 3, strategy.consume(0, arguments));
		Object[] expecteds = {l1, l2, l3};
		Object[] actuals = strategy.getValues();
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testUpdateBeforeConsume() throws Exception {
		/* 1 or many UTC millisecond */
		String s1 = "2014-10-29";
		String s2 = "2009-04-01";
		String s3 = "2011-12-24";
		Date l1 = parse(s1);
		Date l2 = parse(s2);
		Date l3 = parse(s3);
		String[] arguments = {"-u", valueOf(l1), valueOf(l2), valueOf(l3)};
		strategy.update(df);
		strategy.consume(0, arguments);
		String[] expecteds = {s1, s2, s3};
		assertArrayEquals(expecteds, actualOutputs.toArray());
	}

	
	@Test
	public void testUpdateAfterConsume() throws Exception {
		/* 1 or many UTC millisecond */
		String s1 = "2014-10-29";
		String s2 = "2009-04-01";
		String s3 = "2011-12-24";
		Date l1 = parse(s1);
		Date l2 = parse(s2);
		Date l3 = parse(s3);
		String[] arguments = {"-u", valueOf(l1), valueOf(l2), valueOf(l3)};
		strategy.consume(0, arguments);
		strategy.update(df);
		String[] expecteds = {s1, s2, s3};
		assertArrayEquals(expecteds, actualOutputs.toArray());
	}
}
