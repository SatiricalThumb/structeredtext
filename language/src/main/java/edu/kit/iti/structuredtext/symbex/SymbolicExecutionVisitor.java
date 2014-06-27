package edu.kit.iti.structuredtext.symbex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.kit.iti.structuredtext.DefaultVisitor;
import edu.kit.iti.structuredtext.StructuredTextPrinter;
import edu.kit.iti.structuredtext.Visitable;
import edu.kit.iti.structuredtext.ast.AssignmentStatement;
import edu.kit.iti.structuredtext.ast.BinaryExpression;
import edu.kit.iti.structuredtext.ast.BinaryExpression.Operator;
import edu.kit.iti.structuredtext.ast.CaseConditions;
import edu.kit.iti.structuredtext.ast.CaseConditions.Enumeration;
import edu.kit.iti.structuredtext.ast.CaseConditions.IntegerCondition;
import edu.kit.iti.structuredtext.ast.CaseExpression;
import edu.kit.iti.structuredtext.ast.CaseStatement;
import edu.kit.iti.structuredtext.ast.CaseStatement.Case;
import edu.kit.iti.structuredtext.ast.Expression;
import edu.kit.iti.structuredtext.ast.GuardedStatement;
import edu.kit.iti.structuredtext.ast.IfStatement;
import edu.kit.iti.structuredtext.ast.ProgramDeclaration;
import edu.kit.iti.structuredtext.ast.Reference;
import edu.kit.iti.structuredtext.ast.Statement;
import edu.kit.iti.structuredtext.ast.StatementList;
import edu.kit.iti.structuredtext.ast.SymbolicReference;
import edu.kit.iti.structuredtext.ast.UnaryExpression;

public class SymbolicExecutionVisitor extends DefaultVisitor<Object> {

    public static class SymbexState {

        private final Map<String, Expression> map = new HashMap<>();
        private final ReplaceVisitor replaceVisitor = new ReplaceVisitor(this);

        public StatementList toAssignments() {
            StatementList result = new StatementList();
            for (Map.Entry<String, Expression> en : map.entrySet()) {
                Reference key = new SymbolicReference(en.getKey(), new SymbolicReference("NEXT", null));
                result.add(new AssignmentStatement(key , en.getValue()));
            }
            return result;
        }

        public Expression eval(Expression expression) {
            return expression.visit(replaceVisitor);
        }

        public void update(String reference, Expression newExpr) {
            map.put(reference, newExpr);
        }

        public SymbexState copy() {
            SymbexState result = new SymbexState();
            result.map.putAll(map);
            return result;
        }

        public Collection<? extends String> getAssignedVars() {
            return map.keySet();
        }

        public Expression get(String var) {
            Expression lookup = map.get(var);
            if(lookup == null) {
                // TODO do this right
                return new SymbolicReference(var, null);
            } else {
                return lookup;
            }
        }

    }

    private SymbexState symbexState;

    @Override
    public Object defaultVisit(Visitable visitable) {
        throw new Error("Visiting not (yet?) supported: " + visitable.getClass());
    }

    @Override
    public Object visit(ProgramDeclaration pd) {

        ProgramDeclaration result = new ProgramDeclaration();
        result.setProgramName(pd.getProgramName());
        result.setScope(pd.getScope());
        result.setProgramBody((StatementList)pd.getProgramBody().visit(this));

        return result;
    }

    @Override
    public Object visit(StatementList statements) {

        symbexState = new SymbexState();
        for (Statement stm : statements) {
            stm.visit(this);
        }

        return symbexState.toAssignments();
    }

    @Override
    public Object visit(AssignmentStatement as) {
        Reference ref = as.getVariable();
        assert ref instanceof SymbolicReference : "No direct variables";
        SymbolicReference var = (SymbolicReference) ref;
        assert var.getSubscripts().isEmpty() : "No array subscripts";
        assert var.getSub() == null : "No sub references for now (to de done)";

        Expression newExpr = symbexState.eval(as.getExpression());
        symbexState.update(var.getIdentifier(), newExpr);
        return null;
    }

    private static String toString(Reference variable) {
        StructuredTextPrinter v = new StructuredTextPrinter();
        variable.visit(v);
        return v.getString();
    }

    @Override
    public Object visit(CaseStatement caseStatement) {

        SymbexState origState = symbexState.copy();
        Expression cmpExpr = caseStatement.getExpression();

        List<SymbexState> branchStates = new ArrayList<>();
        List<Expression> conditions = new ArrayList<>();
        for (Case cas : caseStatement.getCases()) {
            conditions.add(origState.eval(makeEQ(cmpExpr, cas.getConditions())));
            symbexState = origState.copy();
            cas.getStatements().visit(this);
            branchStates.add(symbexState);
        }

        symbexState = origState.copy();
        caseStatement.getElseCase().visit(this);
        SymbexState elseState = symbexState;

        symbexState = origState;
        Set<String> unionDomain = getUnionDomain(branchStates);
        for (String var : unionDomain) {
            if(differs(var, branchStates, elseState)) {
                CaseExpression expr = new CaseExpression();
                for (int i = 0; i < branchStates.size(); i++) {
                    Expression value = branchStates.get(i).get(var);
                    expr.addCase(conditions.get(i), value);
                }
                expr.setElseExpression(elseState.get(var));
                symbexState.update(var, expr);
            } else {
                // just take one of the (equal!) values
                symbexState.update(var, branchStates.get(0).get(var));
            }
        }

        return null;

    }

    private Expression makeEQ(Expression cmpExpr, List<CaseConditions> conditions) {
        Expression result = null;
        for (CaseConditions cond : conditions) {
            Expression val = (Expression) cond.visit(this);
            Expression eq = new BinaryExpression(cmpExpr, val, Operator.EQUALS);
            result = makeOR(result, eq);
        }
        return result;
    }

    @Override
    public Object visit(IntegerCondition integerCondition) {
        return integerCondition.getValue();
    }

    @Override
    public Object visit(Enumeration enumeration) {
        assert enumeration.getStart() == enumeration.getStop();
        return enumeration.getStart();
    }

    @Override
    public Object visit(IfStatement ifStatement) {

        SymbexState origState = symbexState.copy();
        List<SymbexState> branchStates = new ArrayList<>();
        List<Expression> conditions = new ArrayList<>();
        for (GuardedStatement stm : ifStatement.getConditionalBranches()) {
            conditions.add(origState.eval(stm.getCondition()));
            symbexState = origState.copy();
            stm.getStatements().visit(this);
            branchStates.add(symbexState);
        }

        symbexState = origState.copy();
        ifStatement.getElseBranch().visit(this);
        SymbexState elseState = symbexState;

        symbexState = origState;
        Set<String> unionDomain = getUnionDomain(branchStates);
        for (String var : unionDomain) {
            if(differs(var, branchStates, elseState)) {
                CaseExpression expr = new CaseExpression();
                Expression notExpr = null;
                for (int i = 0; i < branchStates.size(); i++) {
                    Expression cond = makeAND(notExpr, conditions.get(i));
                    Expression value = branchStates.get(i).get(var);
                    expr.addCase(cond, value);
                    notExpr = makeAND(notExpr, makeNOT(conditions.get(i)));
                }
                expr.setElseExpression(elseState.get(var));
                symbexState.update(var, expr);
            } else {
                // just take one of the (equal!) values
                symbexState.update(var, branchStates.get(0).get(var));
            }
        }

        return null;
    }

    private Expression makeNOT(Expression ex) {
        return new UnaryExpression(UnaryExpression.Operator.NEGATE, ex);
    }

    private static Expression makeAND(Expression a, Expression b) {
        if(a == null) {
            // TODO default case should be TRUE not null
            return b;
        } else {
            return new BinaryExpression(a, b, BinaryExpression.Operator.AND);
        }
    }

    private static Expression makeOR(Expression a, Expression b) {
        if(a == null) {
            // TODO default case should be FALSE not null
            return b;
        } else {
            return new BinaryExpression(a, b, BinaryExpression.Operator.OR);
        }
    }

    private static boolean differs(String var, List<SymbexState> states, SymbexState anotherState) {
        Expression expected = null;
        for (SymbexState state : states) {
            if(expected == null) {
                expected = state.get(var);
            } else {
                if(!expected.equals(state.get(var))) {
                    return true;
                }
            }
        }
        if(expected != null && !expected.equals(anotherState.get(var))) {
            return true;
        }
        return false;
    }

    private Set<String> getUnionDomain(Collection<SymbexState> states) {
        Set<String> result = new HashSet<>();
        for (SymbexState state : states) {
            result.addAll(state.getAssignedVars());
        }
        return result;
    }


}
