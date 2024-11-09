package cpit.pkg252;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlertSystem implements Subject {
    private List<Observer> observers;
    private List<String> alerts; // Store alert messages for broadcast

    public AlertSystem() {
        this.observers = new ArrayList<>();
        this.alerts = new ArrayList<>();
    }

    public void addAlert(String message) {
        alerts.add(message); // Store the alert message
        notifyObservers(message); // Notify all observers
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message); // Send alert to each observer
        }
    }
  
    
    // Method to send alert to all customers
    public void sendAlertToAllCustomers(String alertMessage) {
        try (Connection conn = Database.getConnection()) {
            // Assuming you have a Customer table with an "alert" column
            PreparedStatement stmt = conn.prepareStatement("UPDATE Customer SET alerts = ? WHERE alerts IS NULL");
            stmt.setString(1, alertMessage);  // Set the alert message
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
