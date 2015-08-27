package org.tool.time.arguments;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.tool.time.arguments.ArgumentBuilder;
import org.tool.time.parser.ArgumentParser;
import org.tool.time.parser.ArgumentParserBuilder;
import org.tool.time.parser.ConsumeManagerOutput;
import org.tool.time.parser.strategy.DateFormatObserver;
import org.tool.time.parser.strategy.DateFormatSubject;

public class ArgumentBuilderTest {

	@Test
	public void testBuild() throws Exception {
		DateFormatSubject subject = null;
		DateFormatObserver observer = null;
		ConsumeManagerOutput output = null;
		ArgumentParserBuilder builder = new ArgumentBuilder(subject, observer, output);
		ArgumentParser parser = builder.build();
		assertNotNull("Parser", parser);
		assertNotNull("Help", parser.getHelp());
	}
}
