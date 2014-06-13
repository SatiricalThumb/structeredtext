package edu.kit.iti.structuredtext;

import com.google.common.base.Strings;
import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextListener;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.ast.Statement;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by weigla on 09.06.2014.
 */
public class Utils {

    public static void dump(Object o) {

        DumperOptions options = new DumperOptions();
        Yaml yaml = new Yaml(new PointRepresenter(), options);
        System.out.println(yaml.dump(o));
    }

    private static class PointRepresenter extends Representer {
        protected Tag getTag(Class<?> clazz, Tag defaultTag) {
            if (classTags.containsKey(clazz)) {
                return classTags.get(clazz);
            } else {
                if (clazz.getCanonicalName().startsWith("edu")) {
                    return new Tag(clazz.getName());
                }
                return defaultTag;
            }
        }

        @Override
        protected NodeTuple representJavaBeanProperty(Object javaBean, Property property,
                                                      Object propertyValue, Tag customTag) {
            if (propertyValue == null) return null;
            return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
        }
    }

    public static ParseTree parseStructuredText(String input, String rule) {
        return parseStructuredText(new ANTLRInputStream(input), rule);
    }

    public static ParseTree parseStructuredText(ANTLRInputStream stream, String rule) {
        try {
            StructuredTextLexer stl = new StructuredTextLexer(stream);
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

    public static void compareTokens(List<? extends Token> tokens, String[] expected, Lexer lexer) {
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
