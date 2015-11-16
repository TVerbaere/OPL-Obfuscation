package com.tibo.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtAssert;

/**
 * 
 * @author thibaud
 *
 */
public class RemoveAssertProcessor extends AbstractProcessor<CtAssert> {

	@Override
	public void process(CtAssert element) {
		element.replace(null);
		
	}

}
