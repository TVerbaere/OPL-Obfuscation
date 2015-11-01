package com.tibo.processors;

import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;


/**
 * Processor permettant de changer les noms des variables des fonctions.
 * @author thibaud
 *
 */
public class TestProcessor extends AbstractProcessor<CtClass> {
			
	@Override
	public void process(CtClass element) {
		if (element.getSimpleName().equals("App")) {

		element.setSimpleName("Pomme");
				
		Filter<CtInvocation> filter = new TypeFilter(CtInvocation.class);
		List<CtInvocation> list = element.getElements(filter);
		for (CtInvocation e : list) {
			if (e.getExecutable().getDeclaringType().getSimpleName().equals("App")) {
				e.getExecutable().getDeclaringType().setSimpleName("Pomme");
			}
		}
		
		Filter<CtVariable> filter2 = new TypeFilter(CtVariable.class);
		List<CtVariable> list2 = element.getElements(filter2);
		for (CtVariable e2 : list2) {
			if (e2.getReference().getType().getSimpleName().equals("App"))
				e2.getReference().getType().setSimpleName("Pomme");
		}
		
		Filter<CtFieldAccess> filter3 = new TypeFilter(CtFieldAccess.class);
		List<CtFieldAccess> list3 = element.getElements(filter3);
		for (CtFieldAccess e3 : list3) {
			if (e3.getVariable().getDeclaringType().getSimpleName().equals("App"))
				e3.getVariable().getDeclaringType().setSimpleName("Pomme");
		}
		
		Filter<CtConstructorCall> filter4 = new TypeFilter(CtConstructorCall.class);
		List<CtConstructorCall> list4 = element.getElements(filter4);
		for (CtConstructorCall e4 : list4) {
			if (e4.getType().getSimpleName().equals("App"))
				e4.getType().setSimpleName("Pomme");
		}
		
		}
			
	}

}
