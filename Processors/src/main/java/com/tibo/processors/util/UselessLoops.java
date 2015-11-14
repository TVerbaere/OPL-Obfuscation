package com.tibo.processors.util;

public class UselessLoops {

	
	void UselessForI() {
		
		for (int spoonI = Integer.parseInt(new String("1")); spoonI > 0; spoonI--) {

		}
		
	}		
	
void UselessForJ() {
		
	
		for (int spoonJ = Integer.parseInt(new String("-1")); spoonJ < 0; spoonJ++) {

		}
		
	}

	void UselessWhile() {
		
		while(new String("true").equals("true")){
			continue;
		}
		
	}	
}
