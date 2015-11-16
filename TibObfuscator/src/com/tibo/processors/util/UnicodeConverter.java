package com.tibo.processors.util;

/**
 * Classe permettant la convertion String à unicode.
 * @author thibaud
 *
 */
public class UnicodeConverter {

	private String text2convert;
	
	public UnicodeConverter(String toconvert) {
		text2convert = toconvert;
	}
	
	private String toUnicode(char ch) {
		return String.format("\\u%04x", (int) ch);
	}
	
	public String proceed() {
		String result = "";
		for (int i=0; i < text2convert.length(); i++) {
			result += this.toUnicode(text2convert.charAt(i));
		}
		
		return result;
	}
}
