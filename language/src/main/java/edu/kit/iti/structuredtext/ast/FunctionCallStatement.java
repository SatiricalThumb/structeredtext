package edu.kit.iti.structuredtext.ast;

/**
 * Created by weigl on 11.06.14.
 */
public class FunctionCallStatement extends Statement {
    private FunctionCall functionBlockCall;

    public FunctionCallStatement(FunctionCall fc) {
        this.functionBlockCall = fc;
    }

    public void setFunctionBlockCall(FunctionCall functionBlockCall) {
        this.functionBlockCall = functionBlockCall;
    }

    public FunctionCall getFunctionBlockCall() {
        return functionBlockCall;
    }
}
