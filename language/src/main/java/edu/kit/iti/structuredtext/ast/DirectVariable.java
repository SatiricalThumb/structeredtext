package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;

/**
 * Created by weigl on 11.06.14.
 */
public class DirectVariable extends Reference {
    public DirectVariable(String s) {

    }

    public <T> T visit(Visitor<T> visitor)
    {
        return visitor.visit(this);
    }
}
