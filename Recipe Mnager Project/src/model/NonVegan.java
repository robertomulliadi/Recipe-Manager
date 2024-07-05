package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class NonVegan extends Ingredient {
    private boolean animalDerived;

    public NonVegan(int ingredientID, String name, String measurementUnit, boolean animalDerived) {
        super(ingredientID, name, measurementUnit);
        this.animalDerived = animalDerived;
    }

    // Getter and setter for animalDerived
    public boolean isAnimalDerived() {
        return animalDerived;
    }

    public void setAnimalDerived(boolean animalDerived) {
        this.animalDerived = animalDerived;
    }
}
