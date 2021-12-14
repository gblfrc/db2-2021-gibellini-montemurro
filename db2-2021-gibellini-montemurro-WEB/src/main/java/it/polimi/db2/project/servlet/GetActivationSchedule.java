package it.polimi.db2.project.servlet;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.entities.Order;
import it.polimi.db2.project.entities.Subscription;
import it.polimi.db2.project.services.OptService;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.services.SubService;
import it.polimi.db2.project.utils.Error;
import it.polimi.db2.project.utils.TemplateEngineHandler;

@WebServlet("/GetActivationSchedule")
public class GetActivationSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TemplateEngine templateEngine;

	@EJB
	SpService sps;
	@EJB
	OptService ops;
	@EJB
	SubService sbs;

	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//fetch subscription object and remove it from session
		//Subscription sub = (Subscription)request.getSession().getAttribute("subscription");
		//request.getSession().removeAttribute("subscription");
		
		//fetch order object and remove it from session
		Order order = (Order)request.getSession().getAttribute("order");
		
		if(order==null) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request: non-existent order");
			error.forward("/GetUserHomePage", this, request, response);
			return;
		}
		
		Subscription sub = order.getSubscription();
		//request.getSession().removeAttribute("order");
		
		//get deactivation date (needed for template)
		Date deactivationDate = sub.getDeactivationDate();
		
		//give access to actual home page which should show the activation schedule
		String path = "/WEB-INF/activationSchedule.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("user", (Client)request.getSession().getAttribute("user"));
		ctx.setVariable("sub", sub);
		ctx.setVariable("order", order);
		ctx.setVariable("deactivationDate", deactivationDate);
		templateEngine.process(path, ctx, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}
