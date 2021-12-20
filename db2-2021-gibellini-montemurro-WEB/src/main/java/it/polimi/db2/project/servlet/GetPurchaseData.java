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
import it.polimi.db2.project.entities.MvOptProd;
import it.polimi.db2.project.entities.MvPackage;
import it.polimi.db2.project.services.AuditingService;
import it.polimi.db2.project.services.MvOptProdService;
import it.polimi.db2.project.services.MvPackageService;
import it.polimi.db2.project.services.OrderService;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.services.UserService;

@WebServlet("/GetPurchaseData")
public class GetPurchaseData extends HttpServlet {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selected = request.getParameter("selected");
		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		List<Object[]> allSalesPerPackage;
		List<Object[]> allSalesPerValidityAndPackage;
		List<Object[]> revWoOpt;
		List<Object[]> revWOpt;
		List<Object[]> avgOpt;
		List<Client> insolvents;
		List<Object[]> suspendedOrders;
		List<Object[]> alerts;
		List<MvOptProd> bestSeller;
		String json1=null;
		
		switch (selected.toLowerCase()) {
			case "perpackage":
				allSalesPerPackage = mvPackageService.findTotPurchase();
				json1 = gson.toJson(allSalesPerPackage);
				break;
			case "pervalidity":
				allSalesPerValidityAndPackage = mvPackageService.findAllPurchasesPerPackageAndValidityPeriod();
				json1 = gson.toJson(allSalesPerValidityAndPackage);
				break;
			case "withoutopt":
				revWoOpt = mvPackageService.findTotRevWoOpt();
				json1 = gson.toJson(revWoOpt);
				break;
			case "withopt":
				revWOpt = mvPackageService.findTotRevWOpt();
				json1 = gson.toJson(revWOpt);
				break;
			case "average":
				avgOpt = mvPackageService.findAvgOpt();
				json1 = gson.toJson(avgOpt);
				break;
			case "insolvent":
				insolvents = uService.findInsolvents();
				json1 = gson.toJson(insolvents);
				break;
			case "suspended":
				suspendedOrders = orderService.getSuspendedOrders();
				json1 = gson.toJson(suspendedOrders);
				break;
			case "alerts":
				alerts = auditingService.findAlerts();
				json1 = gson.toJson(alerts);
				break;
			case "best":
				bestSeller = mvOptProdService.findBestSeller();
				json1 = gson.toJson(bestSeller);
				break;
		}
		
		response.getWriter().write(json1);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
