package org.tool.time.parser.strategy;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class SecondsFactoryTest {

	private SecondsFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new SecondsFactory();		
	}
	
	@Test
	public void testCreate() {
		long time     = 142358997552521L;
		long expected = 142358997552L;
		Date date = new Date(time);
		long actual = factory.create(date);
		assertEquals(expected, actual);		
	}

}
