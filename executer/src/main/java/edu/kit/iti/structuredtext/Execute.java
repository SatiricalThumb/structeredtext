package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.ast.TopLevelElement;
import java.io.File;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.List;

/**
 * Created by weigl on 13.06.14.
 */
public class Execute {
    public static void main(String argv[]) throws IOException {
        
        System.out.println(new File(".").getAbsolutePath());

        ANTLRFileStream input = new ANTLRFileStream("sample.st");
        StructuredTextLexer lexer = new StructuredTextLexer(input);

        StructuredTextParser.StartContext ctx = new StructuredTextParser(new CommonTokenStream(lexer)).start();
        List<TopLevelElement> elements = ctx.ast;

        Utils.dump(elements);

        StructuredTextPrinter printer = new StructuredTextPrinter();
        for (TopLevelElement element : elements) {
            element.visit(printer);
        }

        System.out.println(printer.getString());
        
        ExecuteAST v = new ExecuteAST();
        v.visit(elements);

    }

}
