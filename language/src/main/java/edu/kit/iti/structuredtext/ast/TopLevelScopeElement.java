package edu.kit.iti.structuredtext.ast;

/**
 * Created by weigl on 13.06.14.
 */
public class TopLevelScopeElement extends TopLevelElement {
    private VariableScope scope = new VariableScope();


    public VariableScope getScope() {
        return scope;
    }

    public void setScope(VariableScope scope) {
        this.scope = scope;
    }
}
