package edu.kit.iti.structuredtext.ast;

import java.util.ArrayList;
import java.util.List;

import edu.kit.iti.structuredtext.ValueFactory;
import edu.kit.iti.structuredtext.Visitor;

public class CaseExpression extends Expression {

    public static class Case {
        private final Expression condition;
        private final Expression expression;

        public Case(Expression condition, Expression value) {
            this.condition = condition;
            this.expression = value;
        }

        public Expression getCondition() {
            return condition;
        }

        public Expression getExpression() {
            return expression;
        }
    }

    private final List<Case> cases = new ArrayList<>();
    private Expression elseExpression = ValueFactory.makeBool("FALSE");

    public static CaseExpression makeIfThenElse(Expression cond, Expression thenExpr, Expression elseExpr) {
        CaseExpression result = new CaseExpression();
        result.addCase(cond, thenExpr);
        result.setElseExpression(elseExpr);
        return result;
    }

    public void addCase(Expression cond, Expression value) {
        cases.add(new Case(cond, value));
    }

    public Expression getElseExpression() {
        return elseExpression;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setElseExpression(Expression elseValue) {
        this.elseExpression = elseValue;
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
        return visitor.visit(this);
    }

}
