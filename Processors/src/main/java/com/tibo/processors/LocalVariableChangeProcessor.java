package com.tibo.processors;

import java.util.List;

import com.tibo.processors.util.AleaName;
import com.tibo.processors.util.SaveMap;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Processor permettant de changer les noms des variables locales contenues dans les fonctions.
 * @author thibaud
 *
 */
public class LocalVariableChangeProcessor extends AbstractProcessor<CtLocalVariable> {
		
	@Override
	public void process(CtLocalVariable element) {
		
		// On récupère le nom actuel :
		String oldname = element.getSimpleName();
		// On génere un nouveau nom :
		String newname = AleaName.variableNameAlea();
		// On sauvegarde ce couple :
		SaveMap.save(oldname, newname);
		
		// On peut desormais changer le nom du paramètre :
		element.setSimpleName(newname);
		
		// On va maintenant changer tout les appels à ce paramètre :
		if (element.getParent(CtExecutable.class).getBody() != null) {
			// On créé un filtre sur les variables :
			Filter<CtVariableAccess> filter = new TypeFilter(CtVariableAccess.class);
			// On applique ce filtre sur la méthode qui a pour paramètre celui que nous avons changé :
			List<CtVariableAccess> list = element.getParent(CtExecutable.class).getBody().getElements(filter);
			
			// Quand on trouve l'ancien nom, on le remplace directement par le nouveau nom :
			for (CtVariableAccess e : list) {
				if (e.getVariable().getSimpleName().equals(oldname))
					e.getVariable().setSimpleName(newname);
			}
			
		}
		
	}

}
