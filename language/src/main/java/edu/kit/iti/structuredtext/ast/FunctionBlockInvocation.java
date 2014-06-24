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


    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Map<String, Expression> getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(Map<String, Expression> inputParameters) {
        this.inputParameters = inputParameters;
    }

    public Map<String, Expression> getOutputParameters() {
        return outputParameters;
    }

    public void setOutputParameters(Map<String, Expression> outputParameters) {
        this.outputParameters = outputParameters;
    }
}
