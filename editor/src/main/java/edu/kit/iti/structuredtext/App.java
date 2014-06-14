package edu.kit.iti.structuredtext;

import com.sun.java.swing.plaf.gtk.GTKLookAndFeel;
import edu.kit.iti.structuredtext.ui.MainFrame;
import edu.kit.iti.structuredtext.ui.STTokenMaker;
import edu.kit.iti.structuredtext.ui.StructuredTextFoldParser;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.folding.FoldParserManager;
import org.stringtemplate.v4.compiler.STLexer;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.File;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    UIManager.setLookAndFeel(new GTKLookAndFeel());
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }

                FoldParserManager.get().addFoldParserMapping("text/structuredtext", new StructuredTextFoldParser());

                AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
                atmf.putMapping("text/structuredtext", STTokenMaker.class.getName());

                MainFrame frame = new MainFrame();
                frame.openFile(new File("sample.st"));
                frame.setVisible(true);
            }
        });
    }
}
