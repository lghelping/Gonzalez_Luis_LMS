
/**
 * The library_management_system package contains classes for the library management system.
 * This package includes functionality for database connection and GUI.
 */
package library_managament_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseConnection class handles the connection to the library management
 * system database.
 */

public class DatabaseConnection {
public static void main(String[] args){
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/library_management_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "26879704aA.";

    // Connection object to manage the database connection
    private Connection connection;

    /**
     * Constructs a new DatabaseConnection object and attempts to establish a
     * connection to the database. If successful, the connection field is
     * assigned the connected instance. Handles SQLException and
     * ClassNotFoundException.
     */
    public DatabaseConnection() {
        try {
            // Register the JDBC driver and establish a connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the established database connection.
     *
     * @return The Connection object representing the database connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Closes the database connection if it is not null. Handles SQLException if
     * encountered while attempting to close the connection.
     */
    public void closeConnection() {
        try {
            // Close the connection if it is not null
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
