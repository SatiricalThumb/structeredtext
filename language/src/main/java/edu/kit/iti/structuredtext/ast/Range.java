package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.datatypes.AnyInt;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 13.06.14.
 */
public class Range {
    private ScalarValue<? extends AnyInt, Integer> start, stop;

    public Range(ScalarValue<? extends AnyInt, Integer> start, ScalarValue<? extends AnyInt, Integer> stop) {
        this.start = start;
        this.stop = stop;
    }

    public ScalarValue<? extends AnyInt, Integer> getStart() {
        return start;
    }

    public void setStart(ScalarValue<? extends AnyInt, Integer> start) {
        this.start = start;
    }

    public ScalarValue<? extends AnyInt, Integer> getStop() {
        return stop;
    }

    public void setStop(ScalarValue<? extends AnyInt, Integer> stop) {
        this.stop = stop;
    }
}
