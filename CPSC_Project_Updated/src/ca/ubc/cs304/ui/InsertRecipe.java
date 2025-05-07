package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.Recipe;
import ca.ubc.cs304.model.RecipeUser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InsertRecipe extends JFrame {

    private DefaultTableModel tableModel;
    private TerminalTransactionsDelegate delegate;
    JTextField RecipeID = new JTextField(20);
    JTextField title = new JTextField(20);
    JTextField instructions = new JTextField(20);
    JTextField cookingTime = new JTextField(20);
    JTextField servingSize = new JTextField(20);
    JTextField RecipeUser = new JTextField(20);
    public InsertRecipe(TerminalTransactionsDelegate delegate) {
        super("Insert Recipe");
        this.delegate = delegate;



        JPanel panel = new JPanel(new BorderLayout(20, 20));

        getContentPane().add(panel);

        setSize(400, 300);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Recipe ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Instructions");
        tableModel.addColumn("Cooking Time");
        tableModel.addColumn("Serving Size");
        tableModel.addColumn("User ID");

        RecipeUser[] recipeUsers = this.delegate.getRecipeUserInfo();

        for (RecipeUser recipeUser: recipeUsers) {
            Object[] rowData = {recipeUser.getRecipeID(), recipeUser.getTitle(), recipeUser.getInstructions(),
                    recipeUser.getCookingTime(), recipeUser.getServingSize(), recipeUser.getUserID()};
            tableModel.addRow(rowData);

        }


        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(scrollPane);

        JButton BackToMenu = new JButton("Back to Menu");

        JPanel inputPanel = new JPanel(new GridLayout(6, 2)); // Use GridLayout for compact arrangement
        inputPanel.add(new JLabel("RecipeID:"));
        inputPanel.add(RecipeID);
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(title);
        inputPanel.add(new JLabel("Instructions:"));
        inputPanel.add(instructions);
        inputPanel.add(new JLabel("Cooking Time:"));
        inputPanel.add(cookingTime);
        inputPanel.add(new JLabel("Serving Size:"));
        inputPanel.add(servingSize);
        inputPanel.add(new JLabel("User Id"));
        inputPanel.add(RecipeUser);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Create a button to add data
        JButton addButton = new JButton("Add Recipe");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    int recipeID = Integer.parseInt(RecipeID.getText());
                    String recipeTitle = title.getText();
                    String recipeInstructions = instructions.getText();
                    int recipeCookingTime = Integer.parseInt(cookingTime.getText());
                    int recipeServingSize = Integer.parseInt(servingSize.getText());
                    int recipeUserID = Integer.parseInt(RecipeUser.getText());

                    delegate.insertRecipeUser(new RecipeUser(recipeID, recipeTitle, recipeInstructions, recipeCookingTime, recipeServingSize, recipeUserID));
                    Object[] rowData = {recipeID, recipeTitle, recipeInstructions, recipeCookingTime, recipeServingSize, recipeUserID};
                    tableModel.addRow(rowData);

                    RecipeID.setText("");
                    title.setText("");
                    instructions.setText("");
                    cookingTime.setText("");
                    servingSize.setText("");
                    RecipeUser.setText("");
                    showSuccuess("recipeID: " + recipeID + " Added successfully");
                } catch (SQLException ex) {
                    showError("An unexpected SQL error occurred: " + ex.getMessage());
                } catch (Exception err) {
                    showError("Please enter valid numeric values for RecipeID, Cooking Time, and Serving Size.");
                }
            }
        });
        buttonPanel.add(addButton);

        panel.add(inputPanel, BorderLayout.NORTH); // Add text fields to the top

        panel.add(buttonPanel, BorderLayout.SOUTH);


        BackToMenu.setSize(10, 10);



        panel.add(BackToMenu, BorderLayout.EAST);

        BackToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                dispose();
                MenuPage menuPage = new MenuPage(delegate);
                menuPage.setSize(400,300);
                menuPage.setVisible(true);

            }
        });
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    private void showSuccuess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.PLAIN_MESSAGE);
    }

}
