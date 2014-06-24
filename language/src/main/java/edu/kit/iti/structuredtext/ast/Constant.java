package edu.kit.iti.structuredtext.ast;


import edu.kit.iti.structuredtext.Visitable;
import edu.kit.iti.structuredtext.Visitor;

/**
 * Created by weigla on 09.06.2014.
 */
public class Constant implements Visitable {
    private Literal literal;

    @Override
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
