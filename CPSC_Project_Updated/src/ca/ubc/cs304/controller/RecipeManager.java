package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.RecipeUser;
import ca.ubc.cs304.model.User;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.MenuPage;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class RecipeManager implements LoginWindowDelegate, TerminalTransactionsDelegate {
    private DatabaseConnectionHandler dbHandler = null;
    private LoginWindow loginWindow = null;

    public RecipeManager() {
        dbHandler = new DatabaseConnectionHandler();
    }

    private void start() {
        loginWindow = new LoginWindow();
        loginWindow.showFrame(this);
    }

    /**
     * LoginWindowDelegate Implementation
     * <p>
     * connects to Oracle database with supplied username and password
     */
    public void login(String username, String password) {
        boolean didConnect = dbHandler.login(username, password);

        if (didConnect) {
            // Once connected, remove login window and start text transaction flow
            loginWindow.dispose();

            MenuPage menuPage = new MenuPage(this);
            menuPage.setVisible(true);
            menuPage.setSize(400, 300);
            menuPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } else {
            loginWindow.handleLoginFailed();

            if (loginWindow.hasReachedMaxLoginAttempts()) {
                loginWindow.dispose();
                System.out.println("You have exceeded your number of allowed attempts");
                System.exit(-1);
            }
        }
    }


    @Override
    public String[] division() {
        return dbHandler.division();
    }

    public RecipeUser[] filterRecipe(String UserInput){return dbHandler.filterRecipe(UserInput); }


    public ArrayList<String> Projection (String Selection, String From){
        return dbHandler.RandomProjection(Selection, From);
    }
    public RecipeUser[] RecipeCreatedby(String Username){return dbHandler.RecipeCreatedby(Username); }

    @Override
    public void insertRecipeUser(RecipeUser recipeUser) throws SQLException {
        dbHandler.insertRecipeUser(recipeUser);
    }

    @Override
    public RecipeUser[] getRecipeUserInfo() {
        return dbHandler.getRecipeUserInfo();
    }

    @Override
    public User[] getUserInfo() {
        return dbHandler.getUserInfo();
    }

    @Override
    public void deleteRecipeUser(int recipeUserId) throws SQLException {
        dbHandler.deleteRecipeUser(recipeUserId);
    }

    @Override
    public void updateUser(int id, String username, String password, String preferences, String email) throws SQLException {
        dbHandler.updateUser(id, username, password, preferences, email);
    }

    @Override
    public List<Map.Entry<Integer, Integer>> getUsersWithMoreThanTwoRecipes() {
        return dbHandler.getUsersWithMoreThanTwoRecipes();
    }

    @Override
    public List<Map.Entry<Integer, Integer>> getTotalRecipesPerUser() {
        return dbHandler.getTotalRecipesPerUser();
    }

    @Override
    public void updateRecipe(int branchId, String name) {

    }

    @Override
    public String[] getTableNames() {
        return dbHandler.getTableNames();
    }

    @Override
    public String[] getColNames(String selectedTable) {
        return dbHandler.getColNames(selectedTable);
    }

    @Override
    public String[] doProjection(ArrayList<String> Selection, String From) {
        return dbHandler.doProjection(Selection, From);
    }

    @Override
    public List<Map.Entry<String, Integer>> getUsersAboveAverageRecipes() {
        return dbHandler.getUsersAboveAverageRecipes();
    }

    /**
     * TerminalTransactionsDelegate Implementation
     * <p>
     * The TerminalTransaction instance tells us that it is done with what it's
     * doing so we are cleaning up the connection since it's no longer needed.
     */
    public void terminalTransactionsFinished() {
        dbHandler.close();
        dbHandler = null;

        System.exit(0);
    }

    /**
     * Main method called at launch time
     */
    public static void main(String args[]) {
        RecipeManager recipeManager = new RecipeManager();
        recipeManager.start();
    }
}
