package org.tool.time;

import static org.junit.Assert.assertArrayEquals;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tool.test.mocks.java.io.MockOutputStream;
import org.tool.time.arguments.HelpTexts;
import org.tool.time.arguments.OutputPrinter;
import org.tool.time.parser.ArgumentException;
import org.tool.time.parser.ArgumentParser;
import org.tool.time.parser.ArgumentParserBuilder;
import org.tool.time.parser.ArgumentParserBuilderException;
import org.tool.time.parser.ConsumeManagerOutput;
import org.tool.time.parser.Help;
import org.tool.time.parser.HelpMessage;

public class MainTest {
	
	private ArgumentParserBuilder builderError;
	private ArgumentParserBuilder builderParserError;
	private static ConsumeManagerOutput output;
	private static List<Object> actuals;
	private String buildErrorMessage;
	private String parseErrorMessage;

	@BeforeClass
	public static void setUpOutputConsumer() throws Exception {
		actuals = new ArrayList<>(10);
		PrintStream out = new PrintStream(new MockOutputStream()) {
			@Override
			public void println(String x) {
				actuals.add(x);
			}
			@Override
			public void println(Object x) {
				actuals.add(x);
			}
		};
		output = new OutputPrinter(out) ;
	}
	
	@AfterClass
	public static void tearDownOutput() throws Exception {
		actuals = null;
		output = null;
	}
	
	@After
	public void tearDownResetPrinted() throws Exception {
		actuals.clear();
	}
	
	@Before
	public void setUpBuilder() throws Exception {
		buildErrorMessage = "builder shall fail";
		builderError = new ArgumentParserBuilder() {
			@Override
			public ArgumentParser build() throws ArgumentParserBuilderException {
				throw new ArgumentParserBuilderException(buildErrorMessage);
			}
		};
	}
	
	@Before
	public void setUpArgumentParser() throws Exception {
		parseErrorMessage = "parser must fail";
		final ArgumentParser argumentParser = new ArgumentParser() {
			@Override
			public Help getHelp() {
				List<HelpMessage> list = Collections.emptyList();
				return new Help(list, output);
			}
			@Override
			public void execute(String[] arguments) throws ArgumentException {
				throw new ArgumentException(parseErrorMessage);
			}
		};
		this.builderParserError = new ArgumentParserBuilder() {
			@Override
			public ArgumentParser build() throws ArgumentParserBuilderException {
				return argumentParser;
			}
		};
	}

	@After
	public void tearDown() throws Exception {
		builderError = null;
		builderParserError = null;
	}

	@Test
	public void testExecute_ArgumentParserBuilderFails() throws Exception {
		Main main = new Main(output, builderError);
		String[] arguments = {"-h"};
		main.execute(arguments);
		Object[] expecteds = {buildErrorMessage};
		assertArrayEquals(expecteds, actuals.toArray());
	}

	@Test
	public void testExecutingFails() throws Exception {
		Main main = new Main(output, builderParserError);
		String[] arguments = {"-h"};
		main.execute(arguments);
		Object[] expecteds = {parseErrorMessage, HelpTexts.HELP_TITLE};
		assertArrayEquals(expecteds, actuals.toArray());
	}
}
