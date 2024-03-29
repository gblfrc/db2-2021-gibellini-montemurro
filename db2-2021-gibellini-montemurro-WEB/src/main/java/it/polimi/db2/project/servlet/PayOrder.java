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
import it.polimi.db2.project.services.OrderService;
import it.polimi.db2.project.services.SubService;
import it.polimi.db2.project.utils.TemplateEngineHandler;
import it.polimi.db2.project.utils.Error;

@WebServlet("/PayOrder")
public class PayOrder extends HttpServlet {
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

		// get order related to subscription
		Order order = (Order) request.getSession().getAttribute("order");

		// check order is present
		if (order == null) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request: non-existent order");
			error.forward("/GetClientHomePage", this, request, response);
			return;
		}

		// "call external service" (analyze fail parameter and save updated order)
		boolean fail;
		try {
			String failStr = request.getParameter("fail").toLowerCase();
			if (!failStr.equals("true") && !failStr.equals("false")) throw new IllegalArgumentException();
			fail = Boolean.parseBoolean(request.getParameter("fail"));
		} catch (Exception e) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST,
					"Illegal request: impossible to attempt payment");
			error.forward("/GetClientHomePage", this, request, response);
			return;
		}
		if (fail) order.setRefusedPayments(order.getRefusedPayments() + 1);
		else order.setValidity(true);
		try {
			os.mergeOrder(order);
		} catch (Exception e) {
			Error error = new Error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An accidental error occurred");
			error.forward("/GetClientHomePage", this, request, response);
			return;
		}

		response.sendRedirect("GetActivationSchedule");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
		error.forward("/GetClientHomePage", this, request, response);
	}

}
