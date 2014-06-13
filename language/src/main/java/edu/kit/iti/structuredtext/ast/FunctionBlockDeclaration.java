package edu.kit.iti.structuredtext.ast;

/**
 * Created by weigl on 13.06.14.
 */
public class FunctionBlockDeclaration extends TopLevelScopeElement {
    private StatementList functionBody;
    private String functionBlockName;

    public String getFunctionBlockName() {
        return functionBlockName;
    }


    public void setFunctionBlockName(String functionBlockName) {
        this.functionBlockName = functionBlockName;
    }

    public void setFunctionBody(StatementList functionBody) {
        this.functionBody = functionBody;
    }

    public StatementList getFunctionBody() {
        return functionBody;
    }
}
