package com.tibo.processors;

import java.util.List;
import java.util.Set;

import com.tibo.processors.util.SaveMap;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;


/**
 * Processor permettant de changer les noms des classes (suite : Refractoring des modifications).
 * @author thibaud
 *
 */
public class RefractorProcessor extends AbstractProcessor<CtClass> {

	@Override
	public void process(CtClass element) {
		
		// Refractoring pour une éventuelle super classe :
		try {
			String superClassName = element.getSuperclass().getSimpleName();
			if (SaveMap.containsClass(superClassName)) {
				element.getSuperclass().setSimpleName(SaveMap.getNewClassName(superClassName));
			}
		}
		catch (NullPointerException npe) {
			// rien
		}
		
		// Refractoring pour les éventuelles interfaces :
		Set<CtTypeReference<?>> superInterfaceNames = element.getSuperInterfaces();
		for (CtTypeReference<?> ref : superInterfaceNames) {
			String interfaceName = ref.getSimpleName();
			if (SaveMap.containsClass(interfaceName)) {
				ref.setSimpleName(SaveMap.getNewClassName(interfaceName));
			}
		}
		
		// Refractoring pour les appels des méthodes :
		Filter<CtInvocation> filter = new TypeFilter(CtInvocation.class);
		List<CtInvocation> list = element.getElements(filter);
		for (CtInvocation e : list) {
			String current_name = e.getExecutable().getDeclaringType().getSimpleName();
			if (SaveMap.containsClass(current_name)) {
				e.getExecutable().getDeclaringType().setSimpleName(SaveMap.getNewClassName(current_name));
			}
			current_name = e.getExecutable().getSimpleName();
			if (SaveMap.containsMethod(current_name))
				e.getExecutable().setSimpleName(SaveMap.getNewMethodName(current_name));
		}
		
		// Refractoring pour les appels des variables :
		Filter<CtVariableAccess> filter6 = new TypeFilter(CtVariableAccess.class);
		List<CtVariableAccess> list6 = element.getElements(filter6);
		for (CtVariableAccess e6 : list6) {
			String current_name = e6.getVariable().getSimpleName();
			if (SaveMap.containsVariable(current_name))
				e6.getVariable().setSimpleName(SaveMap.getNewVariableName(current_name));
		}
		
		// Refractoring pour les appels d'attributs :
		Filter<CtFieldAccess> filter3 = new TypeFilter(CtFieldAccess.class);
		List<CtFieldAccess> list3 = element.getElements(filter3);
		for (CtFieldAccess e3 : list3) {
			String current_name = e3.getVariable().getDeclaringType().getSimpleName();
			if (SaveMap.containsClass(current_name))
				e3.getVariable().getDeclaringType().setSimpleName(SaveMap.getNewClassName(current_name));
			current_name = e3.getVariable().getSimpleName();
			if (SaveMap.containsVariable(current_name))
				e3.getVariable().setSimpleName(SaveMap.getNewVariableName(current_name));
		}
		
		// Refractoring pour les appels des contructeurs :
		Filter<CtConstructorCall> filter4 = new TypeFilter(CtConstructorCall.class);
		List<CtConstructorCall> list4 = element.getElements(filter4);
		for (CtConstructorCall e4 : list4) {
			String current_name = e4.getType().getSimpleName();
			if (SaveMap.containsClass(current_name))
				e4.getType().setSimpleName(SaveMap.getNewClassName(current_name));
		}
		
		// Refractoring pour les types des variables :
		Filter<CtVariable> filter2 = new TypeFilter(CtVariable.class);
		List<CtVariable> list2 = element.getElements(filter2);
		for (CtVariable e2 : list2) {
			String current_name = e2.getReference().getType().getSimpleName();
			if (SaveMap.containsClass(current_name))
				e2.getReference().getType().setSimpleName(SaveMap.getNewClassName(current_name));
		}
				
		// Refractoring pour les signatures des méthodes.
		Filter<CtMethod> filter5 = new TypeFilter(CtMethod.class);
		List<CtMethod> list5 = element.getElements(filter5);
		for (CtMethod e5 : list5) {
			String current_name = e5.getType().getSimpleName();
			if (SaveMap.containsClass(current_name))
				e5.getType().setSimpleName(SaveMap.getNewClassName(current_name));
		}
					
	}

}
