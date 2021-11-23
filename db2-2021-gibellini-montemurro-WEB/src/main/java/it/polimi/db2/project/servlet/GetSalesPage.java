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

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.services.UserService;
import it.polimi.db2.project.utils.TemplateEngineHandler;

/**
 * Servlet implementation class GetSalesPage
 */
@WebServlet("/GetSalesPage")
public class GetSalesPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB
	private UserService uService;
       	
	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Client> insolvents= uService.findInsolvents();
		
		String path = "/WEB-INF/sales.html";
		ServletContext servletContext = getServletContext();
		
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("insolvents", insolvents);
		templateEngine.process(path, ctx, response.getWriter());
	}
}
