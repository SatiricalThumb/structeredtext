package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.MyTestRig;
import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextListener;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.ast.Expression;
import edu.kit.iti.structuredtext.ast.Statement;
import edu.kit.iti.structuredtext.visitors.ExpressionParseTreeVisitor;
import edu.kit.iti.structuredtext.visitors.StatementToAstVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.gui.TreeViewer;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

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

    public static  void compareTokens(List<? extends Token> tokens, String[] expected, Lexer lexer) {
        try {
            for (int i = 0; i < expected.length; i++) {
                int expect = lexer.getTokenType(expected[i]);
                Token tok = tokens.get(i);
                String tokName = StructuredTextLexer.tokenNames[tok.getType()];


                if (!expected[i].contentEquals(tokName)) {
                    throw new AssertionError(
                            String.format("Token mismatch! Expected: %s but got %s",
                                    expected[i], tokName)
                    );
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AssertionError(
                    "Not enough tokens found!"
            );
        }

        if (expected.length < tokens.size()) {
            throw new AssertionError("Too much tokens found!");
        }
    }
}
