package edu.kit.iti.structuredtext.runtime;

import edu.kit.iti.structuredtext.ASTVisitor;
import edu.kit.iti.structuredtext.ast.FunctionBlockDeclaration;
import edu.kit.iti.structuredtext.datatypes.Any;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by weigl on 13.06.14.
 */
public abstract class Function
{
    public List<String> parameterNames = new LinkedList<>();
    public HashMap<String, Any> dataTypes = new HashMap<>();

    public Function(String name) {
        setFunctionName(name);
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    private String functionName;

    public FunctionScope createCall() {
        FunctionScope functionScope = new FunctionScope();
        // TODO add default
        return functionScope;
    }


    public abstract Object invoke(FunctionScope scope, ASTVisitor executer);

}
