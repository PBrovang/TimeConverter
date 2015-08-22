package org.tool.time.convert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.BeforeClass;

public class AbstractDateTest {

	protected static DateFormat df;

	@BeforeClass
	public static void setUpDateFormat() throws Exception {
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

}
