package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.antlr.StructuredTextParser;

import java.util.HashMap;
import java.util.Map;

public class BinaryExpression extends Expression {
    public static enum Operator {
        //airthmetic
        ADD("+"),
        MULT("*"),
        SUB("-"),
        DIV("/"),
        MOD("MOD"),

        //logical
        AND("AND"),
        OR("OR"),
        XOR("XOR"),

        //comparison
        EQUALS("="),
        NOT_EQUALS("<>"),

        POWER("**"),

        LESS_THAN("<"),
        GREATER_THAN(">"),
        GREATER_EQUALS(">="),
        LESS_EQUALS("<=");

        public final String token;
        Operator(String t) {
            token = t;
        }
    }

    public static final Map<String, Operator> OPERATOR_MAP = new HashMap<>();
    static {
        for(Operator o : Operator.values()){
            OPERATOR_MAP.put(o.token,o);
        }
    }

    private Expression leftExpr, rightExpr;
    private Operator operator;

    public BinaryExpression(Expression leftExpr, Expression rightExpr, Operator operator) {
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
        this.operator = operator;
    }

    public BinaryExpression(Expression leftExpr, Expression rightExpr, String operator) {
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
        this.operator = OPERATOR_MAP.get(operator);
    }

    public static Map<String, Operator> getOperatorMap() {
        return OPERATOR_MAP;
    }

    public Expression getLeftExpr() {
        return leftExpr;
    }

    public void setLeftExpr(Expression leftExpr) {
        this.leftExpr = leftExpr;
    }

    public Expression getRightExpr() {
        return rightExpr;
    }

    public void setRightExpr(Expression rightExpr) {
        this.rightExpr = rightExpr;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
