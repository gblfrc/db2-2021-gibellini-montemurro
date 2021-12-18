package it.polimi.db2.project.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.entities.Employee;
import it.polimi.db2.project.entities.MvOptProd;
import it.polimi.db2.project.entities.MvPackage;
import it.polimi.db2.project.services.AuditingService;
import it.polimi.db2.project.services.MvOptProdService;
import it.polimi.db2.project.services.MvPackageService;
import it.polimi.db2.project.services.OrderService;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.services.UserService;

/**
 * Servlet implementation class GetSalesPage
 */
@WebServlet("/GetSalesPage")
public class GetSalesPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserService uService;
	@EJB
	private SpService spService;
	@EJB
	private OrderService orderService;  	
	@EJB
	private MvPackageService mvPackageService;  
	@EJB
	private MvOptProdService mvOptProdService;  
	@EJB
	private AuditingService auditingService; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {					
		List<Object[]> allSalesDataPerPackage=mvPackageService.findAllPurchasesPerPackage();
		List<MvPackage> allSalesPerValidityAndPackage= mvPackageService.findAllPurchasesPerPackageAndValidityPeriod();
		List<Client> insolvents= uService.findInsolvents();
		List<Object[]> suspendedOrders= orderService.getSuspendedOrders();
		List<Object[]> alerts=auditingService.findAlerts();
		List<MvOptProd> bestSeller=mvOptProdService.findBestSeller();

		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
		String json1 = gson.toJson(allSalesDataPerPackage);
		String json2 = gson.toJson(allSalesPerValidityAndPackage);
		String json3 = gson.toJson(insolvents);
		String json4 = gson.toJson(suspendedOrders);
		String json5 = gson.toJson(alerts);
		String json6 = gson.toJson(bestSeller);
		String json7 = gson.toJson((Employee)request.getSession().getAttribute("user"));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
				
		response.getWriter().write(json1);
		response.getWriter().write(json2);
		response.getWriter().write(json3);
		response.getWriter().write(json4);
		response.getWriter().write(json5);
		response.getWriter().write(json6);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
