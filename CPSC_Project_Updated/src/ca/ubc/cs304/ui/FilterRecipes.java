package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.RecipeUser;

public class FilterRecipes extends JFrame {
    private TerminalTransactionsDelegate delegate;

    public FilterRecipes(TerminalTransactionsDelegate delegate) {
        super("Filter Recipes");
        this.delegate = delegate;
        initializeUI();
    }

    private void initializeUI() {
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2)); // Adjust grid layout as needed

        JLabel Filter = new JLabel("Filter Conditions:");
        JTextField UserInputCond = new JTextField(10);

        inputPanel.add(Filter);
        inputPanel.add(UserInputCond);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton filterButton = new JButton("Filter");
        JButton backButton = new JButton("Back to Menu");

        buttonPanel.add(filterButton);
        buttonPanel.add(backButton);

        // Add action listener to filter button
        filterButton.addActionListener(e -> {
            try {
                String UserInput = UserInputCond.getText();
                RecipeUser[] filteredRecipes = delegate.filterRecipe(UserInput);
                new DisplayRecipes(filteredRecipes, delegate).setVisible(true);
                FilterRecipes.this.dispose(); // Close the filter window after showing the results
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(FilterRecipes.this,
                        "Please enter a valid filter conditions.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                dispose();
                MenuPage menuPage = new MenuPage(delegate);
                menuPage.setSize(400,300);
                menuPage.setVisible(true);

            }
        });

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

}