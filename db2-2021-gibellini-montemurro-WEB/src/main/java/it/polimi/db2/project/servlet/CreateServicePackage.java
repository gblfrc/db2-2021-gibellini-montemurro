package it.polimi.db2.project.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;

import it.polimi.db2.project.entities.OptionalProduct;
import it.polimi.db2.project.entities.Service;
import it.polimi.db2.project.services.OptService;
import it.polimi.db2.project.services.ServService;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.utils.TemplateEngineHandler;

/**
 * Servlet implementation class CreateServicePackage
 */
@WebServlet("/CreateServicePackage")
public class CreateServicePackage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
       
	@EJB
	private SpService spService;
	
	@EJB
	private OptService optService;
	
	@EJB
	private ServService servService;
	
	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name= request.getParameter("name");
		String[] optProdList= request.getParameterValues("typeOptional");
		String[] serviceList= request.getParameterValues("typeService");
		
		List<OptionalProduct>optionals= optService.findProductsSelected(optProdList);
		List<Service>services= servService.findServicesSelected(serviceList);
		
		spService.addServicePackage(name,services,optionals);
		response.sendRedirect("GetEmployeeHomePage");
	}

}
