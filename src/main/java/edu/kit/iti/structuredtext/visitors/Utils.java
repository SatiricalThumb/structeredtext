package edu.kit.iti.structuredtext.visitors;

import edu.kit.iti.structuredtext.MyTestRig;
import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextListener;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.ast.Expression;
import edu.kit.iti.structuredtext.ast.Statement;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.gui.TreeViewer;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by weigla on 09.06.2014.
 */
public class Utils {

    public static ParseTree parseStructuredText(String input, String rule) {
        try {
            StructuredTextLexer stl = new StructuredTextLexer(new ANTLRInputStream(input));
            CommonTokenStream cts = new CommonTokenStream(stl);
            StructuredTextParser stp = new StructuredTextParser(cts);
            Class<?> clazz = stp.getClass();
            Method method = null;
            method = clazz.getMethod(rule);
            return (ParseTree) method.invoke(stp);
        } catch (Exception e) {
            return null;
        }
    }

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
