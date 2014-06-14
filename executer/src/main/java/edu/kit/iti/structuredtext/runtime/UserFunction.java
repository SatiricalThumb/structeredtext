package edu.kit.iti.structuredtext.runtime;

import java.util.Map;

import edu.kit.iti.structuredtext.ExecuteAST;
import edu.kit.iti.structuredtext.ast.FunctionBlockDeclaration;
import edu.kit.iti.structuredtext.ast.StatementList;
import edu.kit.iti.structuredtext.ast.VariableScope;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;


public class UserFunction extends Function {
	private StatementList statements;
	private VariableScope scope;

	public UserFunction(FunctionBlockDeclaration fbd) {
		super(fbd.getFunctionBlockName());
		scope = fbd.getScope();
		statements = fbd.getFunctionBody();
	}

	@Override
	public <S> FunctionScope createCall(
			Map<String, ScalarValue<? extends Any, S>> arguments) {
		//TODO define variables, retrieve types
		FunctionScope functionScope = new FunctionScope();
		scope.getVariableMap();
		return functionScope ;
	}

	@Override
	public Object execute(ExecuteAST executeAST) {
		return executeAST.visit(statements);
	}

}
