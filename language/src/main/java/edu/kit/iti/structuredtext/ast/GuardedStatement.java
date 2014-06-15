package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;

import java.util.List;

/**
 * Created by weigla on 09.06.2014.
 */
public class GuardedStatement extends Statement {
    protected Expression condition;
    protected StatementList statements;

    public GuardedStatement() {

    }

    public GuardedStatement(Expression condition, StatementList statements) {
        this.condition = condition;
        this.statements = statements;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }


    public StatementList getStatements() {
        return statements;
    }

    public void setStatements(StatementList statements) {
        this.statements = statements;
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}