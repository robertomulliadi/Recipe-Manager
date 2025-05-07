package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class IngredientQuantity {
	private final int Quantity;
	
	public IngredientQuantity(int Quantity) {
		this.Quantity = Quantity;
	}

	public int getQuantity() {
		return Quantity;
	}

}
