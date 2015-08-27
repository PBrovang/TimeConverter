package org.tool.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tool.test.mocks.java.io.MockOutputStream;

public class MainStaticMainMethodTest {
	private SimpleDateFormat sdf;
	private PrintStream systemOut;
	private Object actual;

	@Before
	public void setUpDateFormat() throws Exception {
		sdf = new SimpleDateFormat("yyyy-MM-dd");		
	}
	
	@Before
	public void setUp() throws Exception {
		systemOut = System.out;
		System.setOut(new PrintStream(new MockOutputStream()){
			@Override
			public void println(Object x) {
				assertNull("Actual has been assinged", actual);
				actual = x;
			}
		});
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(systemOut);
		sdf = null;
	}
	
	@Test
	public void testMain() throws Exception {
		String source = "2008-02-29";
		Date d = sdf.parse(source);
		String[] arguments = {
			"-f", sdf.toPattern(),
			"-l", source
		};
		Main.main(arguments);
		Object expected = d.getTime();
		assertEquals(expected, actual);
	}

}
