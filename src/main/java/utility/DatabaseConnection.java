package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
    // Declare the static instance of the connection
    private static Connection connection = null;
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/java_training";
	private static final String USER = "root";
	private static final String PASSWORD = "rootroot";
	
	 // Private constructor to prevent external instantiation
	private DatabaseConnection() {
		
	}

	/**
	 * Static method to get the connection instance 
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (SQLException e) {
            // Handle any SQL exceptions during connection setup
            e.printStackTrace();
            throw new SQLException("Failed to create the database connection.", e);
        } catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
		
		System.out.println("Connected to database successfully");
		return connection;
	}

}
