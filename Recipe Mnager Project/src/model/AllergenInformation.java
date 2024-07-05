package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class AllergenInformation {
	private final String AllergenType;
	private final int AllergenID;
	
	public AllergenInformation(String AllergenType, int AllergenID) {
		this.AllergenType = AllergenType;
		this.AllergenID = AllergenID;
	}

	public String getAllergenType() {
		return AllergenType;
	}
	public int getAllergenID() {
		return AllergenID;
	}
}
