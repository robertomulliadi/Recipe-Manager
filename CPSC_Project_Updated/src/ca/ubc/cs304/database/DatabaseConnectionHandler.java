package ca.ubc.cs304.database;

import java.sql.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.ubc.cs304.model.RecipeUser;
import ca.ubc.cs304.model.User;
import ca.ubc.cs304.util.PrintablePreparedStatement;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public String[] division() {
        ArrayList<String> result = new ArrayList<String>();

        try {
            String query = "SELECT DISTINCT u.username" +
                    " FROM RecipeUsers u" +
                    " WHERE NOT EXISTS" +
                    " (SELECT mt.Type FROM recipe_isof mt" +
                    " WHERE NOT EXISTS (SELECT * " +
                    "FROM Recipe_isOf r " +
                    "JOIN Recipe_createdby rc ON r.RecipeID = rc.RecipeID " +
                    "WHERE r.Type = mt.Type AND rc.userid = u.userid))";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("username"));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new String[result.size()]);
    }

    public RecipeUser[] filterRecipe(String UserInput) {
        ArrayList<RecipeUser> result = new ArrayList<RecipeUser>();

        try {

            String query = "SELECT * FROM recipe_CreatedBy WHERE " + UserInput;
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                while (rs.next()) {
                    RecipeUser recipe = new RecipeUser(rs.getInt("RecipeID"),
                            rs.getString("Title"),
                            rs.getString("Instructions"),
                            rs.getInt("CookingTime"),
                            rs.getInt("ServingSize"),
                            rs.getInt("UserID"));
                    result.add(recipe);
                }
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new RecipeUser[result.size()]);
    }

    public ArrayList<String> RandomProjection(String Selection, String From) {
        ArrayList<String> result = new ArrayList<String>();

        try {
            String query = "SELECT " + Selection + " FROM " + From;
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                StringBuilder rowBuilder = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    rowBuilder.append(rs.getString(i)).append(", ");
                }
                result.add(rowBuilder.substring(0, rowBuilder.length() - 2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result;
    }


    public String[] doProjection(ArrayList<String> Selection, String From) {
        ArrayList<String> result = new ArrayList<String>();

        String select = "";


        for (int i = 0; i < Selection.size(); i++) {
            if (i == 0) {
                select += Selection.get(0);
            } else {
                select += " , " + Selection.get(i);
            }
        }

        try {
            String query = "SELECT " + select + " FROM " + From;
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                for (String s : Selection) {
                    result.add(rs.getString(s));
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new String[result.size()]);
    }


    public String[] getTableNames() {

        ArrayList<String> result = new ArrayList<String>();


        try {
            String query = "SELECT table_name FROM " + "user_tables";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("table_name"));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new String[result.size()]);


    }


    public String[] getColNames(String selectedTable) {

        ArrayList<String> result = new ArrayList<String>();


        try {
            String query = "SELECT DISTINCT column_name FROM all_tab_columns WHERE table_name = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, selectedTable);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("column_name"));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new String[result.size()]);


    }


    public RecipeUser[] RecipeCreatedby(String Username) {
        ArrayList<RecipeUser> result = new ArrayList<RecipeUser>();

        try {
            String query = "SELECT * FROM recipe_CreatedBy, RecipeUsers" +
                    " WHERE recipe_CreatedBy.UserID = RecipeUsers.UserID AND" +
                    " RecipeUsers.Username = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, Username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RecipeUser recipe = new RecipeUser(rs.getInt("RecipeID"),
                        rs.getString("Title"),
                        rs.getString("Instructions"),
                        rs.getInt("CookingTime"),
                        rs.getInt("ServingSize"),
                        rs.getInt("UserID"));
                result.add(recipe);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new RecipeUser[result.size()]);
    }

    ;


    public void updateUser(int id, String username, String password, String preferences, String email) throws SQLException {
        try {
            String query = "UPDATE RecipeUSERS SET USERNAME = ?, PASSWORD = ?, PREFERENCES = ?, EMAIL= ? WHERE USERID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, preferences);
            ps.setString(4, email);
            ps.setInt(5, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Recipe " + id + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw new SQLException(e.getMessage());
        }
    }


    public void deleteRecipeUser(int userID) throws SQLException {
        try {
            String query = "DELETE FROM RecipeUSERS WHERE USERID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, userID);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " User " + userID + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw new SQLException(e.getMessage());

        }
    }

    public void insertRecipeUser(RecipeUser recipeUser) throws SQLException {
        try {
            String query = "INSERT INTO RECIPE_CREATEDBY VALUES (?,?,?,?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, recipeUser.getRecipeID());
            ps.setString(2, recipeUser.getTitle());
            ps.setString(3, recipeUser.getInstructions());
            ps.setInt(4, recipeUser.getCookingTime());
            ps.setInt(5, recipeUser.getServingSize());
            ps.setInt(6, recipeUser.getUserID());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw new SQLException(e.getMessage());
        }
    }

    public RecipeUser[] getRecipeUserInfo() {
        ArrayList<RecipeUser> result = new ArrayList<RecipeUser>();

        try {
            String query = "SELECT * FROM RECIPE_CREATEDBY";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RecipeUser recipeUser = new RecipeUser(rs.getInt("RecipeID"),
                        rs.getString("Title"),
                        rs.getString("Instructions"),
                        rs.getInt("CookingTime"),
                        rs.getInt("ServingSize"),
                        rs.getInt("UserID"));
                result.add(recipeUser);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {

            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new RecipeUser[result.size()]);
    }


    public User[] getUserInfo() {
        ArrayList<User> result = new ArrayList<User>();

        try {
            String query = "SELECT * FROM RecipeUSERS";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Preferences"),
                        rs.getString("Email"));
                result.add(user);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {

            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new User[result.size()]);
    }


    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }



    public List<Map.Entry<Integer, Integer>> getUsersWithMoreThanTwoRecipes() {
        List<Map.Entry<Integer, Integer>> usersWithRecipes = new ArrayList<>();
        try {
            String query = "SELECT UserID, COUNT(*) AS RecipeCount FROM Recipe_CreatedBy GROUP BY UserID HAVING COUNT(*) > 2";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("UserID");
                int recipeCount = rs.getInt("RecipeCount");
                usersWithRecipes.add(new AbstractMap.SimpleEntry<>(userId, recipeCount));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return usersWithRecipes;
    }

    public List<Map.Entry<Integer, Integer>> getTotalRecipesPerUser() {
        List<Map.Entry<Integer, Integer>> userRecipeCounts = new ArrayList<>();
        try {
            String query = "SELECT UserID, COUNT(*) AS RecipeCount FROM Recipe_CreatedBy GROUP BY UserID";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("UserID");
                int recipeCount = rs.getInt("RecipeCount");
                userRecipeCounts.add(new AbstractMap.SimpleEntry<>(userId, recipeCount));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return userRecipeCounts;
    }

    public List<Map.Entry<String, Integer>> getUsersAboveAverageRecipes() {
        List<Map.Entry<String, Integer>> results = new ArrayList<>();
        try {
            String query = "SELECT u.username, COUNT(*) AS total_recipes " +
                    "FROM Users u JOIN Recipe_createdby rc ON u.userid = rc.userid " +
                    "GROUP BY u.username " +
                    "HAVING COUNT(*) > ( " +
                    "SELECT AVG(recipe_count) FROM ( " +
                    "SELECT userid, COUNT(*) AS recipe_count " +
                    "FROM Recipe_createdby " +
                    "GROUP BY userid) tempTable)";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                int totalRecipes = rs.getInt("total_recipes");
                results.add(new AbstractMap.SimpleEntry<>(username, totalRecipes));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return results;
    }
}
