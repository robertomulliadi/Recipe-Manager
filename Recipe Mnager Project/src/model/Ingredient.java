package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
//public class Ingredient {
//	private final String Name;
//	private final String MeasurementUnit;
//	private final int IngredientID;
//
//	public Ingredient(String Name, String MeasurementUnit, int IngredientID) {
//		this.Name = Name;
//		this.MeasurementUnit = MeasurementUnit;
//		this.IngredientID = IngredientID;
//	}
//
//	public String getMeasurementUnit() {
//		return MeasurementUnit;
//	}
//
//	public String getName() {
//		return Name;
//	}
//
//	public int getIngredientID() {
//		return IngredientID;
//	}
//
//}
public class Ingredient {
    private int ingredientID;
    private String name;
    private String measurementUnit;

    public Ingredient(int ingredientID, String name, String measurementUnit) {
        this.ingredientID = ingredientID;
        this.name = name;
        this.measurementUnit = measurementUnit;
    }

    // Getters and setters
    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
}