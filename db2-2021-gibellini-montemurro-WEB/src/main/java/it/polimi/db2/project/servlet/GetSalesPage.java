package it.polimi.db2.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import it.polimi.db2.project.entities.ServicePackage;
import it.polimi.db2.project.services.OrderService;
import it.polimi.db2.project.services.ServService;
import it.polimi.db2.project.services.SpService;
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
	@EJB
	private SpService spService;
	@EJB
	private OrderService orderService;  	
	
	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		List<ServicePackage> packages=spService.getAllPackages();
		List<Object[]> allSales= orderService.findAllPurchasesPerPackage();
		List<Object[]> allSalesPerValidityAndPackage= orderService.findAllPurchasesPerPackageAndValidityPeriod();
		List<Object[]> salesAmountWithOpt=orderService.getAmountWithOpt();
		List<Client> insolvents= uService.findInsolvents();
		List<Order> suspendedOrders= orderService.getSuspendedOrders();
		
		
		String path = "/WEB-INF/sales.html";
		ServletContext servletContext = getServletContext();
		
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("allSales", allSales);
		ctx.setVariable("allSalesPerValidityAndPackage", allSalesPerValidityAndPackage);
		ctx.setVariable("salesAmountWithOpt", salesAmountWithOpt);
		ctx.setVariable("insolvents", insolvents);
		ctx.setVariable("suspendedOrders", suspendedOrders);

		templateEngine.process(path, ctx, response.getWriter());
	}
}
