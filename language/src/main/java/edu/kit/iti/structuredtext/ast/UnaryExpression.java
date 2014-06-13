package edu.kit.iti.structuredtext.ast;


public class UnaryExpression extends Expression {
    public static enum Operator {
        MINUS, NEGATE;
    }

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
}
