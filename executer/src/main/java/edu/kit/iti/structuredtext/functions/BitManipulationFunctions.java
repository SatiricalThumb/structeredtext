package edu.kit.iti.structuredtext.functions;

import edu.kit.iti.structuredtext.datatypes.AnyBit;
import edu.kit.iti.structuredtext.datatypes.AnyInt;
import edu.kit.iti.structuredtext.datatypes.values.Bits;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 11.06.14.
 */
public class BitManipulationFunctions {
    public static <T extends AnyBit, S extends AnyInt> ScalarValue<T, Bits>
    shl(ScalarValue<T, Bits> in, ScalarValue<S, Integer> n) {
        return new ScalarValue<>(in.getDataType(),
                in.getValue().shl(n.getValue()));
    }


    public static <T extends AnyBit, S extends AnyInt> ScalarValue<T, Bits>
    shr(ScalarValue<T, Bits> in, ScalarValue<S, Integer> n) {
        return new ScalarValue<>(in.getDataType(),
                in.getValue().shr(n.getValue()));
    }

    public static <T extends AnyBit, S extends AnyInt> ScalarValue<T, Bits>
    ror(ScalarValue<T, Bits> in, ScalarValue<S, Integer> n) {
        return new ScalarValue<>(in.getDataType(),
                in.getValue().ror(n.getValue()));
    }

    public static <T extends AnyBit, S extends AnyInt> ScalarValue<T, Bits>
    rol(ScalarValue<T, Bits> in, ScalarValue<S, Integer> n) {
        return new ScalarValue<>(in.getDataType(),
                in.getValue().rol(n.getValue()));
    }
}
