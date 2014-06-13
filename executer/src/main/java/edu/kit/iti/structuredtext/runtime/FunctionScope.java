package edu.kit.iti.structuredtext.runtime;

import edu.kit.iti.structuredtext.Scope;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 13.06.14.
 */
public class FunctionScope extends Scope {
    public FunctionScope(Scope scope) {
        super(scope);
        push();//Locals
    }

    public FunctionScope() {

    }

    public void setParameter(String left, ScalarValue<?, ?> leftValue) {

    }

}
