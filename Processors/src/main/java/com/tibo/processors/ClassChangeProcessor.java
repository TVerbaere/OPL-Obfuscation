package com.tibo.processors;

import com.tibo.processors.util.AleaName;
import com.tibo.processors.util.SaveMap;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtType;


/**
 * Processor permettant de changer les noms des classes.
 * @author thibaud
 *
 */
public class ClassChangeProcessor extends AbstractProcessor<CtType> {

	@Override
	public void process(CtType element) {
		
		// On récupère le nom actuel :
		String oldname = element.getSimpleName();

		// On génere un nouveau nom :
		String newname = AleaName.classNameAlea();
		// On sauvegarde ce couple :
		SaveMap.saveClassChange(oldname, newname);
		
		// On change le nom :
		element.setSimpleName(newname);
		
		// Pas d'autres modifications, elles se feront dans le RefractorProcessor...
					
	}

}
