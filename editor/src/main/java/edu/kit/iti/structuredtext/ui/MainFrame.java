package edu.kit.iti.structuredtext.ui;

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
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by weigl on 13.06.14.
 */
public class MainFrame extends JFrame {
    private JTabbedPane tabs = new JTabbedPane();

    public MainFrame() {



        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(tabs);
        setSize(600,600);
    }

    public void openFile(File file){
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


    public class EditorPane extends JSplitPane implements DocumentListener {
        private RSyntaxTextArea textArea = new RSyntaxTextArea();
        private JTree overview = new JTree();
        private RTextScrollPane scrollPane = new RTextScrollPane(textArea);

        public EditorPane() {
            super(HORIZONTAL_SPLIT);


            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DELPHI);
            textArea.setAntiAliasingEnabled(true);
            textArea.setCodeFoldingEnabled(true);
            textArea.setAnimateBracketMatching(false);
            textArea.setBracketMatchingEnabled(false);
            textArea.setCloseCurlyBraces(false);
            textArea.setSyntaxEditingStyle("text/structuredtext");



            textArea.addParser(new RSTAParser( this ));
            setLeftComponent(overview);
            setRightComponent(scrollPane);


            textArea.getDocument().addDocumentListener(this);
        }

        public void setText(String text) {
            textArea.setText(text);

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
