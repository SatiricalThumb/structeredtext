package edu.kit.iti.structuredtext.ast;

/**
 * Created by weigl on 11.06.14.
 */
public class AssignmentStatement extends Statement {
    private Reference variable;
    private Expression expression;

    public AssignmentStatement() {
    }

    public AssignmentStatement(Reference variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    public Reference getVariable() {
        return variable;
    }

    public void setVariable(SymbolicReference variable) {
        this.variable = variable;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
