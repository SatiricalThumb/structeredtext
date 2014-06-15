package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;
import edu.kit.iti.structuredtext.datatypes.AnyInt;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 13.06.14.
 */
public class SubRangeDataType extends TypeDeclaration<ScalarValue<? extends AnyInt, Long>> {
    private Range range;

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
