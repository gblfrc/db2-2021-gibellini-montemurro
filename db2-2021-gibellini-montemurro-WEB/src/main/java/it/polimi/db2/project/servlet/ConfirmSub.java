package it.polimi.db2.project.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;

import it.polimi.db2.project.entities.Order;
import it.polimi.db2.project.entities.Subscription;
import it.polimi.db2.project.services.OrderService;
import it.polimi.db2.project.services.SubService;
import it.polimi.db2.project.utils.TemplateEngineHandler;

@WebServlet("/ConfirmSub")
public class ConfirmSub extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TemplateEngine templateEngine;

	@EJB
	SubService sbs;
	@EJB
	OrderService os;

	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//fetch subscription object
		Subscription sub = (Subscription)request.getSession().getAttribute("subscription");
		
		//save subscription on DB
		sbs.persistSubscription(sub);
		
		//get order related to subscription
		Order order = os.getOrderBySubscription(sub);
		request.getSession().setAttribute("order", order);
		
		//"call external service" (analyze fail parameter and save updated order)
		boolean fail = Boolean.parseBoolean(request.getParameter("fail"));
		if (fail) order.setRefusedPayments(order.getRefusedPayments()+1);
		else order.setValidity(true);
		os.persistOrder(order);
		
		response.sendRedirect(getServletContext().getContextPath() + "/GetActivationSchedule");
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
	}


}
