package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Division extends JFrame {

    private DefaultTableModel UserTable;
    private TerminalTransactionsDelegate delegate;
    JTextField username = new JTextField(20);
    JTextField password = new JTextField(20);
    JTextField preferences = new JTextField(20);
    JTextField email = new JTextField(20);
    public Division(TerminalTransactionsDelegate delegate) {
        super("Division");
        this.delegate = delegate;

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);

        UserTable = new DefaultTableModel();

        UserTable.addColumn("username");


        String[] username = this.delegate.division();
        for (String user : username) {

            System.out.println(user);
            Object[] rowData = {user};
            UserTable.addRow(rowData);
        }

        JTable uTable = new JTable(UserTable);
        JPanel tablesPanel = new JPanel(new GridLayout(1, 1, 20, 20)); // 1 row, 2 columns
        JScrollPane scrollPaneUser = new JScrollPane(uTable);
        tablesPanel.add(scrollPaneUser);
        panel.add(tablesPanel, BorderLayout.CENTER);
        JButton BackToMenu = new JButton("Back to Menu");


        BackToMenu.setSize(10, 10);
        panel.add(BackToMenu, BorderLayout.EAST);

        BackToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuPage menuPage = new MenuPage(delegate);
                menuPage.setSize(400, 300);
                menuPage.setVisible(true);
            }
        });







    }
}
