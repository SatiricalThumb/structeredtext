package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import org.antlr.v4.runtime.*;


import java.util.List;


/**
 * Created by weigla on 07.06.2014.
 */
public class SmallTest {
    public static void main(String[] argv) throws Exception {
        String input = "TYPE feld: ARRAY[1..16] OF INT;\n" +
                "            END_TYPE";
        test_line_lexer(input);
        MyTestRig rig = new MyTestRig(input,  "data_type_declaration");
    }

    private static void test_line_lexer(String tmp) {
        StructuredTextLexer stl = new StructuredTextLexer(new ANTLRInputStream(
                tmp));
        List<? extends Token> tokens = stl.getAllTokens();

        for (int i = 0; i < 2; i++) {
            for (Token token : tokens) {
                String text = token.getText();
                String type = StructuredTextLexer.tokenNames[token.getType()];
                int length = Math.max(text.length(), type.length());

                if (i == 0)
                    System.out.format(" %-" + length + "s ", text);
                else
                    System.out.format(" %-" + length + "s ", type);
            }
            System.out.println();
        }
    }
}
