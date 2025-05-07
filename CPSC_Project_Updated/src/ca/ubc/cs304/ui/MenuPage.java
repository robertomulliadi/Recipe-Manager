package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MenuPage extends JFrame {


    private TerminalTransactionsDelegate delegate;


    public MenuPage(TerminalTransactionsDelegate delegate) {

        super("Menu Page");

        this.delegate = delegate;


        JPanel panel = new JPanel();

        JButton insert = new JButton("Insert Recipe");
        JButton delete = new JButton("Delete User");
        JButton update = new JButton("Update User");
        JButton filter = new JButton("Filter Recipes");
        JButton createdby = new JButton("Recipe Created by");
        JButton Projection = new JButton("Random Projection");
        JButton viewUsersBtn = new JButton("View Users with >2 Recipes");
        JButton viewTotalRecipesPerUserBtn = new JButton("View Total Recipes Per User");
        JButton division = new JButton("Division");
        JButton BProjection = new JButton("Projection");
        JButton showAboveAverageUsersBtn = new JButton("Show Users Above Average Recipes");

        getContentPane().add(panel);
        panel.add(insert);
        panel.add(delete);
        panel.add(update);
        panel.add(filter);
        panel.add(createdby);
        panel.add(Projection);
        panel.add(viewUsersBtn);
        panel.add(viewTotalRecipesPerUserBtn);
        panel.add(division);
        panel.add(BProjection);
        panel.add(showAboveAverageUsersBtn);

        insert.addActionListener(e -> {
            try {
                openInsertPage(delegate);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        delete.addActionListener(e -> {
            try {
                openDeletePage(delegate);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        update.addActionListener(e -> {
            try {
                openUpdatePage(delegate);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        filter.addActionListener(e -> {
            openFilterPage(delegate);
            dispose();
        });

        createdby.addActionListener(e -> {
            openCreatedbyPage(delegate);
            dispose();
        });

        Projection.addActionListener(e -> {
            openProjectionPage(delegate);
            dispose();
        });

        division.addActionListener(e -> {
            openDivisionPage(delegate);
            dispose();
        });


        viewUsersBtn.addActionListener(e -> displayUsersWithRecipes());

        viewTotalRecipesPerUserBtn.addActionListener(e -> {
            displayTotalRecipesPerUser();
            dispose();
        });

        BProjection.addActionListener(e -> {
            displayProjection();
            dispose();
        });

        showAboveAverageUsersBtn.addActionListener(e -> displayUsersAboveAverageRecipes());

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void displayProjection() {
        BProjection projection = new BProjection(delegate);
        projection.setVisible(true);
    }

    private void openDivisionPage(TerminalTransactionsDelegate delegate) {
        Division div = new Division(delegate);
        div.setVisible(true);
    }

    private void openUpdatePage(TerminalTransactionsDelegate delegate) throws SQLException {
        UpdateUser updateUser = new UpdateUser(delegate);
        updateUser.setVisible(true);
    }

    private void openDeletePage(TerminalTransactionsDelegate delegate) throws SQLException {

        DeleteUser deleteUser = new DeleteUser(delegate);
        deleteUser.setVisible(true);
    }

    private void openInsertPage(TerminalTransactionsDelegate dbHandler) throws SQLException {
        InsertRecipe insertRecipe = new InsertRecipe(dbHandler);
        insertRecipe.setVisible(true);

    }

    private void openFilterPage(TerminalTransactionsDelegate delegate) {
        FilterRecipes filterRecipes = new FilterRecipes(delegate);
        filterRecipes.setVisible(true);
    }

    private void openCreatedbyPage(TerminalTransactionsDelegate delegate) {
        RecipeCreatedby RecipeCreatedby = new RecipeCreatedby(delegate);
        RecipeCreatedby.setVisible(true);
    }

    private void openProjectionPage(TerminalTransactionsDelegate delegate) {
        Projection Projection = new Projection(delegate);
        Projection.setVisible(true);
    }

    private void displayUsersWithRecipes() {
        List<Map.Entry<Integer, Integer>> usersWithRecipes = delegate.getUsersWithMoreThanTwoRecipes();
        Object[][] data = new Object[usersWithRecipes.size()][2];
        for (int i = 0; i < usersWithRecipes.size(); i++) {
            Map.Entry<Integer, Integer> entry = usersWithRecipes.get(i);
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue();
        }
        String[] columnNames = {"UserID", "Recipe Count"};
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JDialog dialog = new JDialog();
        dialog.setTitle("Users with More Than Two Recipes");
        dialog.add(scrollPane);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null); // Center on screen
        dialog.setVisible(true);
    }

    private void displayTotalRecipesPerUser() {
        List<Map.Entry<Integer, Integer>> usersWithRecipes = delegate.getTotalRecipesPerUser();
        Object[][] data = new Object[usersWithRecipes.size()][2];
        for (int i = 0; i < usersWithRecipes.size(); i++) {
            Map.Entry<Integer, Integer> entry = usersWithRecipes.get(i);
            data[i][0] = entry.getKey();   // UserID
            data[i][1] = entry.getValue(); // Recipe Count
        }
        String[] columnNames = {"UserID", "Total Recipes"};
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JDialog dialog = new JDialog();
        dialog.setTitle("Total Recipes Per User");
        dialog.add(scrollPane);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null); // Center on screen
        dialog.setVisible(true);


        JButton BackToMenu = new JButton("Back to Menu");


        BackToMenu.setSize(10, 10);
        dialog.add(BackToMenu, BorderLayout.EAST);

        BackToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                MenuPage menuPage = new MenuPage(delegate);
                menuPage.setSize(400, 300);
                menuPage.setVisible(true);
            }
        });
    }

    private void displayUsersAboveAverageRecipes() {
        List<Map.Entry<String, Integer>> usersAboveAverage = delegate.getUsersAboveAverageRecipes();
        Object[][] data = new Object[usersAboveAverage.size()][2];
        int i = 0;
        for (Map.Entry<String, Integer> entry : usersAboveAverage) {
            data[i][0] = entry.getKey();   // Username
            data[i++][1] = entry.getValue(); // Total Recipes
        }
        String[] columnNames = {"Username", "Total Recipes"};
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JDialog dialog = new JDialog();
        dialog.setTitle("Users Above Average Recipes");
        dialog.add(scrollPane);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null); // Centers the dialog
        dialog.setVisible(true);
    }

}

