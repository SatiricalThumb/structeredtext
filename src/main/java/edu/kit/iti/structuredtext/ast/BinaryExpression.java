package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

public class BinaryExpression extends Expression {
    public static enum Operator {
        //airthmetic
        ADD(StructuredTextParser.PLUS),
        MULT(StructuredTextParser.MULT),
        SUB(StructuredTextParser.MINUS),
        DIV(StructuredTextParser.DIV),
        MOD(StructuredTextParser.MOD),
        POW(StructuredTextParser.POWER),

        //logical
        AND(StructuredTextParser.AND),
        OR(StructuredTextParser.OR),
        XOR(StructuredTextParser.XOR),

        //comparison
        EQUALS(StructuredTextParser.EQUALS),
        NOT_EQUALS(StructuredTextParser.NOT_EQUALS),

        LESS_THAN(StructuredTextParser.LESS_THAN),
        GREATER_THAN(StructuredTextParser.GREATER_THAN),
        GREATER_EQUALS(StructuredTextParser.GREATER_EQUALS),
        LESS_EQUALS(StructuredTextParser.LESS_EQUALS);

        public final int token;
        Operator(int t) {
            token = t;
        }
    }

    Expression leftExpr, rightExpr;
    Operator operator;

    public BinaryExpression(Expression leftExpr, Expression rightExpr, Operator operator) {
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
        this.operator = operator;
    }
}
