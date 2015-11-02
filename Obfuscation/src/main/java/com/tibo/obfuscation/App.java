package com.tibo.obfuscation;

/**
 * Hello world!
 *
 */
public class App {
	
	private static String titi = "Lala";
		
	public App() {
		
	}
	
	public App returnApp(App a) {
		return a;
	}
	
	public static int fun(int x) {
		String var1 = "tata";
		System.out.println(x+var1+titi);
		App ap = new App();
		//Nana n = new Nana();
		//n.nono();
		return x;
	}
	
    public static void main( String[] args ) {
    	try {
    		System.out.println( "Hello World!" );
    		fun(6);
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }
}
