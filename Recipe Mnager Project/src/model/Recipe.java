package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Recipe {
    private final int RecipeID;
    private final String Title;
    private final String Instructions;
    private final int CookingTime;
    private final int ServingSize;

    public Recipe(int RecipeID, String Title, String Instructions, int CookingTime, int ServingSize) {
        this.RecipeID = RecipeID;
        this.Title = Title;
        this.Instructions = Instructions;
        this.CookingTime = CookingTime;
        this.ServingSize = ServingSize;
    }


    public int getRecipeID() {
        return RecipeID;
    }
    public String getTitle() {
        return Title;
    }
    public String getInstructions() {
        return Instructions;
    }
    public int getCookingTime() {
        return CookingTime;
    }
    public int getServingSize() {return ServingSize;}
}
