package edu.kit.iti.structuredtext.ast;

import java.util.List;

/**
 * Created by weigla on 09.06.2014.
 */
public class GuardedStatement extends Statement {
    protected Expression condition;
    protected List<Statement> statements;

    public GuardedStatement() {

    }

    public GuardedStatement(Expression condition, List<Statement> statements) {
        this.condition = condition;
        this.statements = statements;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }


    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }
}