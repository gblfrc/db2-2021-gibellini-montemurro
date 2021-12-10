package it.polimi.db2.project.servlet;

import java.io.IOException;

import javax.ejb.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.thymeleaf.TemplateEngine;

import it.polimi.db2.project.services.UserService;
import it.polimi.db2.project.utils.TemplateEngineHandler;
import it.polimi.db2.project.utils.Error;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;

	@EJB
	private UserService uService;

	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username, password, email;
		try {
			username = request.getParameter("username");
			password = request.getParameter("password");
			email = request.getParameter("email");
			if(username.equals("")||username==null||password.equals("")||password==null||email.equals("")||email==null) {
				throw new Exception();
			}
			uService.addClient(username, password, email);
		}catch(Exception e) {
			Error error=new Error(HttpServletResponse.SC_BAD_REQUEST, "Couldn't create account, invalid credentials");
			error.forward("/GetLogin", this, request, response);
		}
		
		RequestDispatcher rd= getServletContext().getRequestDispatcher("/CheckLogin");
		rd.forward(request, response);
	}

}