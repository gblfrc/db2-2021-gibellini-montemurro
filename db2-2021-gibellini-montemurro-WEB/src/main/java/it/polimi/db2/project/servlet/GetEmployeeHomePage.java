package it.polimi.db2.project.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import it.polimi.db2.project.entities.Employee;
import it.polimi.db2.project.entities.OptionalProduct;
import it.polimi.db2.project.entities.Service;
import it.polimi.db2.project.services.OptService;
import it.polimi.db2.project.services.ServService;
import it.polimi.db2.project.utils.Error;
import it.polimi.db2.project.utils.TemplateEngineHandler;

/**
 * Servlet implementation class GetEmployeeHomePage
 */
@WebServlet("/GetEmployeeHomePage")
public class GetEmployeeHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB
	private OptService optService;
	@EJB
	private ServService servService;
	 
    public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Error error = (Error)request.getAttribute("error");
		List<OptionalProduct> optionalProducts= optService.findAllOptProducts();
		List<Service> services=servService.findAllServices();
		
		String path = "/WEB-INF/employeeHome.html";
		ServletContext servletContext = getServletContext();
		
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("user", (Employee)request.getSession().getAttribute("user"));
		ctx.setVariable("optionalProducts", optionalProducts);
		ctx.setVariable("services", services);
		ctx.setVariable("error", error);
		templateEngine.process(path, ctx, response.getWriter());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
