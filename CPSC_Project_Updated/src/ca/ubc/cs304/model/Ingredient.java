package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Ingredient {
	private final String Name;
	private final String MeasurementUnit;
	private final int IngredientID;
	
	public Ingredient(String Name, String MeasurementUnit, int IngredientID) {
		this.Name = Name;
		this.MeasurementUnit = MeasurementUnit;
		this.IngredientID = IngredientID;
	}

	public String getMeasurementUnit() {
		return MeasurementUnit;
	}

	public String getName() {
		return Name;
	}

	public int getIngredientID() {
		return IngredientID;
	}

}
