package it.polimi.db2.project.servlet;

import java.io.IOException;

import javax.ejb.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.project.services.UserService;
import it.polimi.db2.project.utils.Error;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserService uService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
		error.forward("/GetLogin", this, request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username, password, email;
		try {
			username = request.getParameter("username");
			password = request.getParameter("password");
			email = request.getParameter("email");
			//check parameters validity
			if (username == null || username.equals("") || username.substring(0, 1).equals(" ") ||
					password == null || password.equals("")	|| password.substring(0, 1).equals(" ") ||
					email == null|| email.equals("") || password.substring(0, 1).equals(" ") || email.contains(" ")) {
				throw new Exception();
			}
			//save user in DB
			uService.addClient(username, password, email);
		} catch (Exception e) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Couldn't create account, invalid credentials");
			error.forward("/GetLogin", this, request, response);
			return;
		}

		Error success = new Error(HttpServletResponse.SC_OK, "Successful registration");
		//save success in session
		request.getSession().setAttribute("success", success);

		response.sendRedirect(getServletContext().getContextPath() + "/GetLogin");
	}

}