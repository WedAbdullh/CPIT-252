/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit.pkg252;

/**
 *
 * @author wedalotibi
 */
public abstract class User {

    int userID;
    private String password;
    private boolean isLoggedIn; // Track login status

    // Constructor
    public User(int userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    // Login method for all users
    public boolean login(int userID, String password) {
        if (this.userID==userID && this.password.equals(password)) {
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