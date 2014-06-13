package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.functions.Operator;
import edu.kit.iti.structuredtext.runtime.Function;
import edu.kit.iti.structuredtext.runtime.VariableBinding;
import org.apache.commons.collections.map.MultiKeyMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weigl on 13.06.14.
 */
public class Scope {

    private static MultiKeyMap mkm;
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
        return variables.get(name);
    }

    public Any getDataType(String name) {
        return dataTypes.get(name);
    }

}
