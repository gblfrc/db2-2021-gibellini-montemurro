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

import it.polimi.db2.project.entities.Employee;
import it.polimi.db2.project.entities.User;
import it.polimi.db2.project.utils.Error;

public class EmployeeFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.print("Checking employee's access rights ...\n");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// check if the logged user is a employee
		HttpSession s = req.getSession();
		User user=(User)s.getAttribute("user");
		if(user==null || !user.getClass().equals(Employee.class)) {
			//handle specifically the illegal request to JSONPackage
			if(req.getRequestURI().contains("JSONTable")) {
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				res.getWriter().write("Unathorized resource access");
			}
			else {
				//create error
				Error error = new Error(HttpServletResponse.SC_UNAUTHORIZED, "Unathorized resource access; user logged out");
				//change message if user hasn't logged yet
				if (user==null) error.setMessage("Unathorized resource access");
				// log out user
				req.getSession().removeAttribute("user");
				//save error in session to display in login page
				req.getSession().setAttribute("logError", error);
				//redirect to login page
				res.sendRedirect(req.getServletContext().getContextPath() + "/GetLogin");
			}
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
