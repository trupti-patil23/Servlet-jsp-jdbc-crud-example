package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

public class ViewUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ViewUsersServlet called for viewing list of Users");
		UserDAO userDao = new UserDAO();
		List<User> usersList = userDao.selectAllUsers();
		System.out.println("List of users"+usersList);

		req.setAttribute("usersList", usersList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("ViewUsers.jsp");
		dispatcher.forward(req, resp);
	}
}
