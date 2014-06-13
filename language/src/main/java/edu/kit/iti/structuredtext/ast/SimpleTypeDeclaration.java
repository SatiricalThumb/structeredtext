package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 13.06.14.
 */
public class SimpleTypeDeclaration extends TypeDeclaration
{

    private ScalarValue<?, ?> initializationValue;

    public void setInitializationValue(ScalarValue<?,?> initializationValue) {
        this.initializationValue = initializationValue;
    }


}
