package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.ast.*;
import edu.kit.iti.structuredtext.datatypes.AnyBit;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;
import edu.kit.iti.structuredtext.runtime.Function;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by weigl on 13.06.14.
 */
public class ExecuteAST {

	Scope scope = new Scope();


	@SuppressWarnings("unchecked")
	public Object visit(Object obj) {		
		
		if (obj instanceof ProgramDeclaration) {
			return visit((ProgramDeclaration) obj);
		}
		
		if (obj instanceof TypeDeclarations) {
			return visit((TypeDeclarations) obj);
		}
		
		if (obj instanceof TypeDeclarations) {
			return visit((TypeDeclarations) obj);		
		}
		
		if (obj instanceof EnumerationTypeDeclaration) {
			return visit((EnumerationTypeDeclaration) obj);		
		}

		if (obj instanceof ArrayTypeDeclaration) {
			return visit((ArrayTypeDeclaration) obj);		
		}

		if (obj instanceof StructureTypeDeclaration) {
			return visit((StructureTypeDeclaration) obj);		
		}

		if (obj instanceof SubRangeDataType) {
			return visit((SubRangeDataType) obj);		
		}

		if (obj instanceof SimpleTypeDeclaration) {
			return visit((SimpleTypeDeclaration) obj);		
		}
		
		if (obj instanceof StringTypeDeclaration) {
			return visit((StringTypeDeclaration) obj);		
		}

		if (obj instanceof StatementList) {
			return visit((StatementList) obj);		
		}
		
		if (obj instanceof IfStatement) {
			return visit((IfStatement) obj);		
		}
		
		if (obj instanceof FunctionBlockDeclaration) {
			return visit((FunctionBlockDeclaration) obj);
		}

		if (obj instanceof FunctionCallStatement) {
			return visit((FunctionCallStatement) obj);		
		}

		if (obj instanceof RepeatStatement) {
			return visit((RepeatStatement) obj);		
		}
		
		if (obj instanceof WhileStatement) {
			return visit((WhileStatement) obj);		
		}
		
		if (obj instanceof GuardedStatement) {
			return visit((GuardedStatement) obj);		
		}
		
		if (obj instanceof List<?>) {
			return visit((List<?>) obj);
		}
		
		System.err.println("No handler for class " + obj.getClass().getCanonicalName());
		
		return obj;
	}

	public Object visit(List<?> l) {
		for (Object t : l)
			visit(t);
		return null;
	}

	public Object visit(ProgramDeclaration pd) {
		System.out.println("Executing Program: " + pd.getProgramName());
		visit(pd.getProgramBody());
		return null;
	}

	public Object visit(FunctionBlockDeclaration fbd) 
	{
		scope.declareFunction(fbd);
		return null;
	}

	public Object visit(TypeDeclarations td) {
		for (TypeDeclaration d : td) {
			visit(d);
		}
		return null;
	}

	public Object visit(EnumerationTypeDeclaration etd) {
		return etd;
	}

	public Object visit(ArrayTypeDeclaration atd) {
		return atd;
	}

	public Object visit(StructureTypeDeclaration atd) {
		return atd;
	}

	public Object visit(SubRangeDataType atd) {
		return atd;
	}

	public Object visit(SimpleTypeDeclaration atd) {
		return atd;
	}

	public Object visit(StringTypeDeclaration atd) {
		return atd;
	}

	public Object visit(StatementList statements) {
		for (Statement stmt : statements) {
			visit(stmt);
		}
		return statements;
	}

	public Object visit(IfStatement ifstmt) {
		boolean exec = false;
		for (GuardedStatement guardedStatement : ifstmt
				.getConditionalBranches()) {
			exec = visit(guardedStatement);
			if (exec)
				break;
		}
		if (!exec)
			visit(ifstmt.getElseBranch());
		return exec;
	}

	public Object visit(FunctionCallStatement fcs) {
		System.out.println("Call Function: " + fcs);
		
		Function func = scope.getFunction(fcs.getFunctionBlockCall().getFunctionName());
		
		scope.push(); //new stack top
		
		for(String name : func.getParameterNames()) {
			scope.declareVariable(name);
		}
		
		/*for(Map.Entry<String, Expression> input : inputVariables.entrySet())
		{
			Expression value = input.getValue();
			if(value != null)
				//scope.declareVariable(input.getKey(), exprEval.eval(value));
                return null;
			else 
				scope.declareVariable(input.getKey());
		}
		
		func.execute(this);
		scope.pop();  //clear local variables
		*/
		return null;
	}

	public boolean visit(GuardedStatement gs) {
		Expression cond = gs.getCondition();

		/*ScalarValue<AnyBit.Bool, Boolean> bool = (ScalarValue<AnyBit.Bool, Boolean>) exprEval
				.eval(cond);

		if (bool.getValue()) {
			visit(gs.getStatements());
			return true;
		} else {
			return false;
		}*/
        return true
                ;
	}

}
