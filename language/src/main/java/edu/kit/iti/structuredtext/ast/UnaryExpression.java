package edu.kit.iti.structuredtext.ast;


import edu.kit.iti.structuredtext.Visitor;
import edu.kit.iti.structuredtext.datatypes.IECString;

public class UnaryExpression extends Expression {
    Operator operator;
    Expression expression;
    public UnaryExpression(Operator operator, Expression expression) {
        this.operator = operator;
        this.expression = expression;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }


    public static enum Operator {
        MINUS("-"), NEGATE("NOT");

        public final String symbol;
        Operator(String symbol) {
            this.symbol = symbol;
        }
    }
}
