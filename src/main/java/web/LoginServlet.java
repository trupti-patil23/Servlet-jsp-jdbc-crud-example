package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import dao.LoginDAO;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LoginDAO loginDAO;

	public void init() {
		loginDAO = new LoginDAO();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equals("Login")) {
			doLogin(request, response);
		}
	}

	/**
	 * Validates UserName and Password from database table
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Get User name and password from the form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isValidUser = loginDAO.getUser(username, password);

		if (isValidUser) {
			// Create a session for the user
			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			// Create a cookie to remember the user
			Cookie userCookie = new Cookie("username", username);
			response.addCookie(userCookie);
			
			// Redirect to home page after login
			 response.sendRedirect("home");

		} else {
			 // Invalid login, redirect to login page
            response.sendRedirect("Login.jsp?error=Invalid Credentials");
		}
	}

}
