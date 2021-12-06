package it.polimi.db2.project.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.entities.Order;
import it.polimi.db2.project.entities.ServicePackage;
import it.polimi.db2.project.services.OrderService;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.utils.TemplateEngineHandler;

@WebServlet("/GetUserHomePage")
public class GetUserHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;

	@EJB
	private SpService spService;
	@EJB
	private OrderService oService;

	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get user from session --> useful to write name at the top of the page
		Client user = (Client)request.getSession().getAttribute("user");
		// !! NEED ERROR HANDLING !!
		
		//fetch all service packages to show
		List<ServicePackage> packages = spService.getAllPackages();
		
		//fetch all currently invalid orders for user
		/*Client user;
		try {
			user = (Client) request.getSession().getAttribute("user");
		} catch(ClassCastException cce) {
			user = null;
		};*/
		
		//actually fetch orders
		List<Order> refusedOrders;
		if (user != null) refusedOrders = oService.getInvalidOrdersByClient(user);
		else refusedOrders = new LinkedList<>();
		
		//give access to actual home page which should show the packages
		String path = "/WEB-INF/home.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("user", user);
		ctx.setVariable("packages", packages);
		ctx.setVariable("invOrders", refusedOrders);
		templateEngine.process(path, ctx, response.getWriter());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
	}

}
