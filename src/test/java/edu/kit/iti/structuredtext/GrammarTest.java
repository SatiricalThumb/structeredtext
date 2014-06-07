package edu.kit.iti.structuredtext;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.antlr.runtime.TokenStream;
import org.antlr.runtime.debug.DebugTokenStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;

import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;

public class GrammarTest {

    @Test
    public void test() throws IOException, JDOMException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        SAXBuilder builder = new SAXBuilder();
        Document document = (Document) builder.build(
                getClass().getResourceAsStream("StructuredText_01.gunit.xml")
        );

        Element rootNode = document.getRootElement();

        for (Element parser : rootNode.getChildren("parser")) {
            String val_ml = parser.getAttributeValue("multiline");
            boolean multiline = Boolean.valueOf(val_ml);
            String rule = parser.getAttributeValue("rule");

            if (multiline) {
                test_line_parser(parser.getTextNormalize().trim(), rule);
            } else {
                for (String line : parser.getTextNormalize().trim().split("\n")) {
                    test_line_parser(line, rule);
                }
            }

        }
    }

    private void test_line_lexer(String tmp) {
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

    private void test_line_parser(String tmp, String rule) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StructuredTextLexer stl = new StructuredTextLexer(new ANTLRInputStream(
                tmp));
        CommonTokenStream cts = new CommonTokenStream(stl);
        StructuredTextParser stp = new StructuredTextParser(cts);


        stp.setBuildParseTree(true);
        Class<?> clazz = stp.getClass();
        Method method = clazz.getMethod(rule);
        System.out.println(tmp);
        method.invoke(stp);
        assertEquals(0, stp.getNumberOfSyntaxErrors());
    }
}
