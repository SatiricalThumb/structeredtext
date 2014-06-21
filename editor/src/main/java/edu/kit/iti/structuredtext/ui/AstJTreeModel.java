package edu.kit.iti.structuredtext.ui;

import edu.kit.iti.structuredtext.antlr.StructuredTextParser;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

/**
 * Created by weigl on 13.06.14.
 */
public class AstJTreeModel extends DefaultTreeModel {
    public AstJTreeModel() {
        super(null, true);
    }
}
