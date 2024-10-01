package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrieve cookies from the request
		Cookie[] cookies = request.getCookies();
		String username = null;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					username = cookie.getValue();
					break;
				}
			}
		}

		if (username != null) {
			request.setAttribute("username", username);
			response.sendRedirect("Header.jsp");
		} else {

			response.sendRedirect("Login.jsp");
		}
	}

}
