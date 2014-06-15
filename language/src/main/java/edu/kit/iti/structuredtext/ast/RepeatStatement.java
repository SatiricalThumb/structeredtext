package edu.kit.iti.structuredtext.ast;


import edu.kit.iti.structuredtext.Visitor;

public class RepeatStatement extends WhileStatement {

    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
