package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;

/**
 * Created by weigl on 13.06.14.
 */
public abstract class TopLevelScopeElement extends TopLevelElement {
    private VariableScope scope = new VariableScope();

    public VariableScope getScope() {
        return scope;
    }

    public void setScope(VariableScope scope) {
        this.scope = scope;
    }
}
