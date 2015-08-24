package org.tool.time.parser.strategy;

import java.text.DateFormat;

public interface DateFormatObserver {
	
	public DateFormatObserver NULL = new DateFormatObserver() {
		@Override
		public void update(DateFormat dateFormat) {
			/* Ignore null-pattern */
		}
	};

	public void update(DateFormat dateFormat);

	
}
