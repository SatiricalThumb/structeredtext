package edu.kit.iti.structuredtext.functions;

import edu.kit.iti.structuredtext.ValueFactory;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.AnyBit;
import edu.kit.iti.structuredtext.datatypes.AnyNum;
import edu.kit.iti.structuredtext.datatypes.values.Bits;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by weigl on 10.06.14.
 */
public abstract class Operator {
    public static Bits AND(Bits a, Bits b) {
        return a.and(b);
    }

    public static Bits OR(Bits a, Bits b) {
        return a.or(b);
    }

    public static Bits XOR(Bits a, Bits b) {
        return a.or(b);
    }

    public static Boolean AND(Boolean a, Boolean b) {
        return a && b;
    }

    public static Boolean OR(Boolean a, Boolean b) {
        return a || b;
    }

    public static Boolean XOR(Boolean a, Boolean b) {
        return a ^ b;
    }

    public static Object invoke(String operator, Object a, Object b) {
        try {
            Method m =  Operator.class.getMethod(operator, a.getClass(), b.getClass());
            return m.invoke(a,b);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
