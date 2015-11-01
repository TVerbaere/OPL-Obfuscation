package com.tibo.processors;

import java.util.List;

import com.tibo.processors.util.AleaName;
import com.tibo.processors.util.SaveMap;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Processor permettant de changer les noms des variables des fonctions.
 * @author thibaud
 *
 */
public class VariableChangeProcessor extends AbstractProcessor<CtVariable> {
			
	@Override
	public void process(CtVariable element) {		
		// On récupère le nom actuel :
		String oldname = element.getSimpleName();
		// On génere un nouveau nom :
		String newname = AleaName.variableNameAlea();
		// On sauvegarde ce couple :
		SaveMap.save(oldname, newname);
		
		// On peut desormais changer le nom du paramètre :
		element.setSimpleName(newname);
		
		// Si getParent declenche une exception alors c'est un attribut.
		// Sinon c'est soit un paramètre, soit une variable locale.
		try {
			// On va maintenant changer tout les appels à ce paramètre :
			if (element.getParent(CtExecutable.class).getBody() != null) {
				// On créé un filtre sur les accès aux variables :
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
		catch (NullPointerException npe) {
			// On créé un filtre sur les accès aux attributs :
			Filter<CtFieldAccess> filter = new TypeFilter(CtFieldAccess.class);
			// On applique ce filtre tout les éléments de la classe :
			List<CtFieldAccess> list = element.getParent(CtElement.class).getElements(filter);
			
			// Quand on trouve l'ancien nom, on le remplace directement par le nouveau nom :
			for (CtFieldAccess e : list) {
				if (e.getVariable().getSimpleName().equals(oldname))
					e.getVariable().setSimpleName(newname);
			}
		}
		
	}

}
