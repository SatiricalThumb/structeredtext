package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.datatypes.AnyInt;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 13.06.14.
 */
public class SubRangeDataType extends TypeDeclaration {
    private ScalarValue<? extends AnyInt, Integer> initializationValue;
    private Range range;

    public void setInitializationValue(ScalarValue<? extends AnyInt, Integer> initializationValue) {
        this.initializationValue = initializationValue;
    }

    public ScalarValue<? extends AnyInt, Integer> getInitializationValue() {
        return initializationValue;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public Range getRange() {
        return range;
    }
}
