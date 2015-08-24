package org.tool.time.parser.strategy;

import java.text.DateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DateFormatObserverList implements DateFormatObserver {

	private final Set<DateFormatObserver> observers;

	public DateFormatObserverList(List<DateFormatObserver> observers) {
		super();
		this.observers = new HashSet<>(observers);
	}

	public DateFormatObserverList() {
		super();
		this.observers = new HashSet<>(10);
	}
	
	public Set<? extends DateFormatObserver> getObservers() {
		return observers;
	}

	public void attach(DateFormatObserver observer) {
		if (observer != null)
			this.observers.add(observer);
	}

	public void detach(DateFormatObserver observer) {
		this.observers.remove(observer);
	}

	@Override
	public void update(DateFormat dateFormat) {
		for (DateFormatObserver observer : observers) {
			observer.update(dateFormat);
		}		
	}
}
