package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.RecipeUser;
import ca.ubc.cs304.model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteUser extends JFrame {

    private DefaultTableModel tableModel;
    private DefaultTableModel UserTable;
    private TerminalTransactionsDelegate delegate;
    JTextField RecipeID = new JTextField(20);
    JTextField title = new JTextField(20);
    JTextField instructions = new JTextField(20);
    JTextField cookingTime = new JTextField(20);
    JTextField servingSize = new JTextField(20);
    JTextField RecipeUser = new JTextField(20);

    private JPanel panel;

    public DeleteUser(TerminalTransactionsDelegate delegate) {

        super("Delete User");
        this.delegate = delegate;


        panel = new JPanel(new BorderLayout(20, 20));

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


        UserTable = new DefaultTableModel();
        UserTable.addColumn("UserID");
        UserTable.addColumn("Username");
        UserTable.addColumn("Password");
        UserTable.addColumn("Preferences");
        UserTable.addColumn("Email");


        RecipeUser[] recipeUsers = this.delegate.getRecipeUserInfo();

        for (RecipeUser recipeUser : recipeUsers) {
            Object[] rowData = {recipeUser.getRecipeID(), recipeUser.getTitle(), recipeUser.getInstructions(),
                    recipeUser.getCookingTime(), recipeUser.getServingSize(), recipeUser.getUserID()};
            tableModel.addRow(rowData);
        }

        User[] users = this.delegate.getUserInfo();

        for (User user : users) {
            Object[] rowData = {user.getUserID(), user.getUsername(), user.getPassword(),
                    user.getPreferences(), user.getEmail()};
            UserTable.addRow(rowData);
        }

        JTable uTable = new JTable(UserTable);
        JTable table = new JTable(tableModel);
        JPanel tablesPanel = new JPanel(new GridLayout(2, 1, 20, 20)); // 1 row, 2 columns

// Add the user table (uTable) on the left side
        JScrollPane scrollPaneUser = new JScrollPane(uTable);
        tablesPanel.add(scrollPaneUser);

// Add the recipe table (table) on the right side
        JScrollPane scrollPaneRecipe = new JScrollPane(table);
        tablesPanel.add(scrollPaneRecipe);
        panel.add(tablesPanel, BorderLayout.CENTER);
        JButton BackToMenu = new JButton("Back to Menu");


        uTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = uTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int option = JOptionPane.showConfirmDialog(DeleteUser.this,
                                "Are you sure you want to delete this User?",
                                "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            int userID = (int) uTable.getValueAt(selectedRow, 0);
                            try {
                                delegate.deleteRecipeUser(userID);
                                UserTable.removeRow(selectedRow);

                                for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                                    if ((int) tableModel.getValueAt(i, 5) == userID) { // Assuming 5 is the column index for userID
                                        tableModel.removeRow(i);
                                    }
                                }

                                showSuccuess("UserID: " + userID + " Deleted Successfully");

                            } catch (SQLException ex) {
                                showError("Failed to delete recipe: " + ex.getMessage());
                            }
                        }
                    }
                }
            }
        });





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



    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccuess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.PLAIN_MESSAGE);
    }

}
