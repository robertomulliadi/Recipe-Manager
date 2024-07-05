package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class IngredientQuantity {
    private Ingredient ingredient;
    private int quantity;

    public IngredientQuantity(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    // Getters and setters
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
