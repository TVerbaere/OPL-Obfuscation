package com.tibo.processors.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant une sauvegarde des couples (anciens noms - nouveaux noms).
 * @author thibaud
 *
 */
public class SaveMap {
	
	private static Map<String, String> map = new HashMap<String, String>();
	
	/**
	 * Permet de sauvegarder un couple.
	 * @param key l'ancien nom à sauvegarder
	 * @param value le nouveau nom à sauvegarder
	 */
	public static void save(String key, String value) {
		map.put(key, value);
	}
	
	/**
	 * Teste la présence d'une clé.
	 * @param key la clé a tester (autrement dit, l'ancien nom a tester)
	 * @return
	 */
	public static boolean contains(String key) {
		return map.containsKey(key);
	}

}
