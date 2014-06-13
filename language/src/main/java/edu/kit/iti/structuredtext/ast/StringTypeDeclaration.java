package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.ValueFactory;
import edu.kit.iti.structuredtext.datatypes.AnyInt;
import edu.kit.iti.structuredtext.datatypes.IECString;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 13.06.14.
 */
public class StringTypeDeclaration extends  TypeDeclaration {
    private ScalarValue<? extends IECString, String> initializationValue;
    private ScalarValue<? extends AnyInt, Integer> size;

    public void setInitializationValue(ScalarValue<? extends IECString, String> initializationValue) {
        this.initializationValue = initializationValue;

    }

    public ScalarValue<? extends IECString, String> getInitializationValue() {
        return initializationValue;
    }

    public void setSize(ScalarValue<? extends AnyInt, Integer> size) {
        this.size = size;
    }

    public ScalarValue<? extends AnyInt, Integer> getSize() {
        return size;
    }
}
