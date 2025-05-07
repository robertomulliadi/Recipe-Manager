package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class User {
    private final int UserID;
    private final String Username;
    private final String Password;
    private final String Preferences;
    private final String email;

    public User(int UserID, String Username, String Password, String Preferences, String email) {
        this.UserID = UserID;
        this.Username = Username;
        this.Password = Password;
        this.Preferences = Preferences;
        this.email = email;
    }


    public int getUserID() {
        return UserID;
    }
    public String getUsername() {
        return Username;
    }

    public String getPassword() {return Password;}

    public String getPreferences() {return Preferences;}

    public String getEmail() {return email;}
}
