package edu.kit.iti.structuredtext.ui;

import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMaker;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMaker;
import org.fife.ui.rsyntaxtextarea.TokenMap;

import javax.swing.text.Segment;
import java.util.List;

/**
 * Created by weigl on 13.06.14.
 */
public class STTokenMaker extends AbstractTokenMaker {
    static TokenMap TOKEN_MAP = new TokenMap(true);

    static {
        TOKEN_MAP.put("case", Token.RESERVED_WORD);
        TOKEN_MAP.put("for", Token.RESERVED_WORD);
        TOKEN_MAP.put("if", Token.RESERVED_WORD);
        TOKEN_MAP.put("while", Token.RESERVED_WORD);

        TOKEN_MAP.put("printf", Token.FUNCTION);
        TOKEN_MAP.put("scanf", Token.FUNCTION);
        TOKEN_MAP.put("fopen", Token.FUNCTION);
    }

    @Override
    public TokenMap getWordsToHighlight() {
        return TOKEN_MAP;
    }

    @Override
    public Token getTokenList(Segment text, int startTokenType, int startOffset) {
        String s = text.toString();
        resetTokenList();

        //addToken(text, text.getBeginIndex(), text.getEndIndex(), Token.IDENTIFIER, startOffset);

        int lineOffset = startOffset;

        StructuredTextLexer lexer = new StructuredTextLexer(new ANTLRInputStream(s));
        List<? extends org.antlr.v4.runtime.Token> list = lexer.getAllTokens();


        resetTokenList();

        if (list.size() == 0) {
            addNullToken();
        }

        for (org.antlr.v4.runtime.Token tok : list) {
            int type = getTokenType(tok);

            lineOffset = startOffset + tok.getStartIndex();

            int start = text.offset + tok.getStartIndex();
            int end = text.offset + tok.getStopIndex();
            addToken(text.array, start, end, type, lineOffset);
        }

        return firstToken;
    }

    private int getTokenType(org.antlr.v4.runtime.Token tok) {
        int type;
        switch (tok.getType()) {
            case StructuredTextLexer.WS:
                type = Token.WHITESPACE;
                break;

            case StructuredTextLexer.LPAREN:
            case StructuredTextLexer.RPAREN:
            case StructuredTextLexer.LBRACKET:
            case StructuredTextLexer.RBRACKET:
            case StructuredTextLexer.COLON:
            case StructuredTextLexer.COMMA:
                type = Token.SEPARATOR;
                break;

            case StructuredTextLexer.COMMENT:
                type = Token.COMMENT_MULTILINE;
                break;
            case StructuredTextLexer.IDENTIFIER:
                type = Token.IDENTIFIER;
                break;
            case StructuredTextLexer.IF:
            case StructuredTextLexer.ELSE:
            case StructuredTextLexer.ELSEIF:
            case StructuredTextLexer.FOR:
            case StructuredTextLexer.END_FOR:
            case StructuredTextLexer.END_IF:
                type = Token.RESERVED_WORD;
                break;

            case StructuredTextLexer.INTEGER_LITERAL:
                type = Token.LITERAL_NUMBER_DECIMAL_INT;
                break;
            case StructuredTextLexer.REAL_LITERAL:
                type = Token.LITERAL_NUMBER_FLOAT;
                break;
            case StructuredTextLexer.WSTRING_LITERAL:
            case StructuredTextLexer.STRING:
                type = Token.LITERAL_STRING_DOUBLE_QUOTE;
                break;
            default:
                type = Token.ANNOTATION;
        }
        return type;
    }
}
