
package com.tibo.processors;


import java.util.List;

import com.tibo.processors.util.ListPoet;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtVariable;



/**
 * Processor permettant de changer les commentaires.
 * @author thibault
 *
 */
public class DocChangeProcessor extends AbstractProcessor<CtElement> {


	private List<String> poetry = getAllSentence();
	private int line =0;


	@Override
	public void process(CtElement element) {
	
		getEnvironment().setGenerateJavadoc(true);
		getEnvironment().setAutoImports(true);
	
		String result ="";
		
		if (element instanceof CtClass || element instanceof CtMethod
				|| element instanceof CtVariable) {
			int length = 2 + (int)(Math.random() * ((5 - 3) + 1));
			for(int i =0; i < length ; i++){
				if(line > poetry.size()-1){
					line =0;
				}
				result += getSentence(line)+"\n";
				
				line ++;
			}
			element.setDocComment(result);
		}
	}


	private String getSentence(int line) {

		String comment = poetry.get(line).trim();
		return comment;
	}


	private List<String> getAllSentence() {
		return  new ListPoet();
	}


}


