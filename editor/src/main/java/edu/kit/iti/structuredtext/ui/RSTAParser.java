package edu.kit.iti.structuredtext.ui;

import edu.kit.iti.structuredtext.Utils;
import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.Nullable;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.parser.*;
import org.fife.ui.rsyntaxtextarea.parser.Parser;
import org.stringtemplate.v4.gui.JTreeSTModel;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.net.URL;
import java.util.BitSet;
import java.util.List;

/**
 * Created by weigl on 13.06.14.
 */
public class RSTAParser implements Parser {

    private final MainFrame.EditorPane editorPane;

    public RSTAParser(MainFrame.EditorPane pane) {
        editorPane = pane;
    }

    @Override
    public ExtendedHyperlinkListener getHyperlinkListener() {
        return null;
    }

    @Override
    public URL getImageBase() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public ParseResult parse(RSyntaxDocument doc, String style) {
        try {
            System.out.println(style);
            final String string = doc.getText(0, doc.getLength());
            StructuredTextParser parser = new StructuredTextParser(new CommonTokenStream(new StructuredTextLexer(new ANTLRInputStream(string))));

            ParseResultListener parseResultListener = new ParseResultListener(this);
            parser.addErrorListener(parseResultListener);

            ProblemListErrorListener errorListener = new ProblemListErrorListener();
            parser.addErrorListener(errorListener);

            long time = System.currentTimeMillis();
            StructuredTextParser.StartContext context = parser.start();

            parseResultListener.parseResult.setParseTime(System.currentTimeMillis() - time);
            parseResultListener.parseResult.setParsedLines(1, 1 + getLine(string));


            editorPane.updateProblemsTable(errorListener.problems);
            editorPane.updateStructure(context.ast);


            return parseResultListener.parseResult;
            //Utils.dump(context.ast);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        return new DefaultParseResult(this);
    }

    public static int getLine(String s, int offSet) {
        char[] c = s.toCharArray();
        int line = 1;
        for (int i = 0; i < Math.min(c.length, offSet); i++) {
            if (c[i] == '\n') line++;
        }
        return line;
    }

    public static int getLine(String s) {
        return getLine(s, Integer.MAX_VALUE);
    }
}

class ParseResultListener implements ANTLRErrorListener {
    DefaultParseResult parseResult;
    private Parser parser;

    ParseResultListener(Parser parser) {
        this.parseResult = new DefaultParseResult(parser);
        this.parser = parser;
    }

    @Override
    public void syntaxError(@NotNull Recognizer<?, ?> recognizer, @Nullable Object offendingSymbol, int line,
                            int charPositionInLine, @NotNull String msg, @Nullable RecognitionException e) {
        parseResult.addNotice(new DefaultParserNotice(parser, msg, line, charPositionInLine, 5));
    }

    @Override
    public void reportAmbiguity(@NotNull org.antlr.v4.runtime.Parser recognizer, @NotNull DFA dfa, int startIndex, int stopIndex, boolean exact, @Nullable BitSet ambigAlts, @NotNull ATNConfigSet configs) {
        //parseResult.addNotice(new DefaultParserNotice(parser, "ambiguity", getLine(string, startIndex)));
    }

    @Override
    public void reportAttemptingFullContext(@NotNull org.antlr.v4.runtime.Parser recognizer, @NotNull DFA dfa, int startIndex, int stopIndex, @Nullable BitSet conflictingAlts, @NotNull ATNConfigSet configs) {
        //parseResult.addNotice(new DefaultParserNotice(parser, "fullcontext", getLine(string, startIndex)));
    }

    @Override
    public void reportContextSensitivity(@NotNull org.antlr.v4.runtime.Parser recognizer, @NotNull DFA dfa, int startIndex, int stopIndex, int prediction, @NotNull ATNConfigSet configs) {
        //parseResult.addNotice(new DefaultParserNotice(parser, "context", getLine(string, startIndex)));
    }
}