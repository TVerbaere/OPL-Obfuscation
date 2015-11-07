package com.tibo.obfuscation;

/**
 * Hello world!
 *
 */
public class App{
	
	private String titi = "Lala";
	private  Ppa ppa;
		
	public App() {
		ppa = new Ppa();
	}
	
	public App returnApp(App a) {
		return a;
	}
	
	public int fun(int x) {
		String var1 = "tata";
		var1 += ppa.toto(0);
		System.out.println(x+var1+titi);
		App ap = new App();
		
		ppa.\u0074\u006f\u0074\u006f(0);
		
		return \u0078;
	}
	
    public static void main(String[] arg) {
    		System.out.println( "Hello World!" );
    	
    		App app = new App();
    		System.out.println(app.fun(6));
    
    }
}
