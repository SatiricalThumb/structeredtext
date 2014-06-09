package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.gui.TreeViewer;
import org.antlr.v4.runtime.tree.ParseTree;


import javax.swing.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by weigla on 07.06.2014.
 */
public class SmallTest {
    public static void main(String[] argv) throws Exception {
        test_line_lexer("2*3+5*5+2");
        MyTestRig rig = new MyTestRig("2*3+5*5+2", "expression");
    }

    private static void test_line_lexer(String tmp) {
        StructuredTextLexer stl = new StructuredTextLexer(new ANTLRInputStream(
                tmp));
        List<? extends Token> tokens = stl.getAllTokens();

        for (int i = 0; i < 2; i++) {
            for (Token token : tokens) {
                String text = token.getText();
                String type = StructuredTextLexer.tokenNames[token.getType()];
                int length = Math.max(text.length(), type.length());

                if (i == 0)
                    System.out.format(" %-" + length + "s ", text);
                else
                    System.out.format(" %-" + length + "s ", type);
            }
            System.out.println();
        }
    }
}
