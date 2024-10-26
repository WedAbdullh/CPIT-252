package com.mycompany.cpit252_project;

public abstract class User {

    String userID;
    private String password;
    private boolean isLoggedIn; // Track login status

    // Constructor
    public User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    // Login method for all users
    public boolean login(String userID, String password) {
        if (this.userID.equals(userID) && this.password.equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid user ID or password.");
            return false;
        }
    }

    // Logout method
    public void logout() {
        System.out.println("User logged out.");
    }

    // Verify login
    public boolean verifyLogin() {
        return isLoggedIn; // Return the actual login status
    }
}
