package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitable;
import edu.kit.iti.structuredtext.Visitor;
import edu.kit.iti.structuredtext.datatypes.AnyInt;
import edu.kit.iti.structuredtext.datatypes.IECString;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

import java.util.*;

/**
 * Created by weigl on 13.06.14.
 */
public class VariableScope implements Visitable{
    private Map<String, VariableDeclaration> variableMap = new HashMap<>();
    private Stack<Integer> stack = new Stack<>();


    public Map<String, VariableDeclaration> getVariableMap() {
        return variableMap;
    }

    public void setVariableMap(Map<String, VariableDeclaration> variableMap) {
        this.variableMap = variableMap;
    }

    public void push(int i) {
        stack.push(i);
    }

    public void clear() {
        stack.clear();
    }

    public void clear(int i) {
        clear();
        push(i);
    }

    public void mix(int i) {
        push(stack.pop() | i);
    }

    public void addBoolEdge(List<String> ast, boolean b) {
        //TODO
    }


    public void add(VariableDeclaration var) {
        variableMap.put(var.getName(), var);
    }

    public void create(List<String> names, TypeDeclaration<?> init) {
        for (String name : names) {
            VariableDeclaration var = new VariableDeclaration(name, peek(), init);
            add(var);
        }
    }

    public void createFBName(List<String> names, String s, StructureInitialization init) {
        throw new AssertionError("i do not know what this construct means");
        /*for(String name : names) {
            Variable var = new Variable(name, stack.peek(), init);
            var.setDataType(init.getStructureName());
            add(var);
        }*/
    }


    public void create(List<String> names, StructureInitialization init) {
        for (String name : names) {
            VariableDeclaration var = new VariableDeclaration(name, peek(), init);
            var.setDataType(init.getStructureName());
            add(var);
        }
    }

    public void create(List<String> names, ScalarValue<?, ?> init) {
        for (String name : names) {
            VariableDeclaration var = new VariableDeclaration(name, peek(), init);
            add(var);
        }
    }


    public void pop() {
        stack.pop();
    }


    public void create(List<String> ast, ScalarValue<? extends AnyInt, Long> length, ScalarValue<? extends IECString, String> def) {
        for (String name : ast) {
            VariableDeclaration var = new StringVariable(name, peek(), length, def);
            add(var);
        }
    }

    public int peek() {
        try {
            return stack.peek();
        } catch (EmptyStackException e) {
            return 0;
        }
    }


    public void create(List<String> ast, String baseType) {
        for (String name : ast) {
            VariableDeclaration var = new VariableDeclaration(name, peek());
            var.setDataType(baseType);
            add(var);
        }
    }

    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public void create(String type, String... variables)
    {
        create(Arrays.asList(variables), type);
    }
}