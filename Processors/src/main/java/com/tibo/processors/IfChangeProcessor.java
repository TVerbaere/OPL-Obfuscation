package com.tibo.processors;


import org.eclipse.jdt.internal.compiler.ast.Block;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.support.reflect.code.CtCodeSnippetStatementImpl;
import spoon.template.Substitution;

public class IfChangeProcessor extends AbstractProcessor<CtIf> {

	@Override
	public void process(CtIf element) {		
		// On récupère le body actuel :

		CtBlock fi= (CtBlock) element.getParent();

		int nbAddIf =  1+ (int)(Math.random() * ((3 - 1)));


		// we declare a new snippet of code to be inserted.
		CtCodeSnippetStatement snippet=new CtCodeSnippetStatementImpl();
		CtCodeSnippetStatement snippetEnd=new CtCodeSnippetStatementImpl();
		for(int i = nbAddIf;i > 0; i--){
			snippet=new CtCodeSnippetStatementImpl();
			snippetEnd=new CtCodeSnippetStatementImpl();



			// this snippet contains a new for
			String begin ="String spoonString"+i+"= \"toto\"; if(spoonString"+i+".equals(\"toto\")){spoonString"+i+"+=\"ti\"";

			snippet.setValue(begin);

			fi.insertBegin(snippet);

			// this snippet contains a new for
			String end ="}spoonString"+i+"+=\"ti\"";

			snippetEnd.setValue(end);

			fi.insertEnd(snippetEnd);

		}
		 
		
	}
	 



}
