package org.tool.time.test;

import org.tool.test.TestException;
import org.tool.time.parser.ConsumeManagerOutput;

public class MockConsumeManagerOutput implements ConsumeManagerOutput {

	public MockConsumeManagerOutput() {
		super();
	}

	@Override
	public void consume(Object object) {
		throw new TestException(String.valueOf(object));
	}

	@Override
	public void setError(String message) {
		throw new TestException(message);
		
	}

	@Override
	public void clear() {
		throw new TestException("Clear");
	}

	@Override
	public void executeConsole() {
		throw new TestException();
	}
}
