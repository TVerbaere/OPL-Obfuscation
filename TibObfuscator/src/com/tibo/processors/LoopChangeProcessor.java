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

		

		this.init();



		int addLoop =  1+ (int)(Math.random() * ((3 - 1)));


			switch(addLoop){
			case  1 : setDummyForI(element); break;
			case  2 : setDummyForJ(element); break;
			case  3 : setDummyWhile(element); break;
			default:
			}



		}


	



	private void setDummyWhile(CtLoop element) {
		
		// On récupère le body actuel :
		CtStatement body;
		
		body = element.getBody();
		element.setBody(getDummyWhile());

		CtStatement bodyLoop =(CtStatement) ((CtLoop) element.getBody().getElements(new TypeFilter(CtLoop.class)).get(0)).getBody();
		bodyLoop.insertBefore(body);

	}



	private void setDummyForJ(CtLoop element) {
		// On récupère le body actuel :
		CtStatement body;
				
				body = (CtStatement) element.getBody();
				element.setBody(getDummyForJ());

				((CtLoop) element.getBody().getElements(new TypeFilter(CtLoop.class)).get(0)).setBody(body);
		
	}



	private void setDummyForI(CtLoop element) {
		// On récupère le body actuel :
		CtStatement body;
		
		body = (CtStatement) element.getBody();
		element.setBody(getDummyForI());

		((CtLoop) element.getBody().getElements(new TypeFilter(CtLoop.class)).get(0)).setBody(body);
		

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
		comp.addInputSources(SpoonResourceHelper.resources("src/com/tibo/processors/util/UselessLoops.java"));
		comp.build();
		return comp.getFactory().Package().get("com.tibo.processors.util").getType("UselessLoops");
	}

	public CtBlock getDummyForI(){

		CtMethod<?> dummyFor = type.getElements(new NameFilter<CtMethod<?>>("UselessForI")).get(0);

		return dummyFor.getBody();
	}


	public CtBlock getDummyForJ(){

		CtMethod<?> dummyFor = type.getElements(new NameFilter<CtMethod<?>>("UselessForJ")).get(0);

		return dummyFor.getBody();
	}

	public CtBlock getDummyWhile(){

		CtMethod<?> dummyFor = type.getElements(new NameFilter<CtMethod<?>>("UselessWhile")).get(0);

		return dummyFor.getBody();
	}


}
