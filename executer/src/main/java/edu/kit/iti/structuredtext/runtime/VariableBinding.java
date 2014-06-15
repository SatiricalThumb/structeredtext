package edu.kit.iti.structuredtext.runtime;

import edu.kit.iti.structuredtext.ast.VariableDeclaration;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 13.06.14.
 */
public class VariableBinding extends VariableDeclaration {
    private ScalarValue< ?extends Any, ?> value;

    
    public VariableBinding() 
    {

    }
    
    public VariableBinding(String name) 
    {
    	
    
    }

	public ScalarValue<?extends Any,?> getValue() {
        return value;
    }
}
