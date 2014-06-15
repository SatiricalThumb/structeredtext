package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;

/**
 * Created by weigl on 11.06.14.
 */
public class FunctionCallStatement extends Statement {
    private FunctionCall functionBlockCall;

    public FunctionCallStatement(FunctionCall fc) {
        this.functionBlockCall = fc;
    }

    public FunctionCall getFunctionBlockCall() {
        return functionBlockCall;
    }

    public void setFunctionBlockCall(FunctionCall functionBlockCall) {
        this.functionBlockCall = functionBlockCall;
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
