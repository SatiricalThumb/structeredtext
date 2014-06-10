package edu.kit.iti.structuredtext.visitors;

import edu.kit.iti.structuredtext.antlr.StructuredTextBaseVisitor;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.antlr.StructuredTextVisitor;
import edu.kit.iti.structuredtext.ast.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.function.BinaryOperator;

public class ExpressionParseTreeVisitor extends StructuredTextBaseVisitor<Expression> {

    @Override
    public Expression visitPrimary_expression(@NotNull StructuredTextParser.Primary_expressionContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public Expression visitExpression(@NotNull StructuredTextParser.ExpressionContext ctx) {

        //LPAREN expr RPAREN
        if (ctx.LPAREN() != null) {
            return visit(ctx.expression(0));
        }

        if (ctx.unary_operator() != null) {
            UnaryExpression.Operator op = null;
            if (ctx.unary_operator().MINUS() != null) {
                op = UnaryExpression.Operator.MINUS;
            }
            if (ctx.unary_operator().NOT() != null) {
                op = UnaryExpression.Operator.NEGATE;
            }
            UnaryExpression ue = new UnaryExpression(op, visit(ctx.expression(0)));
            return ue;
        }

        if (ctx.primary_expression() != null) {
            return visit(ctx.primary_expression());
        }
        //BinaryCase
        BinaryExpression.Operator op = null;

        for (BinaryExpression.Operator o : BinaryExpression.Operator.values()) {
            if (ctx.getToken(o.token, 0) != null) {
                op = o;
                break;
            }
        }

        StructuredTextParser.ExpressionContext left = ctx.expression(0);
        StructuredTextParser.ExpressionContext right = ctx.expression(1);


        BinaryExpression be = new BinaryExpression(visit(left), visit(right), op);
        return be;
    }
}
