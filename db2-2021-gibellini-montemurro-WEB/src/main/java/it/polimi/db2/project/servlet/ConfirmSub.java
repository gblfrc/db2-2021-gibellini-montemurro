package it.polimi.db2.project.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
import it.polimi.db2.project.utils.Error;

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
		
		//check subscription isn't null
		if(sub==null) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request: non-existent subscription");
			error.forward("/GetClientHomePage", this, request, response);
			return;
		}
		
		//check user has requested payment
		if(request.getParameter("fail")==null) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request: non-requested payment");
			error.forward("/GetClientHomePage", this, request, response);
			return;
		}
		
		//save subscription on DB
		sbs.persistSubscription(sub);
		
		//get order related to subscription
		Order order = os.getOrderBySubscription(sub);
		request.getSession().setAttribute("order", order);
		request.getSession().removeAttribute("subscription");
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/PayOrder");
		rd.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
		error.forward("/GetClientHomePage", this, request, response);
	}


}
