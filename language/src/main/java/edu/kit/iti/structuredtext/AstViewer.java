package edu.kit.iti.structuredtext;

import com.google.common.base.Strings;
import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.ast.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by weigl on 11.06.14.
 */
public class AstViewer {

    public static void main(String[] argv) throws IOException {


        StructuredTextLexer lexer = new StructuredTextLexer(new ANTLRFileStream("statements.st"));
        StructuredTextParser parser = new StructuredTextParser(new CommonTokenStream(lexer));

        StructuredTextParser.Statement_listContext parseTree = parser.statement_list();

        System.out.println(parseTree.toStringTree(Arrays.asList(StructuredTextParser.ruleNames)));

        DumperOptions options = new DumperOptions();






        Yaml yaml = new Yaml(new PointRepresenter(), options);


        for (Statement stmt : parseTree.ast) {
            System.out.println(yaml.dump(stmt));

            System.out.println(Strings.repeat("\n", 8));
        }


    }

    static class PointRepresenter extends Representer {

        protected Tag getTag(Class<?> clazz, Tag defaultTag) {

            if (classTags.containsKey(clazz)) {

                return classTags.get(clazz);
            } else {
                if(clazz.getCanonicalName().startsWith("edu"))
                {
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
}
