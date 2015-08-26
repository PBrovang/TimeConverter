package org.tool.time.parser.strategy;

import java.text.SimpleDateFormat;

public interface DateFormatObserver {
	
	public DateFormatObserver NULL = new DateFormatObserver() {
		@Override
		public void update(SimpleDateFormat dateFormat) {
			/* Ignore null-pattern */
		}
	};

	public void update(SimpleDateFormat dateFormat);

	
}
