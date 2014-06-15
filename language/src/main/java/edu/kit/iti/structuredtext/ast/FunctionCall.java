package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weigla on 09.06.2014.
 */
public class FunctionCall extends Expression {
    private String functionName;
    private List<Parameter> parameters = new ArrayList<>();

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void addInputParameter(String key, Expression visit)
    {
        if(visit==null)
            throw new IllegalArgumentException("expression can not be null");
        parameters.add(new Parameter(key, false, visit));
    }

    public void addOutputParameter(String key, Reference visit) {
        assert key!=null;
        assert visit!=null;

        parameters.add(new Parameter(key, false, visit));
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }


    public class Parameter
    {
        private String name;
        private boolean output;
        private Expression expression;

        Parameter(String name, boolean output, Expression expression) {
            this.name = name;
            this.output = output;
            this.expression = expression;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isOutput() {
            return output;
        }

        public void setOutput(boolean output) {
            this.output = output;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }
    }
}
