package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class DifficulityLevel {
	private final int Level;
	
	public DifficulityLevel(int Level) {
		this.Level = Level;
	}

	public int getLevel() {
		return Level;
	}

}
