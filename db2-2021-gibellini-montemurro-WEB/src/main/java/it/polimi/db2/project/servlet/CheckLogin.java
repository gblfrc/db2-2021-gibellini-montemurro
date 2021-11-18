package it.polimi.db2.project.servlet;

import java.io.IOException;

import javax.ejb.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.services.UserService;
import it.polimi.db2.project.utils.TemplateEngineHandler;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;

	@EJB
	private UserService uService;

	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
		// what about filters and errors?
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// fetch user
		// try client first
		Client client;
		client = uService.getClient(request.getParameter("username"), request.getParameter("password"));

		request.getSession().setAttribute("user", client);

		if (client != null) {
			// give actual access to result page
			String path = "/WEB-INF/OK.html";
			ServletContext servletContext = getServletContext();
			final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
			ctx.setVariable("username", client.getUsername());
			ctx.setVariable("email", client.getEmail());
			templateEngine.process(path, ctx, response.getWriter());
		}
		else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Non-existent user for given credentials");
		}

		// response.sendRedirect(getServletContext().getContextPath() + "/GetCourses");

	}

}
