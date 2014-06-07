package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.gui.TreeViewer;
import org.antlr.v4.runtime.tree.ParseTree;


import javax.swing.*;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by weigla on 07.06.2014.
 */
public class ShowParserTree {
    public static void main(String[] argv) throws IOException {
        String def = "sample.st";

        if(argv.length==1)
        {
            def = argv[0];
        }

        showParseTree(def);
    }

    private static void showParseTree(String def) throws IOException {
        StructuredTextLexer stl = new StructuredTextLexer(new ANTLRFileStream(def));
        CommonTokenStream cts = new CommonTokenStream(stl);
        StructuredTextParser stp = new StructuredTextParser(cts);
        stp.setBuildParseTree(true);
        stp.setTrimParseTree(true);
        stp.setBuildParseTree(true);
        ParseTree pt = stp.start();

        //show AST in GUI
        JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                stp.getRuleNames()),pt);
        viewr.setScale(1.5);//scale a little
        panel.add(viewr);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true);
    }
}
