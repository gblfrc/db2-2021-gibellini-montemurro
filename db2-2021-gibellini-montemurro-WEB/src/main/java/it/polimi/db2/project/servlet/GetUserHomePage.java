package it.polimi.db2.project.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import it.polimi.db2.project.entities.ServicePackage;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.utils.TemplateEngineHandler;

@WebServlet("/GetUserHomePage")
public class GetUserHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;

	@EJB
	private SpService spService;

	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get user from session --> useful to write name at the top of the page
		//User user = (User)request.getSession().getAttribute("user");
		
		//fetch all service packages to show
		List<ServicePackage> packages = spService.getAllPackages();
		//give access to actual home page which should show the packages
		String path = "/WEB-INF/home.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("packages", packages);
		templateEngine.process(path, ctx, response.getWriter());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
	}

}
