package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class Projection extends JFrame {
    private TerminalTransactionsDelegate delegate;

    public Projection(TerminalTransactionsDelegate delegate) {
        super("Projection");
        this.delegate = delegate;
        initializeUI();
    }

    private void initializeUI() {
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1));

        JLabel filterLabel = new JLabel("Projection Conditions:");

        JTextField userInputCond1 = new JTextFieldWithHint("Select", 10);
        JTextField userInputCond2 = new JTextFieldWithHint("From", 10);

        inputPanel.add(filterLabel);
        inputPanel.add(userInputCond1);
        inputPanel.add(userInputCond2);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton filterButton = new JButton("Projection");
        JButton backButton = new JButton("Back to Menu");

        buttonPanel.add(filterButton);
        buttonPanel.add(backButton);

        filterButton.addActionListener((e) -> {
            try {
                String userInput1 = userInputCond1.getText();
                String userInput2 = userInputCond2.getText();
                ArrayList<String> results = this.delegate.Projection(userInput1, userInput2);
                new DisplayResults(results, this.delegate).setVisible(true);
                this.dispose();
            } catch (NumberFormatException var7) {
                JOptionPane.showMessageDialog(this, "Please enter valid projection conditions.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener((e) -> {
            this.dispose();
            MenuPage menuPage = new MenuPage(this.delegate);
            menuPage.setSize(400, 300);
            menuPage.setVisible(true);
        });

        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    }
class JTextFieldWithHint extends JTextField {
    private String hint;

    public JTextFieldWithHint(String hint, int columns) {
        super(columns);
        this.hint = hint;
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(hint);
                }
            }
        });
    }}