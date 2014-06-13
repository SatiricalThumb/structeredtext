package edu.kit.iti.structuredtext.ast;

/**
 * Created by weigl on 13.06.14.
 */
public class FunctionDeclaration extends TopLevelScopeElement {
    private String functionName;
    private String returnType;

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }
}
