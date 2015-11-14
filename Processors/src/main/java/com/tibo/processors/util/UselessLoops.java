package com.tibo.processors.util;

public class UselessLoops {

	
	void UselessForI() {
		
		String spoonS = "1";
		int spoonN = Integer.parseInt("nombre");
		for (int spoonI = spoonN; spoonI > 0; spoonI--) {

		}
		
	}		
	
void UselessForJ() {
		
		String spoonS = "-1";
		int spoonN = Integer.parseInt("nombre");
		for (int spoonJ = spoonN; spoonJ < 0; spoonJ++) {

		}
		
	}

	void UselessWhile() {
		String testSpoon = "true";
		while(new String("true").equals(testSpoon)){
			testSpoon = "false";
		}
		
	}	
}
