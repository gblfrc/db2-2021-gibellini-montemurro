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

public class ClientOrNullFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.print("Checking access rights ...\n");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// check if the user is a client or hasn't logged yet
		HttpSession s = req.getSession();
		User user=(User)s.getAttribute("user");
		if(user!=null && !user.getClass().equals(Client.class)) {
			//add error
			System.out.println(req.getRequestURI());
			res.sendRedirect(req.getServletContext().getContextPath() + "/GetLogin");
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
