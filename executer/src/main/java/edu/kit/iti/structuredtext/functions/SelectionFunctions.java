package edu.kit.iti.structuredtext.functions;

import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.AnyBit;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 11.06.14.
 */
public class SelectionFunctions {


    public static <T extends Any, S> ScalarValue<T, S>
    sel(ScalarValue<AnyBit.Bool, Boolean> g, ScalarValue<T, S> a, ScalarValue<T, S> b)
    {
        if (g.getValue())
            return a;
        else
            return b;


    }

    // TODO max, min, LIMIT, MUX

}
