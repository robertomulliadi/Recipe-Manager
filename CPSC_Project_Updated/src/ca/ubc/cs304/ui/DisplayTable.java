package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayTable extends JFrame{
    public DisplayTable(DefaultTableModel tableModel, TerminalTransactionsDelegate delegate, ArrayList<String> selection, String tableName) {
        super("Table Projection");
        tableModel = new DefaultTableModel();
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Table Result");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);

        for(String sel: selection) {
            tableModel.addColumn(sel);
        }

        String[] s = delegate.doProjection(selection, tableName);

        for (int j = 0; j < s.length/selection.size(); j++) {
            ArrayList<Object> a = new ArrayList<>();
            for (int i = 0; i < selection.size(); i++) {
                a.add(s[selection.size()*j + i]);

            }
            tableModel.addRow(a.toArray());
        }

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(scrollPane);

        JButton BackToMenu = new JButton("Back to Menu");
        panel.add(BackToMenu, BorderLayout.EAST);

        BackToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MenuPage menuPage = new MenuPage(delegate);
                menuPage.setSize(400,300);
                menuPage.setVisible(true);

            }
        });

        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.pack();
    }
}
