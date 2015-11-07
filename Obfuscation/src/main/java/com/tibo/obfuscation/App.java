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
		
		ppa.toto(0);
		
		return x;
	}
	
    public static void main(String[] arg) {
    		System.out.println( "Hello World!" );
    		    	
    		App app = new App();
    		
    		assert true;
    		
    		System.out.println(app.fun(6));
    
    }
}
