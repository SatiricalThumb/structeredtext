package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.ast.*;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 21.06.14.
 */
public class DefaultVisitor<T> implements Visitor<T> {
    @Override
    public T visit(ArrayInitialization initializations) {
        return null;
    }

    @Override
    public T visit(ArrayTypeDeclaration arrayTypeDeclaration) {
        return null;
    }

    @Override
    public T visit(AssignmentStatement assignmentStatement) {
        return null;
    }

    @Override
    public T visit(ExitStatement exitStatement) {
        return null;
    }

    @Override
    public T visit(CaseConditions.Range range) {
        return null;
    }

    @Override
    public T visit(CaseConditions.IntegerCondition integerCondition) {
        return null;
    }

    @Override
    public T visit(CaseConditions.Enumeration enumeration) {
        return null;
    }

    @Override
    public T visit(BinaryExpression binaryExpression) {
        return null;
    }

    @Override
    public T visit(ConfigurationDeclaration configurationDeclaration) {
        return null;
    }

    @Override
    public T visit(DirectVariable directVariable) {
        return null;
    }

    @Override
    public T visit(EnumerationTypeDeclaration enumerationTypeDeclaration) {
        return null;
    }

    @Override
    public T visit(RepeatStatement repeatStatement) {
        return null;
    }

    @Override
    public T visit(WhileStatement whileStatement) {
        return null;
    }

    @Override
    public T visit(UnaryExpression unaryExpression) {
        return null;
    }

    @Override
    public T visit(TypeDeclarations typeDeclarations) {
        return null;
    }

    @Override
    public T visit(CaseStatement caseStatement) {
        return null;
    }

    @Override
    public T visit(Constant constant) {
        return null;
    }

    @Override
    public T visit(SymbolicReference symbolicReference) {
        return null;
    }

    @Override
    public T visit(Reference reference) {
        return null;
    }

    @Override
    public T visit(StatementList statements) {
        return null;
    }

    @Override
    public T visit(ProgramDeclaration programDeclaration) {
        return null;
    }

    @Override
    public T visit(ScalarValue<? extends Any, ?> tsScalarValue) {
        return null;
    }

    @Override
    public T visit(Literal literal) {
        return null;
    }

    @Override
    public T visit(ExpressionList expressions) {
        return null;
    }

    @Override
    public T visit(FunctionDeclaration functionDeclaration) {
        return null;
    }

    @Override
    public T visit(FunctionCall functionCall) {
        return null;
    }

    @Override
    public T visit(ForStatement forStatement) {
        return null;
    }

    @Override
    public T visit(ResourceDeclaration resourceDeclaration) {
        return null;
    }

    @Override
    public T visit(FunctionBlockDeclaration functionBlockDeclaration) {
        return null;
    }

    @Override
    public T visit(ReturnStatement returnStatement) {
        return null;
    }

    @Override
    public T visit(FunctionBlockInvocation functionBlockInvocation) {
        return null;
    }

    @Override
    public T visit(IfStatement ifStatement) {
        return null;
    }

    @Override
    public T visit(GuardedStatement guardedStatement) {
        return null;
    }

    @Override
    public T visit(FunctionCallStatement functionCallStatement) {
        return null;
    }

    @Override
    public T visit(CaseStatement.Case aCase) {
        return null;
    }

    @Override
    public T visit(StringTypeDeclaration stringTypeDeclaration) {
        return null;
    }

    @Override
    public T visit(StructureTypeDeclaration structureTypeDeclaration) {
        return null;
    }

    @Override
    public T visit(SubRangeDataType subRangeDataType) {
        return null;
    }

    @Override
    public T visit(SimpleTypeDeclaration simpleTypeDeclaration) {
        return null;
    }

    @Override
    public T visit(VariableScope variableScope) {
        return null;
    }

    @Override
    public T visit(VariableDeclaration variableDeclaration) {
        return null;
    }

    @Override
    public T visit(TopLevelElement topLevelElement) {
        return null;
    }
}
