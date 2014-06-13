package edu.kit.iti.structuredtext.ui;

import com.sun.javafx.fxml.expression.Expression;
import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;

import static edu.kit.iti.structuredtext.antlr.StructuredTextLexer.*;

import org.antlr.v4.runtime.*;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.folding.Fold;
import org.fife.ui.rsyntaxtextarea.folding.FoldParser;
import org.fife.ui.rsyntaxtextarea.folding.FoldType;

import javax.swing.text.BadLocationException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by weigl on 13.06.14.
 */
public class StructuredTextFoldParser implements FoldParser {
    @Override
    public List<Fold> getFolds(RSyntaxTextArea textArea) {
        List<Fold> folds = new LinkedList<>();
        try {

            Stack<Fold> stack = new Stack<>();


            String text = textArea.getText();
            StructuredTextLexer lexer = new StructuredTextLexer(new ANTLRInputStream(text));
            List<? extends Token> tokens = lexer.getAllTokens();


            for (Token t : tokens) {
                if (isClosener(t) && !stack.isEmpty()) {
                    Fold top = stack.pop();
                    if (top != null) {
                        top.setEndOffset(t.getStopIndex());
                        folds.add(top);
                    }
                }


                if (isOpener(t)) {
                    Fold currentFold;
                    if (!stack.isEmpty()) {
                        currentFold = stack.peek().createChild(FoldType.CODE, t.getStartIndex());
                    } else {
                        currentFold = new Fold(FoldType.CODE, textArea, t.getStartIndex());
                    }

                    stack.push(currentFold);
                }

            }

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return folds;
    }


    private boolean isClosener(Token t) {
        switch (t.getType()) {
            case END_CASE:
            case END_CONFIGURATION:
            case END_FOR:
            case END_FUNCTION:
            case END_FUNCTION_BLOCK:
            case END_IF:
            case END_PROGRAM:
            case END_REPEAT:
            case END_RESOURCE:
            case END_STRUCT:
            case END_TYPE:
            case END_VAR:
            case END_WHILE:
            case ELSE:
            case ELSEIF:
            case UNTIL:// closes REPEAT

                return true;

        }
        return false;
    }

    private boolean isOpener(Token t) {
        switch (t.getType()) {
            case CONFIGURATION:
            case ELSE:
            case THEN: // IF ELSEIF
            case DO: // FOR / WHILE
            case FUNCTION:
            case FUNCTION_BLOCK:
            case PROGRAM:
            case REPEAT:
            case RESOURCE:
            case TYPE:
            case VAR:
            case VAR_ACCESS:
            case VAR_CONFIG:
            case VAR_EXTERNAL:
            case VAR_GLOBAL:
            case VAR_INPUT:
            case VAR_IN_OUT:
            case VAR_TEMP:
                return true;
        }
        return false;
    }
}
