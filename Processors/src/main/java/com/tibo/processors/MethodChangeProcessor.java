package com.tibo.processors;

import java.util.List;

import com.tibo.processors.util.AleaName;
import com.tibo.processors.util.SaveMap;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Processor permettant de changer les noms des variables des fonctions.
 * @author thibaudlt
 *
 */
public class MethodChangeProcessor extends AbstractProcessor<CtMethod> {

	@Override
	public void process(CtMethod element) {		
		// On récupère le nom actuel :
		String oldname = element.getSimpleName();
		String newname="";
		if(!oldname.equals("main")){
			
			if(!SaveMap.containsMethod(oldname)){	
				// On génere un nouveau nom :
				newname = AleaName.methodNameAlea();
				// On sauvegarde ce couple :
				SaveMap.saveMethodChange(oldname, newname);
			}else{

			
				// On recupère le nouveau nom :
				newname = SaveMap.getNewMethodName(oldname);
			
			}
			// On peut desormais changer le nom de methode:
			element.setSimpleName(newname);

		}else{
			
			newname ="main";
		}
		// On va maintenant changer tout les appels à ce paramètre :
		if (element.getParent(CtClass.class) != null) {
			// On créé un filtre sur les accès aux variables :
			Filter<CtInvocation> filter = new TypeFilter(CtInvocation.class);
			// On applique ce filtre sur la méthode qui a pour paramètre celui que nous avons changé :
			List<CtInvocation> list = element.getParent(CtClass.class).getElements(filter);

			// Quand on trouve l'ancien nom, on le remplace directement par le nouveau nom :
			for (CtInvocation e : list) {
				if (e.getExecutable().getSimpleName().equals(oldname))
					e.getExecutable().setSimpleName(newname);
			}

		}

	}

}
