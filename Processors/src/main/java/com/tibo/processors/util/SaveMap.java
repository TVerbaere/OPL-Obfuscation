package com.tibo.processors.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant une sauvegarde des couples (anciens noms - nouveaux noms).
 * @author thibaud
 *
 */
public class SaveMap {
	
	private static Map<String, String> methodsMap = new HashMap<String, String>();
	private static Map<String, String> variablesMap = new HashMap<String, String>();
	private static Map<String, String> classesMap = new HashMap<String, String>();
	
	/**
	 * Permet de sauvegarder un couple pour une variable.
	 * @param key l'ancien nom de variable à sauvegarder
	 * @param value le nouveau nom de variable à sauvegarder
	 */
	public static void saveVariableChange(String key, String value) {
		variablesMap.put(key, value);
	}
	
	/**
	 * Teste la présence d'une sauvegarde pour un ancien nom d'une variable.
	 * @param key le nom de variable à tester
	 * @return le resultat du test
	 */
	public static boolean containsVariable(String key) {
		return variablesMap.containsKey(key);
	}
	
	/**
	 * Teste la présence d'une sauvegarde pour un nouveau nom de variable.
	 * @param value le nom de variable à tester
	 * @return le resultat du test
	 */
	public static boolean alreadyGenerateforVariable(String value) {
		return variablesMap.containsValue(value);
	}
		
	/**
	 * Permet de sauvegarder un couple pour une méthode.
	 * @param key l'ancien nom de méthode à sauvegarder
	 * @param value le nouveau nom de méthode à sauvegarder
	 */
	public static void saveMethodChange(String key, String value) {
		methodsMap.put(key, value);
	}
	
	/**
	 * Teste la présence d'une sauvegarde pour un ancien nom d'une méthode.
	 * @param key le nom de méthode à tester
	 * @return le resultat du test
	 */
	public static boolean containsMethod(String key) {
		return methodsMap.containsKey(key);
	}
	
	/**
	 * Teste la présence d'une sauvegarde pour un nouveau nom de méthode.
	 * @param value le nom de méthode à tester
	 * @return le resultat du test
	 */
	public static boolean alreadyGenerateforMethod(String value) {
		return methodsMap.containsValue(value);
	}
	
	/**
	 * Permet de sauvegarder un couple pour une classe.
	 * @param key l'ancien nom de classe à sauvegarder
	 * @param value le nouveau nom de classe à sauvegarder
	 */
	public static void saveClassChange(String key, String value) {
		classesMap.put(key, value);
	}
	
	/**
	 * Teste la présence d'une sauvegarde pour un ancien nom de classe
	 * @param key le nom de classe à tester
	 * @return le resultat du test
	 */
	public static boolean containsClass(String key) {
		return classesMap.containsKey(key);
	}
	
	/**
	 * Teste la présence d'une sauvegarde pour un nouveau nom de classe.
	 * @param value le nom de classe à tester
	 * @return le resultat du test
	 */
	public static boolean alreadyGenerateforClass(String value) {
		return classesMap.containsValue(value);
	}
	
	/**
	 * Retourne le nouveau nom associé à l'ancien passé en paramètre.
	 * @param oldname l'ancien nom de classe
	 * @return le nouveau nom de classe
	 */
	public static String getNewClassName(String oldname) {
		return classesMap.get(oldname);
	}
	

}
