package org.tool.time;

import java.util.Arrays;



public class Main {
	
	public Main() {
		super();

	}

	protected void execute(String[] arguments) {
		System.out.println(Arrays.toString(arguments));
	}
	
	public static void main(String[] arguments) {
		Main main = new Main();
		main.execute(arguments);
	}
}