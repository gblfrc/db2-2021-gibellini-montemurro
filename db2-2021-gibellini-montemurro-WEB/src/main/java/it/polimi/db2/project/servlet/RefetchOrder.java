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
import it.polimi.db2.project.utils.TemplateEngineHandler;

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

		//get id order from the parameter
		int orId = Integer.parseInt(request.getParameter("orId"));
		
		//get order from just-found id
		Order order = os.findOrderById(orId);
		
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
