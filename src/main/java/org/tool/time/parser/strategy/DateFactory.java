package org.tool.time.parser.strategy;

import java.util.Date;

import org.tool.time.parser.ArgumentException;

public interface DateFactory {

	public Date createDate(String value) throws ArgumentException;
}
