package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.ast.*;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

/**
 * Created by weigl on 21.06.14.
 */
public class DefaultVisitor<T> implements Visitor<T> {

    public T defaultVisit(Visitable visitable) {
        return visitable.visit(this);
    }

    @Override
    public T visit(ArrayInitialization initializations) {
        return defaultVisit(initializations);
    }

    @Override
    public T visit(ArrayTypeDeclaration arrayTypeDeclaration) {
        return defaultVisit(arrayTypeDeclaration);
    }

    @Override
    public T visit(AssignmentStatement assignmentStatement) {
        return defaultVisit(assignmentStatement);
    }

    @Override
    public T visit(ExitStatement exitStatement) {
        return defaultVisit(exitStatement);
    }

    @Override
    public T visit(CaseConditions.Range range) {
        return defaultVisit(range);
    }

    @Override
    public T visit(CaseConditions.IntegerCondition integerCondition) {
        return defaultVisit(integerCondition);
    }

    @Override
    public T visit(CaseConditions.Enumeration enumeration) {
        return defaultVisit(enumeration);
    }

    @Override
    public T visit(BinaryExpression binaryExpression) {
        return defaultVisit(binaryExpression);
    }

    @Override
    public T visit(ConfigurationDeclaration configurationDeclaration) {
        return defaultVisit(configurationDeclaration);
    }

    @Override
    public T visit(DirectVariable directVariable) {
        return defaultVisit(directVariable);
    }

    @Override
    public T visit(EnumerationTypeDeclaration enumerationTypeDeclaration) {
        return defaultVisit(enumerationTypeDeclaration);
    }

    @Override
    public T visit(RepeatStatement repeatStatement) {
        return defaultVisit(repeatStatement);
    }

    @Override
    public T visit(WhileStatement whileStatement) {
        return defaultVisit(whileStatement);
    }

    @Override
    public T visit(UnaryExpression unaryExpression) {
        return defaultVisit(unaryExpression);
    }

    @Override
    public T visit(TypeDeclarations typeDeclarations) {
        return defaultVisit(typeDeclarations);
    }

    @Override
    public T visit(CaseStatement caseStatement) {
        return defaultVisit(caseStatement);
    }

    @Override
    public T visit(Constant constant) {
        return defaultVisit(constant);
    }

    @Override
    public T visit(SymbolicReference symbolicReference) {
        return defaultVisit(symbolicReference);
    }

    @Override
    public T visit(Reference reference) {
        return defaultVisit(reference);
    }

    @Override
    public T visit(StatementList statements) {
        return defaultVisit(statements);
    }

    @Override
    public T visit(ProgramDeclaration programDeclaration) {
        return defaultVisit(programDeclaration);
    }

    @Override
    public T visit(ScalarValue<? extends Any, ?> tsScalarValue) {
        return defaultVisit(tsScalarValue);
    }

    @Override
    public T visit(Literal literal) {
        return defaultVisit(literal);
    }

    @Override
    public T visit(ExpressionList expressions) {
        return defaultVisit(expressions);
    }

    @Override
    public T visit(FunctionDeclaration functionDeclaration) {
        return defaultVisit(functionDeclaration);
    }

    @Override
    public T visit(FunctionCall functionCall) {
        return defaultVisit(functionCall);
    }

    @Override
    public T visit(ForStatement forStatement) {
        return defaultVisit(forStatement);
    }

    @Override
    public T visit(ResourceDeclaration resourceDeclaration) {
        return defaultVisit(resourceDeclaration);
    }

    @Override
    public T visit(FunctionBlockDeclaration functionBlockDeclaration) {
        return defaultVisit(functionBlockDeclaration);
    }

    @Override
    public T visit(ReturnStatement returnStatement) {
        return defaultVisit(returnStatement);
    }

    @Override
    public T visit(FunctionBlockInvocation functionBlockInvocation) {
        return defaultVisit(functionBlockInvocation);
    }

    @Override
    public T visit(IfStatement ifStatement) {
        return defaultVisit(ifStatement);
    }

    @Override
    public T visit(GuardedStatement guardedStatement) {
        return defaultVisit(guardedStatement);
    }

    @Override
    public T visit(FunctionCallStatement functionCallStatement) {
        return defaultVisit(functionCallStatement);
    }

    @Override
    public T visit(CaseStatement.Case aCase) {
        return defaultVisit(aCase);
    }

    @Override
    public T visit(StringTypeDeclaration stringTypeDeclaration) {
        return defaultVisit(stringTypeDeclaration);
    }

    @Override
    public T visit(StructureTypeDeclaration structureTypeDeclaration) {
        return defaultVisit(structureTypeDeclaration);
    }

    @Override
    public T visit(SubRangeDataType subRangeDataType) {
        return defaultVisit(subRangeDataType);
    }

    @Override
    public T visit(SimpleTypeDeclaration simpleTypeDeclaration) {
        return defaultVisit(simpleTypeDeclaration);
    }

    @Override
    public T visit(VariableScope variableScope) {
        return defaultVisit(variableScope);
    }

    @Override
    public T visit(VariableDeclaration variableDeclaration) {
        return defaultVisit(variableDeclaration);
    }

    @Override
    public T visit(TopLevelElement topLevelElement) {
        return defaultVisit(topLevelElement);
    }

    @Override
    public T visit(CommentStatement commentStatement) {
        return defaultVisit(commentStatement);
    }
}
