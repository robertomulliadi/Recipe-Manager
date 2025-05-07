package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class DisplayResults extends JFrame {
    private TerminalTransactionsDelegate delegate;

    public DisplayResults(ArrayList<String> results, TerminalTransactionsDelegate delegate) {
        super("Results");
        this.delegate = delegate; // Save the delegate for later use
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        for (String result : results) {
            textArea.append(result + "\n");
        }

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back to Menu");
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
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
