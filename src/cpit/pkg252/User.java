package cpit.pkg252;

public abstract class User {

    protected int id;               
    protected String name;          
    protected String email;         
    protected String phone;         
    private String password;        
    private boolean isLoggedIn;     // Track login status

    // Constructor
    public User(int id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.isLoggedIn = false;
    }

    // Default constructor
    public User() {}

    // Generic login method for all users based on email and password
    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            this.isLoggedIn = true;
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid email or password.");
            return false;
        }
    }

    // Logout method to reset the login status
    public void logout() {
        if (isLoggedIn) {
            isLoggedIn = false;
            System.out.println("User logged out.");
        } else {
            System.out.println("User is already logged out.");
        }
    }

    // Verify login status
    public boolean verifyLogin() {
        return isLoggedIn;
    }

    // Getters and setters for the fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {  // Protected for subclass use
        this.password = password;
    }
}
