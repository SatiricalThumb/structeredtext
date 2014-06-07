package edu.kit.iti.structuredtext;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.antlr.runtime.TokenStream;
import org.antlr.runtime.debug.DebugTokenStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.junit.Test;

import edu.kit.iti.structuredtext.antlr.StructuredTextLexer;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;

public class ExpressionTest {

	@Test
	public void test() throws IOException {
		BufferedReader bis = new BufferedReader(new InputStreamReader(
				getClass().getResourceAsStream("expressions.txt")));

		String tmp;
		while ((tmp = bis.readLine()) != null) {
			test_line_lexer(tmp);
			test_line_parser(tmp);
		}

	}

	private void test_line_lexer(String tmp) {
		StructuredTextLexer stl = new StructuredTextLexer(new ANTLRInputStream(
				tmp));
		List<? extends Token> tokens = stl.getAllTokens();

		for (int i = 0; i < 2; i++) {
			for (Token token : tokens) {
				String text = token.getText();
				String type = StructuredTextLexer.tokenNames[token.getType()];
				int length = Math.max(text.length(), type.length());
											
				if (i == 0)
					System.out.format(" %" + length + "s ", text);
				else
					System.out.format(" %" + length + "s ", type);
			}
			System.out.println();
		}
	}

	private void test_line_parser(String tmp) {
		StructuredTextLexer stl = new StructuredTextLexer(new ANTLRInputStream(
				tmp));
		CommonTokenStream cts = new CommonTokenStream(stl);
		StructuredTextParser stp = new StructuredTextParser(cts);

		stp.setBuildParseTree(true);
		stp.expression();
		assertEquals(0, stp.getNumberOfSyntaxErrors());

	}

}
