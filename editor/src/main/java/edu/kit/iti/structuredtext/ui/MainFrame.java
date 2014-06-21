package edu.kit.iti.structuredtext.ui;

import edu.kit.iti.structuredtext.ast.TopLevelElement;
import org.apache.commons.io.FileUtils;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.folding.FoldParserManager;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by weigl on 13.06.14.
 */
public class MainFrame extends JFrame {
    private JTabbedPane tabs = new JTabbedPane();



    public MainFrame() {


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(tabs);
        setSize(600, 600);
    }

    public void openFile(File file) {
        EditorPane pane = openTab(file.getName());

        String content = null;
        try {
            content = FileUtils.readFileToString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setText(content);
    }

    private EditorPane openTab(String name) {
        EditorPane pane = new EditorPane();
        tabs.add(name, pane);
        return pane;
    }


    public class EditorPane extends JPanel implements DocumentListener {
        private final JTable tblProblems = new JTable();
        private ProblemTableModel model;

        private RSyntaxTextArea textArea = new RSyntaxTextArea();
        private JTree overview = new JTree();
        private RTextScrollPane scrollPane = new RTextScrollPane(textArea);

        private JSplitPane paneH = new JSplitPane(SwingConstants.VERTICAL);
        private JSplitPane paneV = new JSplitPane(SwingConstants.HORIZONTAL);

        public EditorPane() {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DELPHI);
            textArea.setAntiAliasingEnabled(true);
            textArea.setCodeFoldingEnabled(true);
            textArea.setAnimateBracketMatching(false);
            textArea.setBracketMatchingEnabled(false);
            textArea.setCloseCurlyBraces(false);
            textArea.setSyntaxEditingStyle("text/structuredtext");

            textArea.addParser(new RSTAParser(this));

            this.model = new ProblemTableModel();
            tblProblems.setModel(model);
            tblProblems.setDefaultRenderer(String.class, model);
            tblProblems.setDefaultRenderer(Object.class, model);

            paneH.setLeftComponent(overview);
            paneH.setRightComponent(scrollPane);

            textArea.getDocument().addDocumentListener(this);

            paneV.setLeftComponent(paneH);
            paneV.setRightComponent(new JScrollPane(tblProblems));

            setLayout(new BorderLayout());
            add(paneV);
        }

        public void setText(String text) {
            textArea.setText(text);

        }

        public void updateProblemsTable(List<Problem> problems) 
        {
            model.problems = problems;
            model.fireTableDataChanged();
        }

        public void updateStructure(List<TopLevelElement> topLevelElements)
        {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(getName());
            DefaultTreeModel dtm = new DefaultTreeModel(root);

            Ast2TreeModelVisitor v = new Ast2TreeModelVisitor();

            for(TopLevelElement e : topLevelElements)
                root.add(e.visit(v));

            this.overview.setModel(dtm);

        }

        @Override
        public void insertUpdate(DocumentEvent e) {

        }

        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }
}
