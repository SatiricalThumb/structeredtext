package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.ast.FunctionBlockDeclaration;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;
import edu.kit.iti.structuredtext.functions.Operator;
import edu.kit.iti.structuredtext.runtime.Function;
import edu.kit.iti.structuredtext.runtime.UserFunction;
import edu.kit.iti.structuredtext.runtime.VariableBinding;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weigl on 13.06.14.
 */
public class Scope {

    private ChainMap<String, VariableBinding> variables = new ChainMap<>();
    private ChainMap<String, Function> functions = new ChainMap<>();
    private ChainMap<String, Any> dataTypes = new ChainMap<>();


    public Scope(Scope scope) {
        variables.push( scope.variables );
        functions.push(scope.functions);
        dataTypes.push( scope.dataTypes );
    }

    public Scope() {
        push();
    }



    public void push() {
        variables.push();
        functions.push();
        dataTypes.push();
    }

    public void pop() {
        variables.pop();
        functions.pop();
        dataTypes.pop();
    }

    public Function getFunction(String name) {
        return functions.get(name);
    }

    public VariableBinding getVariable(String name){
        //return variables.get(name);
    	return new VariableBinding();
    }

    public Any getDataType(String name) {
        return dataTypes.get(name);
    }

	public void declareFunction(FunctionBlockDeclaration fbd) 
	{
		System.out.println("Decleare Function: " + fbd.getFunctionBlockName());
		Function func = new UserFunction(fbd);
		functions.put(func.getFunctionName(), func);
	}

	public void declareVariable(String name) {
		variables.put(name, new VariableBinding(name));
		
	}

	public void declareVariable(String key, ScalarValue<?, ?> eval) {
		// TODO Auto-generated method stub
		
	}

}
