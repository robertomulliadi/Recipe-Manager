package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class MealType {
	private final String Type;
	
	public MealType(String Type) {
		this.Type = Type;
	}

	public String getType() {
		return Type;
	}
}
