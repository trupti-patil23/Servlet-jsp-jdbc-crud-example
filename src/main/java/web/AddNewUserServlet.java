package web;

import java.io.IOException;
import model.User;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("AddNewUserServlet called for adding New User");
		String userName = (String) req.getParameter("username");
		String email = (String) req.getParameter("email");
		String country = (String) req.getParameter("country");

		// Create User object with inputs received from web browser
		User user = new User(userName, email, country);
		
		UserDAO userDao = new UserDAO();
		boolean isUserAdded = userDao.addUser(user);
		System.out.println("isUserAdded"+isUserAdded);
		
		if (isUserAdded) {
			req.setAttribute("message", "User added successfully.");
			req.getRequestDispatcher("AddNewUser.jsp").forward(req, resp);
		} else {
			req.setAttribute("message", "Error while adding user.");
			req.getRequestDispatcher("AddNewUser.jsp").forward(req, resp);
		}
	}
}
