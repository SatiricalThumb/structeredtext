package edu.kit.iti.structuredtext.ui;

import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;

/**
 * Created by weigl on 13.06.14.
 */
public class StructuredTextDocument extends RSyntaxDocument {
    public StructuredTextDocument(TokenMakerFactory tmf, String syntaxStyle) {
        super(tmf, syntaxStyle);
    }
}
