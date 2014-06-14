package edu.kit.iti.structuredtext.datatypes.values;

import edu.kit.iti.structuredtext.datatypes.Any;

/**
 * Created by weigl on 11.06.14.
 */
public interface Value<T extends Any> {
    public T getDataType();
}
