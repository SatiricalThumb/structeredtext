package edu.kit.iti.structuredtext.ui;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.Nullable;

import java.util.*;

/**
 * Created by weigl on 21.06.14.
 */
public class ProblemListErrorListener implements ANTLRErrorListener {
    List<Problem> problems = new ArrayList<>();

    @Override
    public void syntaxError(@NotNull Recognizer<?, ?> recognizer, @Nullable Object offendingSymbol, int line, int charPositionInLine, @NotNull String msg, @Nullable RecognitionException e) {
        Problem problem = new Problem();
        problem.level = Problem.ProblemLevel.ERROR;


        Token offendingToken = (Token) offendingSymbol;

        List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
        Collections.reverse(stack);
        String stack_str = stack.toString();
        problem.line = line;
        problem.charPositionInLine = charPositionInLine;
        problem.description = String.format("%s -- symbol %s | stack %s", msg, offendingSymbol, stack_str);
        problems.add(problem);
    }

    @Override
    public void reportAmbiguity(@NotNull Parser recognizer, @NotNull DFA dfa, int startIndex, int stopIndex, boolean exact, @Nullable BitSet ambigAlts, @NotNull ATNConfigSet configs) {

    }

    @Override
    public void reportAttemptingFullContext(@NotNull Parser recognizer, @NotNull DFA dfa, int startIndex, int stopIndex, @Nullable BitSet conflictingAlts, @NotNull ATNConfigSet configs) {

    }

    @Override
    public void reportContextSensitivity(@NotNull Parser recognizer, @NotNull DFA dfa, int startIndex, int stopIndex, int prediction, @NotNull ATNConfigSet configs) {

    }
}
