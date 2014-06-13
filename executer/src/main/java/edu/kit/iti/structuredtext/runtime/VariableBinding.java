package edu.kit.iti.structuredtext.runtime;

import edu.kit.iti.structuredtext.ast.Initialization;
import edu.kit.iti.structuredtext.ast.TypeDeclaration;
import edu.kit.iti.structuredtext.ast.Variable;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;
import edu.kit.iti.structuredtext.datatypes.values.Value;

/**
 * Created by weigl on 13.06.14.
 */
public class VariableBinding extends Variable{
    private ScalarValue< ?extends Any, ?> value;

    public ScalarValue<?extends Any,?> getValue() {
        return value;
    }
}
