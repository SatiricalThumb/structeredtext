package edu.kit.iti.structuredtext.ast;

import lombok.Data;
import lombok.NonNull;


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
}
