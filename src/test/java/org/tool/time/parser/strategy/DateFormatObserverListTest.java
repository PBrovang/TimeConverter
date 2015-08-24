package org.tool.time.parser.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class DateFormatObserverListTest {
	
	private DateFormatObserver observer1;
	private DateFormatObserver observer2;
	private DateFormatObserverList observerList;
	private DateFormat actualDateFormat1;
	private DateFormat actualDateFormat2;
	
	@Before
	public void setUp() throws Exception {
		observer1 = new DateFormatObserver() {			
			@Override
			public void update(DateFormat dateFormat) {
				actualDateFormat1 = dateFormat;				
			}
		};
		observer2 = new DateFormatObserver() {			
			@Override
			public void update(DateFormat dateFormat) {
				actualDateFormat2 = dateFormat;				
			}
		};
		observerList = new DateFormatObserverList(Arrays.asList(observer1));
	}

	@Test
	public void testDateFormatObserverList() {
		observerList = new DateFormatObserverList();
		assertTrue(observerList.getObservers().isEmpty());
	}
	
	@Test
	public void testGetObservers() throws Exception {
		HashSet<DateFormatObserver> expected = new HashSet<>(Arrays.asList(observer1));
		assertEquals(expected, observerList.getObservers());
	}

	@Test
	public void testAttach() throws Exception {
		observerList.attach(observer1);
		testGetObservers();
	}
	
	@Test
	public void testAttach_Addnull() throws Exception {
		observerList.attach(null);
		testGetObservers();
	}

	@Test
	public void testDetach() {
		observerList.detach(observer1);
		assertTrue("Empty", observerList.getObservers().isEmpty());
	}

	@Test
	public void testUpdate() {
		observerList.attach(observer2);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		observerList.update(df);
		assertSame("1", df, actualDateFormat1);
		assertSame("2", df, actualDateFormat2);
	}
}