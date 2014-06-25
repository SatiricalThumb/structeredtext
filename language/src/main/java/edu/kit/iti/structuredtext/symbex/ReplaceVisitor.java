package edu.kit.iti.structuredtext.symbex;

import edu.kit.iti.structuredtext.DefaultVisitor;
import edu.kit.iti.structuredtext.Visitable;
import edu.kit.iti.structuredtext.ast.BinaryExpression;
import edu.kit.iti.structuredtext.ast.CaseExpression;
import edu.kit.iti.structuredtext.ast.Expression;
import edu.kit.iti.structuredtext.ast.ExpressionList;
import edu.kit.iti.structuredtext.ast.SymbolicReference;
import edu.kit.iti.structuredtext.ast.UnaryExpression;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;
import edu.kit.iti.structuredtext.symbex.SymbolicExecutionVisitor.SymbexState;

public class ReplaceVisitor extends DefaultVisitor<Expression> {

    private final SymbexState state;

    public ReplaceVisitor(SymbexState state) {
        this.state = state;
    }

    @Override
    public Expression defaultVisit(Visitable visitable) {
        throw new Error("Visiting not (yet?) supported: " + visitable.getClass());
    }

    @Override
    public Expression visit(BinaryExpression binaryExpression) {
        Expression left = binaryExpression.getLeftExpr().visit(this);
        Expression right = binaryExpression.getRightExpr().visit(this);
        return new BinaryExpression(left, right, binaryExpression.getOperator());
    }

    @Override
    public Expression visit(UnaryExpression unaryExpression) {
        Expression left = unaryExpression.getExpression().visit(this);
        return new UnaryExpression(unaryExpression.getOperator(), left);
    }

    @Override
    public Expression visit(CaseExpression caseExpression) {
        CaseExpression result = new CaseExpression();
        for (CaseExpression.Case cas : caseExpression.getCases()) {
            Expression cond = cas.getCondition().visit(this);
            Expression val = cas.getExpression().visit(this);
            result.addCase(cond, val);
        }
        result.setElseExpression(caseExpression.getElseExpression().visit(this));
        return result;
    }

    @Override
    public Expression visit(ExpressionList expressions) {
        ExpressionList result = new ExpressionList();
        for (Expression exp : expressions) {
            result.add(exp.visit(this));
        }
        return result;
    }

    @Override
    public Expression visit(SymbolicReference sr) {
        assert sr.getSub() == null;
        String var = sr.getIdentifier();
        return state.get(var);
    }

    @Override
    public Expression visit(ScalarValue<? extends Any, ?> tsScalarValue) {
        return tsScalarValue;
    }
}
