package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.ast.TopLevelElement;
import java.io.File;
import org.antlr.v4.runtime.ANTLRFileStream;

import java.io.IOException;
import java.util.List;

/**
 * Created by weigl on 13.06.14.
 */
public class Execute {
    public static void main(String argv[]) throws IOException {
        
        System.out.println(new File(".").getAbsolutePath());
        
        StructuredTextParser.StartContext ctx = (StructuredTextParser.StartContext) Utils.parseStructuredText(new ANTLRFileStream("sample.st"), "start");
        List<TopLevelElement> elements = ctx.ast;

        Utils.dump(elements);
        
        
        ExecuteAST v = new ExecuteAST();
        v.visit(elements);

    }

}
