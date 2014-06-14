package edu.kit.iti.structuredtext.runtime;

import edu.kit.iti.structuredtext.ExecuteAST;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by weigl on 13.06.14.
 */
public abstract class Function {
	private String functionName;
	private List<String> parameterNames = new LinkedList<>();
	private HashMap<String, Any> dataTypes = new HashMap<>();
	private HashMap<String, Object> init = new HashMap<>();

	public Function(String name) {
		setFunctionName(name);
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public List<String> getParameterNames() {
		return parameterNames;
	}

	public void setParameterNames(List<String> parameterNames) {
		this.parameterNames = parameterNames;
	}

	public HashMap<String, Any> getDataTypes() {
		return dataTypes;
	}

	public void setDataTypes(HashMap<String, Any> dataTypes) {
		this.dataTypes = dataTypes;
	}

	public HashMap<String, Object> getInit() {
		return init;
	}

	public void setInit(HashMap<String, Object> init) {
		this.init = init;
	}

	public abstract <S> FunctionScope createCall(
			Map<String, ScalarValue<? extends Any, S>> arguments);

	public abstract Object execute(ExecuteAST executeAST);
}
