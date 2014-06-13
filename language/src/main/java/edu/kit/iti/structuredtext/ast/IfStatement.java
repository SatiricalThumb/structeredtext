package edu.kit.iti.structuredtext.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weigla on 09.06.2014.
 */
public class IfStatement extends Statement {
    private List<GuardedStatement> conditionalBranches = new ArrayList<>();
    private StatementList elseBranch = new StatementList();


    public void addGuardedCommand(Expression ast, StatementList ast1) {
        conditionalBranches.add(new GuardedStatement(ast,ast1));
    }

    public void setElseBranch(StatementList elseBranch) {
        this.elseBranch = elseBranch;
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
}