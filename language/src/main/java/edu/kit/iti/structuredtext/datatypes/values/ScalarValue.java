package edu.kit.iti.structuredtext.datatypes.values;

import edu.kit.iti.structuredtext.ast.Expression;
import edu.kit.iti.structuredtext.ast.Initialization;
import edu.kit.iti.structuredtext.datatypes.Any;

/**
 * Created by weigl on 11.06.14.
 */
public class ScalarValue<T extends Any, S> extends Expression implements Value<T>, Initialization
{
    private T dataType;
    private S value;


    public ScalarValue(T dataType, S value) {
        this.dataType = dataType;
        this.value = value;
    }

    @Override
    public T getDataType() {
        return dataType;
    }

    public void setDataType(T dataType) {
        this.dataType = dataType;
    }

    public S getValue() {
        return value;
    }

    public void setValue(S value) {
        this.value = value;
    }
}
