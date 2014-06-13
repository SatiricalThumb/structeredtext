package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.datatypes.AnyInt;
import edu.kit.iti.structuredtext.datatypes.EnumerateType;
import edu.kit.iti.structuredtext.datatypes.IECString;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by weigl on 13.06.14.
 */
public class VariableScope {
    private Map<String, Variable> variableMap = new HashMap<>();
    private Stack<Integer> stack = new Stack<>();


    public Map<String, Variable> getVariableMap() {
        return variableMap;
    }

    public void setVariableMap(Map<String, Variable> variableMap) {
        this.variableMap = variableMap;
    }

    public void push(int i) {
        stack.push(i);
    }

    public void clear() {
        stack.clear();
    }

    public void clear(int i){
        clear();push(i);
    }

    public void mix(int i){
        push(stack.pop() | i);
    }

    public void addBoolEdge(List<String> ast, boolean b) {
        //TODO
    }


    private void add(Variable var) {
        variableMap.put(var.getName(), var);
    }

    public void create(List<String> names, TypeDeclaration<?> init)
    {
        for(String name : names) {
            Variable var = new Variable(name, stack.peek(), init);
            add(var);
        }
    }

    public void createFBName(List<String> names, String s, StructureInitialization init)
    {
        throw new AssertionError("i do not know what this construct means");
        /*for(String name : names) {
            Variable var = new Variable(name, stack.peek(), init);
            var.setDataType(init.getStructureName());
            add(var);
        }*/
    }



    public void create(List<String> names, StructureInitialization init) {
        for(String name : names) {
            Variable var = new Variable(name, stack.peek(), init);
            var.setDataType(init.getStructureName());
            add(var);
        }
    }

    public void create(List<String> names, ScalarValue<?, ?> init) {
        for(String name : names) {
            Variable var = new Variable(name, stack.peek(), init);
            add(var);
        }
    }


    public void pop() {
        stack.pop();
    }


    public void create(List<String> ast, ScalarValue<? extends AnyInt, Integer> length, ScalarValue<? extends IECString, String> def) {
        for(String name : ast) {
            Variable var = new StringVariable(name, stack.peek(), length, def);
            add(var);
        }
    }


    public void create(List<String> ast, String baseType)
    {
        for(String name : ast) {
            Variable var = new Variable(name, stack.peek());
            var.setDataType(baseType);
            add(var);
        }
    }
}