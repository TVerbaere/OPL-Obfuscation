package com.tibo.processors;

import java.util.List;
import java.util.Set;

import com.tibo.processors.util.SaveMap;
import com.tibo.processors.util.UnicodeConverter;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtFieldAccess;
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
			String current_name=null;
			if(e.getExecutable() != null && e.getExecutable().getDeclaringType() !=null){
				current_name = e.getExecutable().getDeclaringType().getSimpleName();
			}
			if (current_name !=  null && SaveMap.containsClass(current_name)) {
				String unicode = new UnicodeConverter(SaveMap.getNewClassName(current_name)).proceed();
				e.getExecutable().getDeclaringType().setSimpleName(unicode);
			}
			current_name = e.getExecutable().getSimpleName();
			if ( current_name !=  null && SaveMap.containsMethod(current_name)) {
				String unicode = new UnicodeConverter(SaveMap.getNewMethodName(current_name)).proceed();
				e.getExecutable().setSimpleName(unicode);
			}
		}
				
		// Refractoring pour les appels d'attributs :
		Filter<CtFieldAccess> filter3 = new TypeFilter(CtFieldAccess.class);
		List<CtFieldAccess> list3 = element.getElements(filter3);
		for (CtFieldAccess e3 : list3) {
			String current_name=null;
			if(e3.getVariable() != null && e3.getVariable().getDeclaringType() !=null){
				current_name = e3.getVariable().getDeclaringType().getSimpleName();
			}
			if (current_name !=  null && SaveMap.containsClass(current_name)) {
				String unicode = new UnicodeConverter(SaveMap.getNewClassName(current_name)).proceed();
				e3.getVariable().getDeclaringType().setSimpleName(unicode);
			}
			current_name = e3.getVariable().getSimpleName();
			if (current_name !=  null && SaveMap.containsVariable(current_name)) {
				String unicode = new UnicodeConverter(SaveMap.getNewVariableName(current_name)).proceed();
				e3.getVariable().setSimpleName(unicode);
			}
		}
		
		// Refractoring pour les appels des contructeurs :
		Filter<CtConstructorCall> filter4 = new TypeFilter(CtConstructorCall.class);
		List<CtConstructorCall> list4 = element.getElements(filter4);
		for (CtConstructorCall e4 : list4) {
			String current_name = e4.getType().getSimpleName();
			if (current_name !=  null && SaveMap.containsClass(current_name)) {
				String unicode = new UnicodeConverter(SaveMap.getNewClassName(current_name)).proceed();
				e4.getType().setSimpleName(unicode);
			}
		}
		
		// Refractoring pour les types des variables :
		Filter<CtVariable> filter2 = new TypeFilter(CtVariable.class);
		List<CtVariable> list2 = element.getElements(filter2);
		for (CtVariable e2 : list2) {
			String current_name =null;
			if(e2.getReference() != null && e2.getReference().getType() != null){
			current_name = e2.getReference().getType().getSimpleName();
			}
			if (current_name !=  null && SaveMap.containsClass(current_name))
				e2.getReference().getType().setSimpleName(SaveMap.getNewClassName(current_name));
		}
				
		// Refractoring pour les signatures des méthodes.
		Filter<CtMethod> filter5 = new TypeFilter(CtMethod.class);
		List<CtMethod> list5 = element.getElements(filter5);
		for (CtMethod e5 : list5) {
			String current_name = e5.getType().getSimpleName();
			if (current_name !=  null && SaveMap.containsClass(current_name))
				e5.getType().setSimpleName(SaveMap.getNewClassName(current_name));
		}
					
	}

}
