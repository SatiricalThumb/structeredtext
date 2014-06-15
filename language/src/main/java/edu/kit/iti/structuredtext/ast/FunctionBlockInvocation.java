package edu.kit.iti.structuredtext.ast;


import edu.kit.iti.structuredtext.Visitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weigla on 09.06.2014.
 */
public class FunctionBlockInvocation extends Statement {
    String functionName;
    Map<String, Expression> inputParameters = new HashMap<>();
    Map<String, Expression> outputParameters = new HashMap<>();

    @Override
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
