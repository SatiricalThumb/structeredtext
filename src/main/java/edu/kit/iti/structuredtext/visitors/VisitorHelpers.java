package edu.kit.iti.structuredtext.visitors;

import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.ast.Expression;
import edu.kit.iti.structuredtext.ast.Statement;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Created by weigl on 10.06.14.
 */
public class VisitorHelpers {
    public static Statement toStatement(StructuredTextParser.StatementContext ctx) {
        StatementToAstVisitor visitor = new StatementToAstVisitor();
        return visitor.visit(ctx);
    }

    public static Expression toExpression(StructuredTextParser.ExpressionContext ctx) {
        ExpressionParseTreeVisitor visitor = new ExpressionParseTreeVisitor();
        return ctx.accept(visitor);
    }

    public static void test() {
        String tmp = "IF b THEN a:= 1; END_IF";

        StructuredTextLexer stl = new StructuredTextLexer(new ANTLRInputStream(tmp));
        CommonTokenStream cts = new CommonTokenStream(stl);
        StructuredTextParser stp = new StructuredTextParser(cts);
        stp.setBuildParseTree(true);
        StructuredTextParser.StatementContext obj = stp.statement();
        toStatement(obj);
    }


}
