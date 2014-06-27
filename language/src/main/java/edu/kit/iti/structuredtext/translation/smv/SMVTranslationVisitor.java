package edu.kit.iti.structuredtext.translation.smv;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import edu.kit.iti.structuredtext.DefaultVisitor;
import edu.kit.iti.structuredtext.Visitable;
import edu.kit.iti.structuredtext.ast.AssignmentStatement;
import edu.kit.iti.structuredtext.ast.BinaryExpression;
import edu.kit.iti.structuredtext.ast.BinaryExpression.Operator;
import edu.kit.iti.structuredtext.ast.CaseExpression.Case;
import edu.kit.iti.structuredtext.ast.CaseExpression;
import edu.kit.iti.structuredtext.ast.Expression;
import edu.kit.iti.structuredtext.ast.ExpressionList;
import edu.kit.iti.structuredtext.ast.ProgramDeclaration;
import edu.kit.iti.structuredtext.ast.Statement;
import edu.kit.iti.structuredtext.ast.StatementList;
import edu.kit.iti.structuredtext.ast.SymbolicReference;
import edu.kit.iti.structuredtext.ast.UnaryExpression;
import edu.kit.iti.structuredtext.ast.VariableDeclaration;
import edu.kit.iti.structuredtext.ast.VariableScope;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;


public class SMVTranslationVisitor extends DefaultVisitor<String> {

    private final StringBuilder decl = new StringBuilder();
    private final StringBuilder assign = new StringBuilder();
    private final Map<String, List<String>> enumTypes = new HashMap<>();

    {
        // HACK .... parse enum types one day :-)
        enumTypes.put("STATE", Arrays.asList("S0", "S1", "S2", "S3"));
    }

    @Override
    public String defaultVisit(Visitable visitable) {
        throw new Error("not supported: " + visitable.getClass());
    }

    @Override
    public String visit(ProgramDeclaration pd) {
        pd.getScope().visit(this);
        pd.getProgramBody().visit(this);
        return null;
    }

    @Override
    public String visit(VariableScope variableScope) {
        for (VariableDeclaration vd : variableScope.getVariableMap().values()) {
            decl.append(" " + vd.getName() + " : ");
            String dataType = vd.getDeclaredType().getBaseTypeName();
            if(enumTypes.containsKey(dataType)) {
                decl.append(enumTypes.get(dataType).toString().replace('[', '{').replace(']', '}'));
                assign.append("  init(" + vd.getName() + ") := " + enumTypes.get(dataType).get(0) + ";\n\n");
            } else {
                switch(dataType) {
                case "BOOL":
                    decl.append("boolean");
                    assign.append("  init(" + vd.getName() + ") := FALSE;\n\n");
                    break;
                default:
                    throw new IllegalArgumentException("Type not supported: " + dataType);
                }
            }
            decl.append(";\n");
        }
        return null;
    }

    @Override
    public String visit(StatementList statements) {
        for (Statement statement : statements) {
            statement.visit(this);
        }
        return null;
    }

    @Override
    public String visit(AssignmentStatement assignmentStatement) {
        String var = ((SymbolicReference)assignmentStatement.getVariable()).getIdentifier();

        assign.append("  next(" + var + ") := ");
        assign.append(assignmentStatement.getExpression().visit(this));
        assign.append(";\n\n");
        return null;
    }

    @Override
    public String visit(BinaryExpression binaryExpression) {
        return "(" + binaryExpression.getLeftExpr().visit(this) + " "
                + binop2string(binaryExpression.getOperator()) + " "
                + binaryExpression.getRightExpr().visit(this) + ")";
    }

    private String binop2string(Operator operator) {
        switch (operator) {
        case GREATER_EQUALS:
            return ">=";

        case GREATER_THAN:
            return ">";

        case EQUALS:
            return "=";

        case ADD:
            return "+";

        case AND:
            return "&";

        default:
            return "[?" + operator + "]";
        }
    }

    @Override
    public String visit(UnaryExpression unaryExpression) {
        return unop2string(unaryExpression.getOperator()) +
                " " + unaryExpression.getExpression().visit(this);
    }

    private String unop2string(UnaryExpression.Operator operator) {
        switch(operator) {
        case MINUS : return "-";
        case NEGATE : return "!";
        default : return "??";
        }
    }

    @Override
    public String visit(CaseExpression caseExpression) {
        StringBuilder sb = new StringBuilder("case ");
        for (CaseExpression.Case cas : caseExpression.getCases()) {
            sb.append(cas.getCondition().visit(this));
            sb.append(" : ");
            sb.append(cas.getExpression().visit(this));
            sb.append("; ");
        }
        sb.append(" TRUE : ").append(caseExpression.getElseExpression().visit(this)).append("; esac");
        return sb.toString();
    }

    @Override
    public String visit(ExpressionList expressions) {
        StringBuilder sb = new StringBuilder();
        for (Expression expression : expressions) {
            if(sb.length() > 0)
                sb.append(", ");
            sb.append(expression.visit(this));
        }
        return sb.toString();
    }

    @Override
    public String visit(ScalarValue<? extends Any, ?> tsScalarValue) {
        // TODO
        String r1 = tsScalarValue.getDataType().repr(tsScalarValue.getValue());
        if(r1.contains("#")) {
            return tsScalarValue.getValue().toString();
        }
        return r1;
    }

    @Override
    public String visit(SymbolicReference symbolicReference) {
        assert symbolicReference.getSub() == null;
        assert symbolicReference.getSubscripts() == null;
        return symbolicReference.getIdentifier();
    }

    public String getModule() {
        return "MODULE main\nVAR\n" + decl + "\nASSIGN\n" + assign;
    }

}
