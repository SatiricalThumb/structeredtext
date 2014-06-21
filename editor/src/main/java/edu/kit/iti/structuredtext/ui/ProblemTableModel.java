package edu.kit.iti.structuredtext.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weigl on 21.06.14.
 */
public class ProblemTableModel extends AbstractTableModel implements TableCellRenderer {
    TableCellRenderer tcr = new DefaultTableCellRenderer();

    List<Problem> problems = new ArrayList<>();

    private static String[] HEADERS = {
            "LEVEL", "MESSAGE", "Line"
    };

    public ProblemTableModel() {
        Problem p = new Problem();
        p.line = 1;
        p.charPositionInLine = 2;
        p.level = Problem.ProblemLevel.ERROR;
        p.description = "Test";


        problems.add(p);
    }

    @Override
    public int getRowCount() {
        return problems.size();
    }

    @Override
    public int getColumnCount() {
        return HEADERS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Problem p = problems.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.level;
            case 1:
                return p.description;
            case 2:
                return p.line + ":" + p.charPositionInLine;
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return HEADERS[column];
    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        DefaultTableCellRenderer lbl = (DefaultTableCellRenderer) tcr.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Problem problem = problems.get(row);

        lbl.setForeground(Color.BLACK);

        if(problem.level == Problem.ProblemLevel.ERROR){
            lbl.setForeground(Color.RED);
        }

        if(problem.level == Problem.ProblemLevel.WARNING) {
            lbl.setForeground(Color.orange.darker().darker().darker());
        }

        return lbl;
    }
}
