package edu.kit.iti.structuredtext.ui;

/**
 * Created by weigl on 21.06.14.
 */
public class Problem {
    String description;
    ProblemLevel level;
    public int line;
    public int charPositionInLine;

    public static enum ProblemLevel {
        ERROR, WARNING;
    }
}
