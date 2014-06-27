package edu.kit.iti.structuredtext.symbex;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import edu.kit.iti.structuredtext.StructuredTextPrinter;
import edu.kit.iti.structuredtext.Visitable;
import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser.StartContext;
import edu.kit.iti.structuredtext.ast.Top;
import edu.kit.iti.structuredtext.translation.smv.SMVTranslationVisitor;

public class TestSymbex {

    public static void main(String[] args) throws Exception {

        String file = args.length == 0 ? "symbexSample.st" : args[0];

        StructuredTextLexer stl = new StructuredTextLexer(new ANTLRFileStream(file));
        CommonTokenStream cts = new CommonTokenStream(stl);
        StructuredTextParser stp = new StructuredTextParser(cts);
        stp.setBuildParseTree(true);
        stp.setTrimParseTree(true);
        stp.setBuildParseTree(true);
        StartContext pt = stp.start();

        StructuredTextPrinter printer = new StructuredTextPrinter();
        for (Top elem : pt.ast) {
            elem.visit(printer);
//            System.out.println(printer.getString());

            SymbolicExecutionVisitor symbex = new SymbolicExecutionVisitor();
            Visitable newElem = (Visitable) elem.visit(symbex);

            printer.clear();
            newElem.visit(printer);
//            System.out.println(printer.getString());

            SMVTranslationVisitor smv = new SMVTranslationVisitor();
            newElem.visit(smv);
            System.out.println(smv.getModule());
        }
    }


}
