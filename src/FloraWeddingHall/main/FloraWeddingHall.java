package FloraWeddingHall.main;

import FloraWeddingHall.GUI.*;
import FloraWeddingHall.system.Database;
import java.sql.SQLException;

public class FloraWeddingHall {

    public static void main(String[] args) {

        try {
            Database.setupDatabase();
        } catch (SQLException e) {
            System.out.println("Error setting up the database.");
        }

        //LogInGUI.showLoginWindow();
        ManagerDashboardGUI.showPackagesWindow();
        //BookHallGUI.showBookingPage();
        //HomeGUI.showHomePage();

    }

}
