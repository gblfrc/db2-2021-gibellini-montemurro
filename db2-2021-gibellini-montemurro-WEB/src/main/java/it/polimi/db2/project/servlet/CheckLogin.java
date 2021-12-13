package it.polimi.db2.project.servlet;

import java.io.IOException;

import javax.ejb.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.entities.User;
import it.polimi.db2.project.services.UserService;
import it.polimi.db2.project.utils.Error;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserService uService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
		error.forward("/GetLogin", this, request, response);
		// what about filters and errors?
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// fetch user
		// try client first
		User user;
		user = uService.getClient(request.getParameter("username"), request.getParameter("password"));
		if (user == null) {
			user = uService.getEmployee(request.getParameter("username"), request.getParameter("password"));
		}

		if (user != null) {
			// save user in session
			request.getSession().setAttribute("user", user);
			// give actual access to result page
			if (user.getClass().equals(Client.class)) {
				//check if the user has already tried to create a sub
				//in that case send back to confirmation page
				if (request.getSession().getAttribute("subscription") != null) {
					response.sendRedirect(getServletContext().getContextPath() + "/GetConfirmationPage");
				}
				//if the user hasn't created a sub (i.e. he's come here from starting login) send to home page
				else response.sendRedirect(getServletContext().getContextPath() + "/GetUserHomePage");
			}
			else {
				response.sendRedirect(getServletContext().getContextPath() + "/GetEmployeeHomePage");
			}
		} else {
			Error error = new Error(HttpServletResponse.SC_NOT_FOUND, "Non-existent user for given credentials");
			error.forward("/GetLogin", this, request, response);
		}

	}

}
