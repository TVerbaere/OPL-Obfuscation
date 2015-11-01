package com.tibo.processors;

import java.util.List;

import com.tibo.processors.util.AleaName;
import com.tibo.processors.util.SaveMap;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;


/**
 * Processor permettant de changer les noms des classes.
 * @author thibaud
 *
 */
public class ClassChangeProcessor extends AbstractProcessor<CtClass> {

	@Override
	public void process(CtClass element) {
		// On récupère le nom actuel :
		String oldname = element.getSimpleName();
		// On génere un nouveau nom :
		String newname = AleaName.classNameAlea();
		// On sauvegarde ce couple :
		SaveMap.saveClassChange(oldname, newname);
		
		element.setSimpleName(newname);
				
		Filter<CtInvocation> filter = new TypeFilter(CtInvocation.class);
		List<CtInvocation> list = element.getElements(filter);
		for (CtInvocation e : list) {
			if (e.getExecutable().getDeclaringType().getSimpleName().equals(oldname)) {
				e.getExecutable().getDeclaringType().setSimpleName(newname);
			}
		}
		
		Filter<CtVariable> filter2 = new TypeFilter(CtVariable.class);
		List<CtVariable> list2 = element.getElements(filter2);
		for (CtVariable e2 : list2) {
			if (e2.getReference().getType().getSimpleName().equals(oldname))
				e2.getReference().getType().setSimpleName(newname);
		}
		
		Filter<CtFieldAccess> filter3 = new TypeFilter(CtFieldAccess.class);
		List<CtFieldAccess> list3 = element.getElements(filter3);
		for (CtFieldAccess e3 : list3) {
			if (e3.getVariable().getDeclaringType().getSimpleName().equals(oldname))
				e3.getVariable().getDeclaringType().setSimpleName(newname);
		}
		
		Filter<CtConstructorCall> filter4 = new TypeFilter(CtConstructorCall.class);
		List<CtConstructorCall> list4 = element.getElements(filter4);
		for (CtConstructorCall e4 : list4) {
			if (e4.getType().getSimpleName().equals(oldname))
				e4.getType().setSimpleName(newname);
		}
					
	}

}
