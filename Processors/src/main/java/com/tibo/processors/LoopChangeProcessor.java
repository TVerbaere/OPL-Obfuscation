package com.tibo.processors;


import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;
import spoon.support.reflect.code.CtCodeSnippetStatementImpl;

public class LoopChangeProcessor extends AbstractProcessor<CtLoop> {

	@Override
	public void process(CtLoop element) {		
		// On récupère le body actuel :
		
		CtBlock body = (CtBlock) element.getBody();
		
		int nbAddLoop =  1+ (int)(Math.random() * ((3 - 1)));
		
		
		// we declare a new snippet of code to be inserted.
		CtCodeSnippetStatement snippet=getFactory().Core().createCodeSnippetStatement();
		String begin =body.toString();
		for(int i = nbAddLoop;i > 0; i--){
			snippet=getFactory().Core().createCodeSnippetStatement();
			
			

			// this snippet contains a new for
			begin ="{ String nombreSpoon"+i+" = \"1\";int resSpoon"+i+" = Integer.parseInt(nombreSpoon"+i+");for(int spoon"+i+" = resSpoon"+i+"; spoon"+i+" > 0; spoon"+i+"--)"+begin+" } ";
			
			
			
		
		
			
		
			
		}
		
		begin += "int spoonEnd = 1";

		snippet.setValue(begin);
		body.replace(snippet);

		
		


		


		

		
	}
	
	
	

}
