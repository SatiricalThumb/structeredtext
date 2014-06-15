package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.StructuredTextPrinter;
import edu.kit.iti.structuredtext.Visitor;

import java.util.ArrayList;

/**
 * Created by weigla on 09.06.2014.
 */
public class StatementList extends ArrayList<Statement> {
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }

}
