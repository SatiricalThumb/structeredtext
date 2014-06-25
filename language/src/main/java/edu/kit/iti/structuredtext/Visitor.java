package edu.kit.iti.structuredtext;

import edu.kit.iti.structuredtext.ast.*;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

import javax.swing.tree.MutableTreeNode;

/**
 * Created by weigla on 14.06.2014.
 */
public interface Visitor<T> {
    T visit(ArrayInitialization initializations);

    T visit(ArrayTypeDeclaration arrayTypeDeclaration);

    T visit(AssignmentStatement assignmentStatement);

    T visit(ExitStatement exitStatement);

    T visit(CaseConditions.Range range);

    T visit(CaseConditions.IntegerCondition integerCondition);

    T visit(CaseConditions.Enumeration enumeration);

    T visit(BinaryExpression binaryExpression);

    T visit(ConfigurationDeclaration configurationDeclaration);

    T visit(DirectVariable directVariable);

    T visit(EnumerationTypeDeclaration enumerationTypeDeclaration);

    T visit(RepeatStatement repeatStatement);

    T visit(WhileStatement whileStatement);

    T visit(UnaryExpression unaryExpression);

    T visit(TypeDeclarations typeDeclarations);

    T visit(CaseStatement caseStatement);

    T visit(Constant constant);

    T visit(SymbolicReference symbolicReference);

    T visit(Reference reference);

    //T visit(Top top);

    T visit(StatementList statements);

    T visit(ProgramDeclaration programDeclaration);

    T visit(ScalarValue<? extends Any, ? extends Object> tsScalarValue);

    T visit(Literal literal);

    T visit(ExpressionList expressions);

    T visit(FunctionDeclaration functionDeclaration);

    T visit(FunctionCall functionCall);

    T visit(ForStatement forStatement);

    T visit(ResourceDeclaration resourceDeclaration);

    T visit(FunctionBlockDeclaration functionBlockDeclaration);

    T visit(ReturnStatement returnStatement);

    T visit(FunctionBlockInvocation functionBlockInvocation);

    T visit(IfStatement ifStatement);

    T visit(GuardedStatement guardedStatement);

    T visit(FunctionCallStatement functionCallStatement);

    T visit(CaseStatement.Case aCase);

    T visit(StringTypeDeclaration stringTypeDeclaration);

    T visit(StructureTypeDeclaration structureTypeDeclaration);

    T visit(SubRangeDataType subRangeDataType);

    T visit(SimpleTypeDeclaration simpleTypeDeclaration);

    T visit(VariableScope variableScope);

    T visit(VariableDeclaration variableDeclaration);

    T visit(TopLevelElement topLevelElement);

    T visit(CommentStatement commentStatement);

    T visit(CaseExpression caseExpression);
}
