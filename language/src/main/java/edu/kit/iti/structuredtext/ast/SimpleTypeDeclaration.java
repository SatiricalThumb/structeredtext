package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 13.06.14.
 */
public class SimpleTypeDeclaration extends TypeDeclaration<ScalarValue<?, ?>> {
    @Override
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
