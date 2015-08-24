package org.tool.time.parser.strategy;

import java.util.Date;

public class SecondsFactory implements LongFactory {

	@Override
	public long create(Date date) {
		return date.getTime() / 1000;
	}

}
