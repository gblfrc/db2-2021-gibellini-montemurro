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
import it.polimi.db2.project.utils.Error;
import it.polimi.db2.project.utils.TemplateEngineHandler;

@WebServlet("/GetClientHomePage")
public class GetClientHomePage extends HttpServlet {
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

		//fetch error (if present, otherwise error is null)
		Error error = (Error)request.getAttribute("error");
		
		//get user from session to write name at the top of the page and to retrieve failed orders
		Client user = (Client)request.getSession().getAttribute("user");
		
		//fetch all service packages to show
		List<ServicePackage> packages = spService.getAllPackages();
		
		//actually fetch orders
		List<Order> refusedOrders;
		if (user != null) refusedOrders = oService.getInvalidOrdersByClient(user.getUsername());
		else refusedOrders = new LinkedList<>();
		
		//give access to actual home page which should show the packages
		String path = "/WEB-INF/clientHome.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("error", error);
		ctx.setVariable("user", user);
		ctx.setVariable("packages", packages);
		ctx.setVariable("invOrders", refusedOrders);
		templateEngine.process(path, ctx, response.getWriter());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
