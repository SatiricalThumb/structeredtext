package edu.kit.iti.structuredtext.functions;

import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.AnyBit;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;
import edu.kit.iti.structuredtext.runtime.Function;

/**
 * Created by weigl on 13.06.14.
 */
public abstract class OperatorFunction<R extends Any, T> extends Function
{
    private Any leftDataType;
    private Any rightDataType;

    public OperatorFunction(String name)
    {
        super(name);
        parameterNames.add("left");
        parameterNames.add("right");
    }

    public abstract ScalarValue<R,T> invoke(ScalarValue<R,T> a, ScalarValue<R,T> b);
}
