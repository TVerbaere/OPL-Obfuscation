package com.tibo.obfuscation;


/**
 * Hello world!
 *
 */
public class Pol{
	
	private String titi = "Lala";
	private  Ppa ppa;
		
	public Pol() {
		ppa = new Ppa();
	}
	
	public Pol returnApp(Pol a) {
		return a;
	}
	
	public int fun(int x) {
		String var1 = "tata";
		var1 += ppa.toto(0);
		System.out.println(x+var1+titi);
		Pol ap = new Pol();
		
		ppa.toto(0);
		
		return x;
	}
	
    public static void main(String[] arg) {
    		System.out.println( "Hello World!" );
    		    	
    		Pol app = new Pol();
    		
    		assert true;
    		
    		System.out.println(app.fun(6));
    
    }
}
