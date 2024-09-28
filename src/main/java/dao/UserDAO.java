package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
import utility.DatabaseConnection;

public class UserDAO {
	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
	private static final String ADD_USERS_SQL = "insert into users (name, email, country) values (?,?,?)";

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
	 * This methods adds new user details to the database table "users"
	 * 
	 * @param user
	 * @return
	 */
	public boolean addUser(User user) {
		Connection connection = null;
		boolean insertSuccess = false;
		try {
			connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = connection.prepareStatement(ADD_USERS_SQL);

			preparedStmt.setString(1, user.getName());
			preparedStmt.setString(2, user.getEmail());
			preparedStmt.setString(3, user.getCountry());

			insertSuccess = preparedStmt.executeUpdate() > 0;

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
		return insertSuccess;
	}

	/**
	 * This method will find out the User with given id, from users table and will
	 * return User object
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(int id) {
		User user = null;
		Connection connection = null;
		try {
			connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = connection.prepareStatement(SELECT_USER_BY_ID);
			preparedStmt.setInt(1, id);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User(id, name, email, country);
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
		return user;
	}

	/**
	 * This method will return the list of all users from user table. return User
	 * object
	 * 
	 * @param id
	 * @return
	 */
	public List<User> selectAllUsers() {
		User user = null;
		Connection connection = null;
		List<User> usersList = new ArrayList<>();
		try {
			connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = connection.prepareStatement(SELECT_ALL_USERS);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User(id, name, email, country);
				usersList.add(user);
				System.out.println(user);
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
		return usersList;
	}

	/**
	 * This method will delete user by give id * @param id
	 * 
	 * @return
	 */
	public boolean deleteUser(int id) {
		Connection connection = null;
		boolean deleteSuccess = false;
		try {
			connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = connection.prepareStatement(DELETE_USERS_SQL);
			preparedStmt.setInt(1, id);
			deleteSuccess = preparedStmt.executeUpdate() > 0;
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
		return deleteSuccess;
	}

	/**
	 * This method will update User data
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user) {
		Connection connection = null;
		boolean updateSuccess = false;
		try {
			connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStmt = connection.prepareStatement(UPDATE_USERS_SQL);
			preparedStmt.setString(1, user.getName());
			preparedStmt.setString(2, user.getEmail());
			preparedStmt.setString(3, user.getCountry());
			preparedStmt.setInt(4, user.getId());
			updateSuccess = preparedStmt.executeUpdate() > 0;
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
		return updateSuccess;
	}
}
