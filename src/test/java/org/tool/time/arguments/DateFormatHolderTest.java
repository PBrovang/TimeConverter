package org.tool.time.arguments;

import static org.junit.Assert.assertSame;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.tool.time.arguments.DateFormatHolder;

public class DateFormatHolderTest {
	private DateFormatHolder holder;
	private SimpleDateFormat sdf;
	
	@Before
	public void setUp() throws Exception {
		sdf = new SimpleDateFormat();
		holder = new DateFormatHolder(sdf);
	}

	@Test
	public void testGetDateFormat() {
		assertSame(sdf, holder.getDateFormat());
	}

	@Test
	public void testUpdate() {
		sdf = new SimpleDateFormat();
		holder.update(sdf);
		testGetDateFormat();
	}

}
