package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utility.DatabaseConnection;

public class LoginDAO {
	private static final String SELECT_LOGIN_DETAILS = "select * from login where username =? and password = ?";

	/**
	 * This method will print exception in detail.
	 * 
	 * @param SQLException
	 */
	void printSQLException(SQLException ex) {
		while (ex != null) {
			ex.printStackTrace(System.err);
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("Error Code: " + ex.getErrorCode());
			System.err.println("Message: " + ex.getMessage());

			Throwable t = ex.getCause();
			while (t != null) {
				System.out.println("Cause: " + t);
				t = t.getCause();
			}
			ex = ex.getNextException(); // Move to the next linked SQLException
		}
	}

	/**
	 * This method will find out the User with given id, from users table and will
	 * return User object
	 * 
	 * @param id
	 * @return
	 */
	public boolean getUser(String username,String password) {	
		Connection connection = null;
		boolean isUserAutharized = false;
		
		try {
			connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = connection.prepareStatement(SELECT_LOGIN_DETAILS);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				isUserAutharized = true;
			}
		} catch (SQLException ex) {
			// Catch SQLException and pass it to printSQLException
			printSQLException(ex);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
		}
		return isUserAutharized;
	}
}
