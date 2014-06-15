package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;
import edu.kit.iti.structuredtext.datatypes.AnyInt;
import edu.kit.iti.structuredtext.datatypes.IECString;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 13.06.14.
 */
public class StringTypeDeclaration extends TypeDeclaration<ScalarValue<? extends IECString, String>> {
    private ScalarValue<? extends AnyInt, Long> size;

    public ScalarValue<? extends IECString, String> getInitializationValue() {
        return initializationValue;
    }

    public void setInitializationValue(ScalarValue<? extends IECString, String> initializationValue) {
        this.initializationValue = initializationValue;

    }

    public ScalarValue<? extends AnyInt, Long> getSize() {
        return size;
    }

    public void setSize(ScalarValue<? extends AnyInt, Long> size) {
        this.size = size;
    }


    @Override
    public <S> S visit(Visitor<S> visitor) {
        return visitor.visit(this);
    }
}
