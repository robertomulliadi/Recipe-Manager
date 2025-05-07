package ca.ubc.cs304.delegates;

//import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.Recipe;
import ca.ubc.cs304.model.RecipeUser;
import ca.ubc.cs304.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the
 * controller class (in this case Bank).
 *
 * TerminalTransactions calls the methods that we have listed below but
 * Bank is the actual class that will implement the methods.
 */
public interface TerminalTransactionsDelegate {
//	public void databaseSetup();
//
//	public void deleteRecipe(int recipeId);
//	public void insertRecipe(Recipe recipe);

//	public Recipe[] getRecipeInfo();

	public String[] division();
	public RecipeUser[] filterRecipe(String UserInput);
	public ArrayList<String> Projection (String Selection, String From);
	public RecipeUser[] RecipeCreatedby(String Username);

	public void insertRecipeUser(RecipeUser recipeUser) throws SQLException;
	public RecipeUser[] getRecipeUserInfo();

	public User[] getUserInfo();

	public void deleteRecipeUser(int recipeUserId) throws SQLException;

	public void updateUser(int id, String username, String password, String preferences, String email) throws SQLException;

//	public void showRecipe();
	public void updateRecipe(int branchId, String name);

	public String[] getTableNames();

	public String[] getColNames(String selectedTable);
	public String[] doProjection (ArrayList<String> Selection, String From);
	public void terminalTransactionsFinished();

	List<Map.Entry<Integer, Integer>> getUsersWithMoreThanTwoRecipes();

	List<Map.Entry<Integer, Integer>> getTotalRecipesPerUser();

	List<Map.Entry<String, Integer>> getUsersAboveAverageRecipes();


}
