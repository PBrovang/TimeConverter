package org.tool.time.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HelpTest implements ConsumeManagerOutput {
	
	private static StringBuilder loremText;
	private Help help;
	private List<Object> consumed;
	private boolean cleared;	
	
	@BeforeClass
	public static void setUpLorem() throws Exception {
		loremText = new StringBuilder(
				"Quisque bibendum sapien vitae nulla volutpat rhoncus.\n"
				+ "Mauris quis euismod sem. Duis convallis scelerisque \n"
				+ " metus, eu auctor est commodo eu. \n "
				+ "Suspendisse euismod neque dui, id elementum nibh \n"
				+ "convallis in.");
	}
	
	@AfterClass
	public static void tearDownLorem() throws Exception {
		loremText = null;
	}
	
	@Override
	public void consume(Object object) {
		//System.out.println(object);
		consumed.add(object);	
	}

	@Override
	public void clear() {
		this.cleared = true;
	}

	@Before
	public void setUp() throws Exception {
		this.cleared = false;
		this.consumed = new ArrayList<>(5);
		List<HelpMessage> messages = Arrays.asList(
			new HelpMessage("a", "Alpha"),
			new HelpMessage("g", "Gamma"),
			new HelpMessage("lorem", loremText.toString())
		);
		ConsumeManagerOutput output = this;
		this.help = new Help(messages, output);
	}

	@Test
	public void testConsume() throws Exception {
		String[] arguments = new String[]{"-h"};
		assertEquals("Offset", 1, help.consume(0, arguments));
		String[] expecteds = {
			Help.TITLE,
			"    -h|H      Help, describing functionalities",
			"    -a        Alpha",
			"    -g        Gamma",
			"    -lorem    Quisque bibendum sapien vitae nulla volutpat rhoncus.",
			"              Mauris quis euismod sem. Duis convallis scelerisque",
			"              metus, eu auctor est commodo eu.",
			"              Suspendisse euismod neque dui, id elementum nibh",
			"              convallis in."
			
		};
		Object[] actuals = consumed.toArray();
		assertTrue("Cleared", cleared);
		assertArrayEquals(expecteds, actuals);
	}

}
