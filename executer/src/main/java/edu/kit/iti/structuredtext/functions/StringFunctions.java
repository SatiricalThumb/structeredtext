package edu.kit.iti.structuredtext.functions;

import edu.kit.iti.structuredtext.ValueFactory;
import edu.kit.iti.structuredtext.datatypes.AnyInt;
import edu.kit.iti.structuredtext.datatypes.IECString;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 11.06.14.
 */
public class StringFunctions {

    public static ScalarValue<AnyInt.Int, Integer> len(String a) {
        return ValueFactory.makeInt(a.length());
    }

    public static <T extends IECString> ScalarValue<T, String>
    left(ScalarValue<T, String> a, ScalarValue<AnyInt, Integer> b) {
        return new ScalarValue<T, String>(a.getDataType(),
                a.getValue().substring(b.getValue()));
    }

    public static <T extends IECString> ScalarValue<T, String>
    right(ScalarValue<T, String> a, ScalarValue<AnyInt, Integer> b) {
        return new ScalarValue<T, String>(a.getDataType(),
                a.getValue().substring(a.getValue().length() - b.getValue()));
    }


    public static <T extends IECString> ScalarValue<T, String>
    mid(ScalarValue<T, String> in, ScalarValue<AnyInt, Integer> len, ScalarValue<AnyInt, Integer> pos) {
        return new ScalarValue<T, String>(in.getDataType(),
                in.getValue().substring(pos.getValue(), len.getValue() + pos.getValue()));
    }

    public static <T extends IECString> ScalarValue<T, String>
    concat(ScalarValue<T, String> a, ScalarValue<T, String> b) {
        return new ScalarValue<>(a.getDataType(),
                a.getValue() + b.getValue()
        );
    }

    public static <T extends IECString> ScalarValue<T, String>
    insert(ScalarValue<T, String> a, ScalarValue<AnyInt, Integer> start, ScalarValue<AnyInt, Integer> end)
    {
        StringBuffer sb = new StringBuffer(a.getValue());
        sb.delete(start.getValue(), end.getValue());
        return new ScalarValue<>(a.getDataType(), sb.toString());
    }

    public static <T extends IECString> ScalarValue<T, String>
    replace(ScalarValue<T, String> a, ScalarValue<T, String> b, ScalarValue<AnyInt, Integer> len, ScalarValue<AnyInt, Integer> pos) {
        StringBuffer sb = new StringBuffer(a.getValue());
        sb.replace(pos.getValue(), len.getValue() + pos.getValue(), b.getValue());
        return new ScalarValue<>(a.getDataType(), sb.toString());
    }

    public static <T extends IECString> ScalarValue<AnyInt.Int, Integer>
    find(ScalarValue<T, String> a, ScalarValue<T, String> b) {
        return ValueFactory.makeInt(a.getValue().indexOf(b.getValue()));
    }





}
