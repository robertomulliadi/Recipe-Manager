package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class nutritionalInformation {
    private final String VitaminsAndMinerals;
    private final int NutritionID;
    private final int Calories;
    private final String macronutrients;

    public nutritionalInformation(String VitaminsAndMinerals, int NutritionID, int Calories, String macronutrients) {
        this.VitaminsAndMinerals = VitaminsAndMinerals;
        this.NutritionID = NutritionID;
        this.Calories = Calories;
        this.macronutrients = macronutrients;
    }


    public String getVitaminsAndMinerals() {
        return VitaminsAndMinerals;
    }

    public int getNutritionID() {
        return NutritionID;
    }

    public int getCalories() {
        return Calories;
    }

    public String getMacronutrients() {
        return macronutrients;
    }
}
