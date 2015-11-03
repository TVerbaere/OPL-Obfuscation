package com.tibo.processors.util;

import java.lang.Math;
/**
 * Classe permettant de générer des noms aléatoirement.
 * @author thibaud
 *
 */
public class AleaName {
	
	/**
	 * Génere aléatoirement un nom pour une variable.
	 * @return un nom de variable
	 */
	 
	  
	  
	public static String variableNameAlea() {
		
		String name = generate();
		if(!SaveMap.alreadyGenerateforVariable(name) && !SaveMap.containsVariable(name))
		return name;
		
		return variableNameAlea();
	}
	
	public static String methodNameAlea() {
		
		String name = generate();
		
		if(!SaveMap.alreadyGenerateforMethod(name) && !SaveMap.containsMethod(name))
		return name;
		
		return methodNameAlea();
	}
	
	public static String classNameAlea() {
		
		String name = generate();
		
		if(!SaveMap.alreadyGenerateforClass(name) && !SaveMap.containsClass(name))
		return name;
		
		return classNameAlea();
	}
	
	public static String generate()
	{	
	    int length = 10 + (int)(Math.random() * ((100 - 10) + 1));

	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	    String pass = "";
	    for(int x=0;x<length;x++)
	    {
	       int i = (int)Math.floor(Math.random() * 52);
	       pass += chars.charAt(i);
	    }

	    return pass;
	}
}
