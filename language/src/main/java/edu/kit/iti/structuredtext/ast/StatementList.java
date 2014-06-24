package edu.kit.iti.structuredtext.ast;

import edu.emory.mathcs.backport.java.util.Arrays;
import edu.kit.iti.structuredtext.StructuredTextPrinter;
import edu.kit.iti.structuredtext.Visitable;
import edu.kit.iti.structuredtext.Visitor;

import java.util.ArrayList;

/**
 * Created by weigla on 09.06.2014.
 */
public class StatementList extends ArrayList<Statement> implements Visitable {
    public StatementList() {
    }

    public StatementList(Statement... then) {
        super(Arrays.asList(then));
    }

    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }

}
