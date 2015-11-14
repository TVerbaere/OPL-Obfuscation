package com.tibo.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtAnnotation;

/**
 * 
 * @author thibaud
 *
 */
public class RemoveAnnotationProcessor extends AbstractProcessor<CtAnnotation> {

	@Override
	public void process(CtAnnotation element) {
		if (element.getType().getPackage().equals("java.lang"))
			element.replace(null);	
	}

}
