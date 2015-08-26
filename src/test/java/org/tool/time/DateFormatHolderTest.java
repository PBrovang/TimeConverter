package org.tool.time;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
