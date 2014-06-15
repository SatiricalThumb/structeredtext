package edu.kit.iti.structuredtext.ast;


import edu.kit.iti.structuredtext.Visitor;

/**
 * Created by weigla on 09.06.2014.
 */
public class Constant {
    private Literal literal;
    public void visit(Visitor visitor) {
        visitor.visit(this);
    }
}
