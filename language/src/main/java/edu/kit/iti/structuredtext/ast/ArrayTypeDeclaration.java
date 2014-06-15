package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;

/**
 * Created by weigl on 13.06.14.
 */
public class ArrayTypeDeclaration extends TypeDeclaration<ArrayInitialization> {


    @Override
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public void addSubRange(Range ast) {

    }
}
