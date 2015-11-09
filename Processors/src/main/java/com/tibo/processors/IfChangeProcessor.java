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



			int nbAddLoop =  1+ (int)(Math.random() * ((3 - 1)));

			nbAddLoop =1;

			for(int i = nbAddLoop;i > 0; i--){
				fi = (CtBlock) element.getThenStatement();

					fi = (CtBlock) element.getThenStatement();
				element.setThenStatement(getDummyStringIf());
				((CtIf) element.getThenStatement().getElements(new TypeFilter(CtIf.class)).get(0)).setThenStatement(fi);
			
				
				/**if(i==1){ ToDO
					
					
					CtBlock dummy =getDummyStringIf();
					((CtIf) dummy.getElements(new TypeFilter(CtIf.class)).get(0)).setThenStatement(element);
					
					 element.getParent(CtBlock.class).replace(dummy);;
					System.out.println(element);
					
					
				}**/
				
				
				


			}
			
			CtExpression cdt = element.getCondition();
			
			
			
			CtBinaryOperator condition = getFactory().Core().createBinaryOperator();
			
			condition.setKind(BinaryOperatorKind.AND);
			
			condition.setLeftHandOperand(cdt);
			condition.setRightHandOperand(((CtIf)getDummyIf().getElements(new TypeFilter(CtIf.class)).get(0)).getCondition());
			
			
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
		
		
		 
		
	}
