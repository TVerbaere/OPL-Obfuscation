package com.tibo.processors;


import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.Block;

import spoon.Launcher;
import spoon.compiler.SpoonCompiler;
import spoon.compiler.SpoonResourceHelper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.filter.NameFilter;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.code.CtCodeSnippetStatementImpl;
import spoon.template.Substitution;

public class IfChangeProcessor extends AbstractProcessor<CtIf> {

	private List<CtIf> ifs;
	private CtType<?> type;
	@Override
	public void process(CtIf element) {		
		// On récupère le body actuel :

		CtBlock fi; 




		this.init();



		int addLoop =  1+ (int)(Math.random() * ((4 - 1)));


		CtBlock dummyIf =null;
		switch(addLoop){
		case  1 : dummyIf=getDummyIf(); break;
		case  2 : dummyIf=getDummyStringIf(); break;
		case  3 : dummyIf=getDummyAndIf(); break;
		case  4 : dummyIf=getDummyOrIf(); break;
		default:
		}

		fi = (CtBlock) element.getThenStatement();

		fi = (CtBlock) element.getThenStatement();
		element.setThenStatement(dummyIf);
		((CtIf) element.getThenStatement().getElements(new TypeFilter(CtIf.class)).get(0)).setThenStatement(fi);


		
		CtExpression cdt = element.getCondition();



		CtBinaryOperator condition = getFactory().Core().createBinaryOperator();

		condition.setKind(BinaryOperatorKind.AND);

		condition.setLeftHandOperand(cdt);
		condition.setRightHandOperand(((CtIf)getDummyAndIf().getElements(new TypeFilter(CtIf.class)).get(0)).getCondition());


		element.setCondition(condition);

	}

	public void init(){
		type = null;

		try {
			type = getLoops();
		} catch (Exception e) {
			System.out.println("Fail to get dummy loop : "+e);
		}

		if(type != null){
			this.ifs= type.getElements(new TypeFilter<CtIf>(CtIf.class));
		}

	}

	public <T extends CtType<?>> T getLoops() throws Exception {
		SpoonCompiler comp = new Launcher().createCompiler();
		comp.addInputSources(SpoonResourceHelper.resources("../Processors/src/main/java/com/tibo/processors/util/UselessIfs.java"));
		comp.build();
		return comp.getFactory().Package().get("com.tibo.processors.util").getType("UselessIfs");
	}

	public CtBlock getDummyIf(){

		CtMethod<?> dummyIf = type.getElements(new NameFilter<CtMethod<?>>("UselessPositiveIf")).get(0);

		return dummyIf.getBody();
	}

	public CtBlock getDummyStringIf(){

		CtMethod<?> dummyIf = type.getElements(new NameFilter<CtMethod<?>>("UselessPositiveIfWithString")).get(0);

		return dummyIf.getBody();
	}



	public CtBlock getDummyAndIf(){
		CtMethod<?> dummyIf = type.getElements(new NameFilter<CtMethod<?>>("UselessPositiveAndIf")).get(0);

		return dummyIf.getBody();

	}


	public CtBlock getDummyOrIf(){
		CtMethod<?> dummyIf = type.getElements(new NameFilter<CtMethod<?>>("UselessPositiveOrIf")).get(0);

		return dummyIf.getBody();

	}




}
