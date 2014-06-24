package edu.kit.iti.structuredtext.plcopenxml.model;

import edu.kit.iti.structuredtext.ast.StatementList;

/**
 * @author weigla
 * @date 24.06.2014
 */
public class SFCAction {
    String name;
    StatementList statements;

    public SFCAction() {}

    public SFCAction(String name, StatementList statements) {
        this.name = name;
        this.statements = statements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatementList getStatements() {
        return statements;
    }

    public void setStatements(StatementList statements) {
        this.statements = statements;
    }
}
