package com.tibo.processors;

import com.tibo.processors.util.AleaName;
import com.tibo.processors.util.SaveMap;
import com.tibo.processors.util.UnicodeConverter;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;

/**
 * Processor permettant de changer les noms des fonctions.
 * @author thibault
 *
 */
public class MethodChangeProcessor extends AbstractProcessor<CtMethod> {

	@Override
	public void process(CtMethod element) {
		
		// Encodage de la signature de la méthode :
		String returntype = element.getType().getSimpleName();
		if (element.getType().isPrimitive())
			element.getType().setSimpleName(new UnicodeConverter(returntype).proceed());
		
		// On récupère le nom actuel :
		String oldname = element.getSimpleName();
		String newname= "";
		
		if(!oldname.equals("main")){
			
			if(!SaveMap.containsMethod(oldname)){	
				// On génere un nouveau nom :
				newname = AleaName.methodNameAlea();
				// On sauvegarde ce couple :
				SaveMap.saveMethodChange(oldname, newname);
			}else{
				
				newname = SaveMap.getNewMethodName(oldname);
			
			}
			// On peut desormais changer le nom de methode:
			element.setSimpleName(newname);

		}else{
			// Touche à rien Spoon !! c'est le main voyons !
			
		}
		
		// Pas d'autres modifications, elles se feront dans le RefractorProcessor...

	}

}
