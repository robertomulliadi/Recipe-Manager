package ca.ubc.cs304.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.RecipeUser;


public class DisplayRecipes extends JFrame {
    private TerminalTransactionsDelegate delegate;

    public DisplayRecipes(RecipeUser[] filteredRecipes, TerminalTransactionsDelegate delegate) {
        super("Recipes");
        this.delegate = delegate; // Save the delegate for later use
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String[] columnNames = {"Recipe ID", "Title", "Instructions", "Cooking Time", "Serving Size","UserID"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (RecipeUser recipe : filteredRecipes) {
            model.addRow(new Object[]{recipe.getRecipeID(), recipe.getTitle(),
                    recipe.getInstructions(), recipe.getCookingTime(),
                    recipe.getServingSize(),recipe.getUserID()});
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

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
