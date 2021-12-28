package it.polimi.db2.project.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.entities.Order;
import it.polimi.db2.project.services.OrderService;
import it.polimi.db2.project.utils.TemplateEngineHandler;
import it.polimi.db2.project.utils.Error;

@WebServlet("/RefetchOrder")
public class RefetchOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TemplateEngine templateEngine;

	@EJB
	OrderService os;

	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Order order = null;
		
		//try to fetch order
		try {
		int orId = Integer.parseInt(request.getParameter("orId"));
		System.out.println("Parsed integer");
		order = os.findOrderById(orId);
		} catch(NumberFormatException cce) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal parameter passed");
			error.forward("/GetClientHomePage", this, request, response);
			return;
		} catch (EJBException e) {
			Error error = new Error(HttpServletResponse.SC_NOT_FOUND, "Order not found");
			error.forward("/GetClientHomePage", this, request, response);
			return;
		}
		
		//check user has the rights to fetch specified order
		try {
			Client user = (Client)request.getSession().getAttribute("user");
			if(!user.getUsername().equals(order.getSubscription().getUser())) throw new IllegalArgumentException();
		} catch (Exception e) {
			Error error = new Error(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized resource access");
			error.forward("/GetClientHomePage", this, request, response);
			return;
		}
		
		//check the order is actually to refetch
		if(order.getValidity()) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
			error.forward("/GetClientHomePage", this, request, response);
			return;
		}
		
		//save order in session
		request.getSession().setAttribute("order", order);
		
		//redirect to get activation schedule
		response.sendRedirect("GetActivationSchedule");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}
