package library_managament_system; // Fixed typo in package name

import GUI.Home;
import java.sql.Connection; // Correct import statement for java.sql.Connection

/**
 * The Library_managament_system class contains the main method to start the
 * Library Management System.
 */
public class Library_managament_system {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // Create a DatabaseConnection object to establish a connection to the database
        DatabaseConnection databaseConnection = new DatabaseConnection();

        // Retrieve the established database connection
        Connection connection = databaseConnection.getConnection(); // Removed unnecessary casting

        // Check if the connection is not null
        if (connection != null) {
            System.out.println("Connected to the database!");

            // Perform database operations here using the 'connection' object
            // Uncomment and add your database operations here
            // Close the database connection if needed
            // databaseConnection.closeConnection();
        } else {
            System.out.println("Failed to connect to the database.");
        }

        // Create and display the GUI Home frame using Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            Home homeFrame = new Home();
            homeFrame.setVisible(true);
        });
    }
}
