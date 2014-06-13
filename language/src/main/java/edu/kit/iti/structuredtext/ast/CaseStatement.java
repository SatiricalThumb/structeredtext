package edu.kit.iti.structuredtext.ast;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by weigla on 09.06.2014.
 */
public class CaseStatement extends Statement {
    private Expression expression;
    private List<Case> cases = new ArrayList<>();
    private StatementList elseCase = new StatementList();

    public void addCase(Case cs) {
        cases.add(cs);
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public static class Case extends Top {
        List<CaseConditions> conditions = new ArrayList<>();
        StatementList statements = new StatementList();

        public void setStatements(StatementList statements) {
            this.statements = statements;
        }

        public void addCondition(CaseConditions condition) {
            conditions.add(condition);
        }

        public List<CaseConditions> getConditions() {
            return conditions;
        }

        public void setConditions(List<CaseConditions> conditions) {
            this.conditions = conditions;
        }

        public StatementList getStatements() {
            return statements;
        }
    }


    public Expression getExpression() {
        return expression;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public StatementList getElseCase() {
        return elseCase;
    }

    public void setElseCase(StatementList elseCase) {
        this.elseCase = elseCase;
    }
}
