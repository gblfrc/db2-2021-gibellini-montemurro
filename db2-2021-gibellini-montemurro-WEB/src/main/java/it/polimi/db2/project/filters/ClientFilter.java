package it.polimi.db2.project.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.entities.User;
import it.polimi.db2.project.utils.Error;

public class ClientFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Checking client's access rights ...");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// check if the logged user is a client
		HttpSession s = req.getSession();
		User user = (User) s.getAttribute("user");
		if (user == null || !user.getClass().equals(Client.class)) {
			//create error
			Error error = new Error(HttpServletResponse.SC_UNAUTHORIZED, "Unathorized resource access; user logged out");
			//change message if user hasn't logged yet
			if (user==null) error.setMessage("Unathorized resource access");
			// log out user
			req.getSession().removeAttribute("user");
			// save error in session to display in login page
			req.getSession().setAttribute("logError", error);
			// redirect to login page
			res.sendRedirect(req.getServletContext().getContextPath() + "/GetLogin");
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
