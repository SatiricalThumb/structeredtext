package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import org.antlr.v4.runtime.misc.TestRig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;



public class MyTestRig extends TestRig {
    public MyTestRig(String input, String rule) throws Exception {
        super(new String[]{"edu.kit.iti.structuredtext.StructuredText", rule});

        this.printTree=true;
        this.diagnostics=true;
        this.showTokens=true;
        this.gui=true;

        StructuredTextLexer lexer = new StructuredTextLexer(null);
        StructuredTextParser parser = new StructuredTextParser(null);

        InputStream is = new ByteArrayInputStream(input.getBytes());
        Reader r = new StringReader(input);

        process(lexer, parser.getClass(), parser, is, r);
    }
}
