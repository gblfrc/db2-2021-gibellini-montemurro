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

import it.polimi.db2.project.entities.AuditingTable;
import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.entities.MvOptProd;
import it.polimi.db2.project.services.AuditingService;
import it.polimi.db2.project.services.MvOptProdService;
import it.polimi.db2.project.services.MvPackageService;
import it.polimi.db2.project.services.OrderService;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.services.UserService;
import it.polimi.db2.project.utils.JSONConverter;

@WebServlet("/JSONTable")
public class JSONTable extends HttpServlet {
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

		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		String toSend = null;
		
		String param = request.getParameter("table");
		
		if (param != null && !param.equals("")) {
			switch (param.toLowerCase()) {
			case "perpackage":
				List<Object[]> allSalesPerPackage = mvPackageService.findTotPurchase();
				toSend = gson.toJson(JSONConverter.converter(allSalesPerPackage));
				break;
			case "pervalidity":
				List<Object[]> allSalesPerValidityAndPackage = mvPackageService
						.findAllPurchasesPerPackageAndValidityPeriod();
				toSend = gson.toJson(JSONConverter.converter(allSalesPerValidityAndPackage));
				break;
			case "withoutopt":
				List<Object[]> revWoOpt = mvPackageService.findTotRevWoOpt();
				toSend = gson.toJson(JSONConverter.converter(revWoOpt));
				break;
			case "withopt":
				List<Object[]> revWOpt = mvPackageService.findTotRevWOpt();
				toSend = gson.toJson(JSONConverter.converter(revWOpt));
				break;
			case "average":
				List<Object[]> avgOpt = mvPackageService.findAvgOpt();
				toSend = gson.toJson(JSONConverter.converter(avgOpt));
				break;
			case "insolvent":
				List<Client> insolvents = uService.findInsolvents();
				toSend = gson.toJson(insolvents);
				break;
			case "suspended":
				List<Object[]> suspendedOrders = orderService.getSuspendedOrders();
				for(Object[] obj: suspendedOrders) {
					System.out.println(obj);
				}
				toSend = gson.toJson(JSONConverter.converter(suspendedOrders));
				break;
			case "alerts":
				List<AuditingTable> alerts = auditingService.findAlerts();
				toSend = gson.toJson(alerts);
				for(AuditingTable obj: alerts) {
					System.out.println(obj);
				}
				System.out.println("---------------------------------------------------");
				System.out.println(toSend);
				System.out.println("---------------------------------------------------");
				break;
			case "best":
				List<MvOptProd> bestSeller = mvOptProdService.findBestSeller();
				toSend = gson.toJson(bestSeller);
				break;
			default:
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				toSend="Illegal request";
				break;
			}
		}
		else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			toSend="Illegal request";
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(toSend);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
