package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.ast.*;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigla on 15.06.2014.
 */
public class StructuredTextPrinter extends DefaultVisitor<Object> {
    private CodeWriter sb = new CodeWriter();
    private boolean printComments;

    public boolean isPrintComments() {
        return printComments;
    }

    public void setPrintComments(boolean printComments) {
        this.printComments = printComments;
    }

    @Override
    public Object defaultVisit(Visitable visitable) {
        throw new IllegalArgumentException("not implemented: " + visitable.getClass());
    }

    public String getString() {
        return sb.toString();
    }

    @Override
    public Object visit(ExitStatement exitStatement) {
        sb.append("EXIT;");
        return null;
    }

    @Override
    public Object visit(CaseConditions.IntegerCondition integerCondition) {
        sb.appendIdent();
        integerCondition.getValue().visit(this);
        return null;
    }

    @Override
    public Object visit(CaseConditions.Enumeration enumeration) {
        if (enumeration.getStart() == enumeration.getStop()) {
            sb.appendIdent();
            enumeration.getStart().visit(this);
        } else {
            sb.appendIdent();
            enumeration.getStart().visit(this);
            sb.append("..");
            enumeration.getStop().visit(this);
        }

        return null;
    }

    @Override
    public Object visit(BinaryExpression binaryExpression) {
        sb.append('(');
        binaryExpression.getLeftExpr().visit(this);
        sb.append(" ").append(binaryExpression.getOperator().token).append(" ");
        binaryExpression.getRightExpr().visit(this);
        sb.append(')');
        return null;
    }


    @Override
    public Object visit(AssignmentStatement assignStatement) {
        sb.nl();
        assignStatement.getVariable().visit(this);
        sb.append(" := ");
        assignStatement.getExpression().visit(this);
        sb.append(";");
        return null;
    }

    @Override
    public Object visit(ConfigurationDeclaration configurationDeclaration) {
        return null;
    }

    @Override
    public Object visit(DirectVariable directVariable) {
        return null;
    }

    @Override
    public Object visit(EnumerationTypeDeclaration enumerationTypeDeclaration) {
        sb.nl().append(enumerationTypeDeclaration.getTypeName()).append(" : ");

        sb.append("(");

        for (String s : enumerationTypeDeclaration.getAllowedValues())
            sb.append(s).append(" , ");

        sb.deleteLast(3);
        sb.append(");");

        return null;
    }

    @Override
    public Object visit(RepeatStatement repeatStatement) {
        sb.nl();
        sb.append("REPEAT").increaseIdent();
        repeatStatement.getStatements().visit(this);

        sb.decreaseIdent().nl().append("UNTIL ");
        repeatStatement.getCondition().visit(this);
        sb.append("END_REPEAT");
        return null;
    }

    @Override
    public Object visit(WhileStatement whileStatement) {
        sb.nl();
        sb.append("WHILE ");
        whileStatement.getCondition().visit(this);
        sb.append(" DO ").increaseIdent();
        whileStatement.getStatements().visit(this);
        sb.decreaseIdent().nl();
        sb.append("END_WHILE");
        return null;
    }

    @Override
    public Object visit(UnaryExpression unaryExpression) {
        sb.append(unaryExpression.getOperator().symbol).append(" ");
        unaryExpression.getExpression().visit(this);
        return null;
    }

    @Override
    public Object visit(TypeDeclarations typeDeclarations) {
        sb.append("TYPE").increaseIdent();
        for (TypeDeclaration decl : typeDeclarations) {
            decl.visit(this);
        }
        sb.decreaseIdent().nl().append("END_TYPE").nl().nl();
        return null;
    }

    @Override
    public Object visit(CaseStatement caseStatement) {

        sb.nl().append("CASE ");
        caseStatement.getExpression().visit(this);
        sb.append(" OF ");

        for(CaseStatement.Case c : caseStatement.getCases())
        {
            c.visit(this);
            sb.nl();
        }
        sb.nl().appendIdent().append("END_CASE");
        return null;
    }


    @Override
    public Object visit(SymbolicReference symbolicReference) {
        sb.append(symbolicReference.getIdentifier());

        if (symbolicReference.getSubscripts() != null && !symbolicReference.getSubscripts().isEmpty()) {
            sb.append('[');
            for (Expression expr : symbolicReference.getSubscripts()) {
                expr.visit(this);
                sb.append(',');
            }
            sb.append(']');
        }

        if (symbolicReference.getSub() != null) {
            sb.append(".");
            symbolicReference.getSub().visit(this);
        }

        return null;
    }

    @Override
    public Object visit(StatementList statements) {
        for (Statement stmt : statements) {
            if(stmt == null)
            {
                sb.append("{*ERROR: stmt null*}");
            }
            else {
                stmt.visit(this);
            }
        }
        return null;
    }

    @Override
    public Object visit(ProgramDeclaration programDeclaration) {
        sb.append("PROGRAM ").append(programDeclaration.getProgramName()).append('\n');

        programDeclaration.getScope().visit(this);

        programDeclaration.getProgramBody().visit(this);
        sb.decreaseIdent().nl().append("END_PROGRAM").nl();
        return null;
    }

    @Override
    public Object visit(ScalarValue<? extends Any, ?> tsScalarValue) {
        sb.append(tsScalarValue.getDataType().repr(tsScalarValue.getValue()));
        return null;
    }

    @Override
    public Object visit(ExpressionList expressions) {
        for (Expression e : expressions) {
            e.visit(this);
            sb.append(", ");
        }
        sb.deleteLast(2);
        return null;
    }

    @Override
    public Object visit(FunctionCall functionCall) {
        //TODO
        sb.append(functionCall.getFunctionName()).append("(").append(")");
        return null;
    }

    @Override
    public Object visit(CaseExpression caseExpression) {
        sb.append("CASES(").increaseIdent();
        for (CaseExpression.Case cas : caseExpression.getCases()) {
            cas.getCondition().visit(this);
            sb.append(" -> ");
            cas.getExpression().visit(this);
            sb.append(";").nl();
        }
        sb.append("ELSE -> ");
        caseExpression.getElseExpression().visit(this);
        sb.append(")").decreaseIdent();
        return null;
    }

    @Override
    public Object visit(ForStatement forStatement) {
        sb.nl();
        sb.append("FOR ").append(forStatement.getVariable());
        sb.append(" := ");
        forStatement.getStart().visit(this);
        sb.append(" TO ");
        forStatement.getStop().visit(this);
        sb.append(" DO ").increaseIdent();
        forStatement.getStatements().visit(this);
        sb.decreaseIdent().nl();
        sb.append("END_FOR");
        return null;
    }

    @Override
    public Object visit(FunctionBlockDeclaration functionBlockDeclaration) {
        sb.append("FUNCTION_BLOCK ").append(functionBlockDeclaration.getFunctionBlockName()).increaseIdent();

        functionBlockDeclaration.getScope().visit(this);

        sb.nl();

        functionBlockDeclaration.getFunctionBody().visit(this);
        sb.decreaseIdent().nl().append("END_FUNCTION_BLOCK;").nl().nl();
        return null;
    }

    @Override
    public Object visit(ReturnStatement returnStatement) {
        sb.appendIdent();
        sb.append("RETURN;");
        return null;
    }

    @Override
    public Object visit(FunctionBlockInvocation functionBlockInvocation) {
        //TODO
        sb.appendIdent();
        sb.append(functionBlockInvocation.getFunctionName()).append("()");
        return null;
    }

    @Override
    public Object visit(IfStatement ifStatement) {
        for (int i = 0; i < ifStatement.getConditionalBranches().size(); i++) {
            sb.nl();

            if (i == 0)
                sb.append("IF ");
            else
                sb.append("ELSEIF ");

            ifStatement.getConditionalBranches().get(i).getCondition().visit(this);

            sb.append(" THEN").increaseIdent();
            ifStatement.getConditionalBranches().get(i).getStatements().visit(this);
            sb.decreaseIdent();
        }

        if (ifStatement.getElseBranch().size() > 0) {
            sb.nl().append("ELSE").increaseIdent();
            ifStatement.getElseBranch().visit(this);
            sb.decreaseIdent();
        }
        sb.nl().append("END_IF;");
        return null;
    }

    @Override
    public Object visit(FunctionCallStatement functionCallStatement) {
        sb.nl();
        sb.append(functionCallStatement.getFunctionBlockCall().getFunctionName()).append("(");

        for (FunctionCall.Parameter entry : functionCallStatement.getFunctionBlockCall().getParameters()) {
            if (entry.getName() != null) {
                sb.append(entry.getName());
                if (entry.isOutput())
                    sb.append(" => ");
                else
                    sb.append(" := ");
            }

            entry.getExpression().visit(this);
            sb.append(", ");
        }

        sb.deleteLast(2);
        sb.append(");");
        return null;
    }

    @Override
    public Object visit(CaseStatement.Case aCase) {
        sb.nl();
        for (CaseConditions cc : aCase.getConditions()) {
            cc.visit(this);
            sb.append(", ");
        }
        sb.deleteLast(2);
        sb.append(":");
        sb.increaseIdent().increaseIdent();
        sb.increaseIdent().nl();
        aCase.getStatements().visit(this);
        sb.decreaseIdent().decreaseIdent().decreaseIdent();
        return null;
    }

    @Override
    public Object visit(SimpleTypeDeclaration simpleTypeDeclaration) {
        sb.append(simpleTypeDeclaration.getBaseTypeName());
        if (simpleTypeDeclaration.getInitializationValue() != null) {
            sb.append(" := ");
            simpleTypeDeclaration.getInitializationValue().visit(this);
        }
        return null;
    }

    @Override
    public Object visit(VariableScope variableScope) {
        for (VariableDeclaration vd : variableScope.getVariableMap().values()) {
            vd.getDataType();
            sb.nl().append("VAR");

            if (vd.isInput())
                sb.append("_INPUT");
            if (vd.isOutput())
                sb.append("_OUTPUT");
            if (vd.isInOut())
                sb.append("_INOUT");

            if (vd.isExternal())
                sb.append("_EXTERNAL");
            if (vd.isGlobal())
                sb.append("_GLOBAL");

            sb.append(" ");

            if (vd.isConstant())
                sb.append(" CONSTANT ");

            if (vd.isRetain())
                sb.append("RETAIN");
            else
                sb.append("NON_RETAIN");

            sb.append(" ");

            sb.append(vd.getName()).append(" : ");
            if (vd.getDeclaredType() != null)
                vd.getDeclaredType().visit(this);
            else
                sb.append(vd.getDataType());

            sb.append(" END_VAR");
        }
        return null;
    }

    @Override
    public Object visit(CommentStatement commentStatement){
        if(printComments) {
            sb.nl();
            sb.append("{*");
            sb.append(commentStatement.getComment());
            sb.append("*}");
        }
        return null;

    }

    public void clear() {
        sb = new CodeWriter();
    }
}
