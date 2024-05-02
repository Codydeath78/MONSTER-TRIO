package monstertrio.myanime.app.models;

//this class is no longer in use
public class User {
    private int id;
    private String username;
    private String password;
    private String name;

    //constructor
    public User() {
        id = 0;
        username = "";
        password = "";
        name = "";
    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    //getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean authenticate(String password) {
        // Method to authenticate user by comparing provided password with stored password
        // Consider using secure password hashing and comparison
        return this.password.equals(password);
    }

    public static boolean isValidUsername(String username) {
        // Perform username validation logic
        // For example, check length, allowed characters, etc.
        return username.charAt(0) != '{';

    }

}
