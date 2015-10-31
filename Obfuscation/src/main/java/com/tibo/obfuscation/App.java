package com.tibo.obfuscation;

/**
 * Hello world!
 *
 */
public class App {
	
	private String titi = "Lala";
	
	public void fun(int x) {
		String var1 = "tata";
		System.out.println(x+var1+titi);
	}
	
    public static void main( String[] args ) {
    	try {
    		System.out.println( "Hello World!" );
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }
}
