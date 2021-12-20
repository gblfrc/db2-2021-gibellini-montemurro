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
import com.google.gson.JsonArray;

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.entities.MvOptProd;
import it.polimi.db2.project.entities.OptionalProduct;
import it.polimi.db2.project.entities.ServicePackage;
import it.polimi.db2.project.services.AuditingService;
import it.polimi.db2.project.services.MvOptProdService;
import it.polimi.db2.project.services.MvPackageService;
import it.polimi.db2.project.services.OrderService;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.services.UserService;
import it.polimi.db2.project.utils.JSONConverter;

@WebServlet("/JSONProvider")
public class JSONProvider extends HttpServlet {
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

		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
		String toSend = null;

		String param = request.getParameter("package");
		if (param != null && !param.equals("")) {
			// retrieve package id
			int packId = Integer.parseInt(param);
			// retrieve package given id
			ServicePackage sp = spService.getServicePackageById(packId);
			// create array of JSON objects to contain strings
			JsonArray prods = new JsonArray();
			// build array
			for (OptionalProduct op : sp.getOptionalProducts()) {
				prods.add(op.getName());
			}
			//convert to string
			toSend=gson.toJson(prods);
		}

		param = request.getParameter("table");
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
				toSend = gson.toJson(JSONConverter.converter(suspendedOrders));
				break;
			case "alerts":
				List<Object[]> alerts = auditingService.findAlerts();
				toSend = gson.toJson(JSONConverter.converter(alerts));
				break;
			case "best":
				List<MvOptProd> bestSeller = mvOptProdService.findBestSeller();
				toSend = gson.toJson(bestSeller);
				break;
			}
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
