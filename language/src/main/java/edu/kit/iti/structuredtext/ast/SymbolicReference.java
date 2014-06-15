package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;

/**
 * Created by weigl on 11.06.14.
 */
public class SymbolicReference extends Reference {
    private String identifier;
    private ExpressionList subscripts;
    private Reference sub;


    public SymbolicReference(String s, Reference sub) {
        this.sub = sub;
        identifier = s;
    }

    public void addSubscript(Expression ast) {
        subscripts.add(ast);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public ExpressionList getSubscripts() {
        return subscripts;
    }

    public void setSubscripts(ExpressionList subscripts) {
        this.subscripts = subscripts;
    }

    public Reference getSub() {
        return sub;
    }

    public void setSub(Reference sub) {
        this.sub = sub;
    }


    public <T> T visit(Visitor<T> visitor)
    {
        return visitor.visit(this);
    }
}
