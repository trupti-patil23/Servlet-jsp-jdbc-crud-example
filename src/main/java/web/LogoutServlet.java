package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Invalidate session
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		
	    // Delete the username cookie
		Cookie cookie = new Cookie("username", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		response.sendRedirect("Login.jsp");
	}	
}
