package edu.kit.iti.structuredtext.visitors;

import edu.kit.iti.structuredtext.antlr.StructuredTextBaseVisitor;
import edu.kit.iti.structuredtext.antlr.StructuredTextParser;
import edu.kit.iti.structuredtext.antlr.StructuredTextVisitor;
import edu.kit.iti.structuredtext.ast.Statement;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Created by weigla on 09.06.2014.
 */
public class StatementToAstVisitor extends StructuredTextBaseVisitor<Statement> {

}
