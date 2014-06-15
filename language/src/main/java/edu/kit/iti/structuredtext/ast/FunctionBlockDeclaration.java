package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;

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

    public StatementList getFunctionBody() {
        return functionBody;
    }

    public void setFunctionBody(StatementList functionBody) {
        this.functionBody = functionBody;
    }


    @Override
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
