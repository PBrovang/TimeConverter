package org.tool.time.parser.strategy;

import java.util.Date;

public class MilliSecondsFactory implements LongFactory {

	@Override
	public long create(Date date) {
		return date.getTime();
	}
	
}
