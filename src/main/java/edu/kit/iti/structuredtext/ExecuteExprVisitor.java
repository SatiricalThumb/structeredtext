package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.ast.Expression;
import edu.kit.iti.structuredtext.visitors.VisitorHelpers;

/**
 * Created by weigla on 09.06.2014.
 */
public class ExecuteExprVisitor {
    public static void main(String[] argv) throws Exception {
        String test = ("2*3+5*5+2");
        Expression obj = VisitorHelpers.toExpression(
                (StructuredTextParser.ExpressionContext)
                        Utils.parseStructuredText(test, "expression"));

        System.out.println(obj);
    }
}
