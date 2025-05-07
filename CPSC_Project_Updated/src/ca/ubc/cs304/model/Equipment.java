package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Equipment {
	private final String Category;
	private final String Name;
	private final int equipmentID;
	
	public Equipment(String Category, String Name, int equipmentID) {
		this.Category = Category;
		this.Name = Name;
		this.equipmentID = equipmentID;
	}

	public String getCategory() {
		return Category;
	}

	public String getName() {
		return Name;
	}

	public int getequipemntId() {
		return equipmentID;
	}

}
