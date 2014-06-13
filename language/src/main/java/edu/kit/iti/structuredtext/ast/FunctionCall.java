package edu.kit.iti.structuredtext.ast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weigla on 09.06.2014.
 */
public class FunctionCall extends Expression {
    private String functionName;
    private Map<String, Expression> inputParameters = new HashMap<>();
    private Map<String, Reference> outputParameters = new HashMap<>();


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

    public Map<String, Reference> getOutputParameters() {
        return outputParameters;
    }

    public void setOutputParameters(Map<String, Reference> outputParameters) {
        this.outputParameters = outputParameters;
    }

    public void addInputParameter(String key, Expression visit) {
        inputParameters.put(key,visit);
    }

    public void addOutputParameter(String key, Reference visit) {
        outputParameters.put(key, visit);
    }
}
