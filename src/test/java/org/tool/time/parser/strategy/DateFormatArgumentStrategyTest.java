package org.tool.time.parser.strategy;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.tool.time.parser.ArgumentException;

public class DateFormatArgumentStrategyTest implements DateFormatObserver {

	private DateFormatArgumentStrategy strategy;
	private DateFormat dateFormat;
	
	@Before
	public void setUp() throws Exception {
		String symbol = "F";
		strategy = new DateFormatArgumentStrategy(symbol, this);
	}

	@Override
	public void update(DateFormat dateFormat) {
		this.dateFormat = dateFormat;				
	}


	@Test
	public void testDateFormatArgumentStrategy() {
		strategy = new DateFormatArgumentStrategy("f", null);
		assertSame(DateFormatObserver.NULL, strategy.getObserver());
	}

	@Test
	public void testConsume() throws Exception {
		String[] arguments = {"-F", "yyyy-MM-dd"};
		int offset = strategy.consume(0, arguments);
		assertEquals("Index Offset", 1, offset);
		assertEquals("yyyy-MM-dd", ((SimpleDateFormat) dateFormat).toPattern());
	}
	
	@Test(expected = ArgumentException.class)
	public void testConsume_Fault() throws Exception {
		String[] arguments = {"-F", "bbbb-MM-dd"};
		strategy.consume(0, arguments);
	}
}