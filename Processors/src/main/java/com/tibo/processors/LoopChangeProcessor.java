package com.tibo.processors;


import java.util.List;

import spoon.Launcher;
import spoon.compiler.SpoonCompiler;
import spoon.compiler.SpoonResourceHelper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.factory.FactoryImpl;
import spoon.reflect.visitor.filter.NameFilter;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.StandardEnvironment;
import spoon.support.reflect.code.CtCodeSnippetStatementImpl;

public class LoopChangeProcessor extends AbstractProcessor<CtLoop> {


	private List<CtFor> fors;
	private CtType<?> type;
	
	@Override
	public void process(CtLoop element) {		
		// On récupère le body actuel :

		CtBlock body;

		this.init();



		int nbAddLoop =  1+ (int)(Math.random() * ((3 - 1)));

		nbAddLoop =1;

		for(int i = nbAddLoop;i > 0; i--){
			body = (CtBlock) element.getBody();

		
			element.setBody(getDummyFor());
	
			((CtLoop) element.getBody().getElements(new TypeFilter(CtLoop.class)).get(0)).setBody(body);
			


		}
		

	}

	public void init(){
		type = null;

		try {
			type = getLoops();
		} catch (Exception e) {
			System.out.println("Fail to get dummy loop : "+e);
		}

		if(type != null){
			this.fors= type.getElements(new TypeFilter<CtFor>(CtFor.class));
		}

	}

	public <T extends CtType<?>> T getLoops() throws Exception {
		SpoonCompiler comp = new Launcher().createCompiler();
		comp.addInputSources(SpoonResourceHelper.resources("../Processors/src/main/java/com/tibo/processors/util/UselessLoops.java"));
		comp.build();
		return comp.getFactory().Package().get("com.tibo.processors.util").getType("UselessLoops");
	}

	public CtBlock getDummyFor(){

		CtMethod<?> dummyFor = type.getElements(new NameFilter<CtMethod<?>>("UselessFor")).get(0);
	
		return dummyFor.getBody();
	}

	


}
