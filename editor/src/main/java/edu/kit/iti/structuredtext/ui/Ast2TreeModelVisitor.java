package edu.kit.iti.structuredtext.ui;

import edu.kit.iti.structuredtext.DefaultVisitor;
import edu.kit.iti.structuredtext.Visitor;
import edu.kit.iti.structuredtext.ast.*;
import edu.kit.iti.structuredtext.datatypes.Any;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

/**
 * Created by weigl on 21.06.14.
 */
public class Ast2TreeModelVisitor extends DefaultVisitor<MutableTreeNode>
{
    @Override
    public MutableTreeNode visit(AssignmentStatement assignmentStatement) {
        return new DefaultMutableTreeNode("ASSIGN");
    }

    @Override
    public MutableTreeNode visit(ExitStatement exitStatement) {
        return new DefaultMutableTreeNode("EXIT");
    }


    @Override
    public MutableTreeNode visit(ConfigurationDeclaration configurationDeclaration) {
        return new DefaultMutableTreeNode("CONFIGURATION");
    }


    @Override
    public MutableTreeNode visit(EnumerationTypeDeclaration enumerationTypeDeclaration) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("ENUM: " + enumerationTypeDeclaration.getTypeName());

        for(String s: enumerationTypeDeclaration.getAllowedValues())
            node.add(new DefaultMutableTreeNode(s));

        return node;
    }

    @Override
    public MutableTreeNode visit(RepeatStatement repeatStatement) {
        return new DefaultMutableTreeNode("REPEAT");
    }

    @Override
    public MutableTreeNode visit(WhileStatement whileStatement) {
        return new DefaultMutableTreeNode("WHILE");
    }

    @Override
    public MutableTreeNode visit(TypeDeclarations typeDeclarations) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("TYPES");

        for(TypeDeclaration<String> td : typeDeclarations) {
            node.add(td.<MutableTreeNode>visit(this));
        }
        return node;
    }


    @Override
    public MutableTreeNode visit(StatementList statements)
    {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Statements");
        for(Statement s : statements) {
            node.add(s.visit(this));
        }
        return node;
    }

    @Override
    public MutableTreeNode visit(ProgramDeclaration programDeclaration) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(programDeclaration.getProgramName());
        node.add(programDeclaration.getScope().visit(this));
        node.add(programDeclaration.getProgramBody().visit(this));
        return node;
    }


    @Override
    public MutableTreeNode visit(FunctionDeclaration functionDeclaration) {
        return new DefaultMutableTreeNode("declare " + functionDeclaration.getFunctionName());
    }

    @Override
    public MutableTreeNode visit(ForStatement forStatement) {
        return new DefaultMutableTreeNode("FOR");
    }

    @Override
    public MutableTreeNode visit(FunctionBlockDeclaration functionBlockDeclaration) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(functionBlockDeclaration.getFunctionBlockName());

        node.add(functionBlockDeclaration.getScope().visit(this));
        node.add(functionBlockDeclaration.getFunctionBody().visit(this));

        return node;
    }

    @Override
    public MutableTreeNode visit(ReturnStatement returnStatement) {
        return new DefaultMutableTreeNode("RETURN");
    }

    @Override
    public MutableTreeNode visit(FunctionBlockInvocation functionBlockInvocation) {
        return null;
    }

    @Override
    public MutableTreeNode visit(IfStatement ifStatement) {
        return new DefaultMutableTreeNode("IF");
    }

    @Override
    public MutableTreeNode visit(FunctionCallStatement functionCallStatement) {
        return new DefaultMutableTreeNode(functionCallStatement.getFunctionBlockCall().getFunctionName()+"()");
    }

    @Override
    public MutableTreeNode visit(StringTypeDeclaration stringTypeDeclaration) {
        return null;
    }

    @Override
    public MutableTreeNode visit(StructureTypeDeclaration structureTypeDeclaration) {
        return null;
    }

    @Override
    public MutableTreeNode visit(SubRangeDataType subRangeDataType) {
        return null;
    }

    @Override
    public MutableTreeNode visit(SimpleTypeDeclaration simpleTypeDeclaration) {
        return null;
    }

    @Override
    public MutableTreeNode visit(VariableScope variableScope) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Variables");
        for (VariableDeclaration vd : variableScope.getVariableMap().values()){
            node.add(vd.visit(this));
        }
        return node;
    }

    @Override
    public MutableTreeNode visit(VariableDeclaration variableDeclaration) {

        DefaultMutableTreeNode node = new DefaultMutableTreeNode(variableDeclaration.getName() + " : "+variableDeclaration.getDataType() +" :=" +variableDeclaration.getInit());
        return  node;
    }
}
