package com.tibo.processors;

import java.nio.charset.StandardCharsets;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtLiteral;


import org.apache.commons.codec.binary.Base64;


/**
 * Processor pour encoder les String en base64.
 * @author thibaud
 *
 */
public class StringEncoderProcessor extends AbstractProcessor<CtLiteral> {

	@Override
	public void process(CtLiteral element) {
		
		// On va changer les String et les mettre sous la forme base64 pour ralentir la lecture 
		if (element.getType().getSimpleName().equals("String")) {
			
			String value = element.getValue().toString();
			
			byte[] value_byte = Base64.encodeBase64(value.getBytes());
			String value_b64 = new String(value_byte, StandardCharsets.UTF_8);
						
			CtCodeSnippetExpression snippet = getFactory().Core().createCodeSnippetExpression();
			snippet.setValue("new String(org.apache.commons.codec.binary.Base64.decodeBase64((\""+value_b64+"\").getBytes()), java.nio.charset.StandardCharsets.UTF_8)");

			element.replace(snippet);
			
		}
					
	}

}
