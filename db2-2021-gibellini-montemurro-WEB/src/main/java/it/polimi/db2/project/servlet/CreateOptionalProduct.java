package it.polimi.db2.project.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;

import it.polimi.db2.project.services.OptService;
import it.polimi.db2.project.utils.TemplateEngineHandler;


@WebServlet("/CreateOptionalProduct")
public class CreateOptionalProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB
	private OptService optService;	
	
	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name;
		int monthlyFee;

		name = request.getParameter("name");
		monthlyFee = Integer.parseInt(request.getParameter("monthlyFee"));
		

		optService.addOptionalProduct(name,monthlyFee);
		response.sendRedirect("GetEmployeeHomePage");
	}
}
