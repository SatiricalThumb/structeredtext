package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weigla on 09.06.2014.
 */
public class IfStatement extends Statement {
    private List<GuardedStatement> conditionalBranches = new ArrayList<>();
    private StatementList elseBranch = new StatementList();


    public void addGuardedCommand(Expression expr, StatementList statements) {
        if (expr == null)
            throw new IllegalArgumentException();

        conditionalBranches.add(new GuardedStatement(expr, statements));
    }

    public List<GuardedStatement> getConditionalBranches() {
        return conditionalBranches;
    }

    public void setConditionalBranches(List<GuardedStatement> conditionalBranches) {
        this.conditionalBranches = conditionalBranches;
    }

    public StatementList getElseBranch() {
        return elseBranch;
    }

    public void setElseBranch(StatementList elseBranch) {
        this.elseBranch = elseBranch;
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
