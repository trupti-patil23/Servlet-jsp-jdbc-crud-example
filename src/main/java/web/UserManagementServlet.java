package web;

import java.io.IOException;
import java.util.List;

import model.User;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserManagementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public void init() {
		userDAO = new UserDAO();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if (action.equals("AddNewUser")) {
			addNewUser(req, resp);
		}
		if (action.equals("ViewUsers")) {
			viewAllUsers(req, resp);
		}
		if (action.equals("EditUser")) {
			editUser(req, resp);
		}
		if (action.equals("DeleteUser")) {
			deleteUser(req, resp);
		}
		if (action.equals("SaveUser")) {
			saveUser(req, resp);
		}
	}
	/**
	 * This method saves updated User data to the database table
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void saveUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id= Integer.parseInt(req.getParameter("id"));
		String userName = (String) req.getParameter("username");
		String email = (String) req.getParameter("email");
		String country = (String) req.getParameter("country");

		// Create User object with inputs received from web browser
		User user = new User(id, userName, email, country);	
	
		boolean isUserUpdated = userDAO.updateUser(user);

		String message = isUserUpdated ? "User added successfully."
									 : "Error while adding user.";
		
		// Set the message as a request attribute
		req.setAttribute("message", message);
		
		// Forward the request to the JSP page
		req.getRequestDispatcher("EditUser.jsp").forward(req, resp);
	}

	/**
	 * This method deletes user from the database
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt((String) req.getParameter("id"));
		boolean isDeleted = userDAO.deleteUser(id);
		
		String message = isDeleted ? "User got deleted successfully." : "Error while deleting user.";
	
		// Set the message as a request attribute
		req.setAttribute("message", message);	

		// Forward the request to the JSP page
		req.getRequestDispatcher("ViewUsers.jsp").forward(req, resp);
	}

	/**
	 * This method edits the User data and save updated information to the database
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt((String) req.getParameter("id"));
		User user = userDAO.getUserById(id);
		req.setAttribute("user", user);
		req.getRequestDispatcher("EditUser.jsp").forward(req, resp);
	}

	/**
	 * Added to list down all users from "users" table
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void viewAllUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> usersList = userDAO.selectAllUsers();
		req.setAttribute("usersList", usersList);
		req.getRequestDispatcher("ViewUsers.jsp").forward(req, resp);
	}

	/**
	 * This method adds new User to the database
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addNewUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userName = (String) req.getParameter("username");
		String email = (String) req.getParameter("email");
		String country = (String) req.getParameter("country");

		// Create User object with inputs received from web browser
		User user = new User(userName, email, country);

		UserDAO userDao = new UserDAO();
		boolean isUserAdded = userDao.addUser(user);

		String message = isUserAdded ? "User added successfully."
									 : "Error while adding user.";
		
		// Set the message as a request attribute
		req.setAttribute("message", message);
		
		// Forward the request to the JSP page
		req.getRequestDispatcher("AddNewUser.jsp").forward(req, resp);
	}
}
