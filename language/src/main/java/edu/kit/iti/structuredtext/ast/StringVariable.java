package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.datatypes.AnyInt;
import edu.kit.iti.structuredtext.datatypes.IECString;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 13.06.14.
 */
public class StringVariable extends Variable {
    private ScalarValue<? extends AnyInt, Integer> length;

    public StringVariable(String name, Integer peek, ScalarValue<? extends AnyInt, Integer> length, ScalarValue<? extends IECString, String> def) {
        super(name, peek);
        this.length = length;
        setInit(def);
    }

    public ScalarValue<? extends AnyInt, Integer> getLength() {
        return length;
    }

    public void setLength(ScalarValue<? extends AnyInt, Integer> length) {
        this.length = length;
    }
}
