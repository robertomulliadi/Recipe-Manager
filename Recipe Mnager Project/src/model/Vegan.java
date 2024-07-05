package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Vegan extends Ingredient {
    private boolean organic;

    public Vegan(int ingredientID, String name, String measurementUnit, boolean organic) {
        super(ingredientID, name, measurementUnit);
        this.organic = organic;
    }

    // Getter and setter for organic
    public boolean isOrganic() {
        return organic;
    }

    public void setOrganic(boolean organic) {
        this.organic = organic;
    }
}
