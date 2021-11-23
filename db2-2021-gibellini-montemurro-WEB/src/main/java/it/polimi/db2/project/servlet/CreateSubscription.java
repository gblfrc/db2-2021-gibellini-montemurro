package it.polimi.db2.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
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

import it.polimi.db2.project.entities.OptionalProduct;
import it.polimi.db2.project.entities.Subscription;
import it.polimi.db2.project.entities.ValidityPeriod;
import it.polimi.db2.project.services.OptService;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.services.SubService;
import it.polimi.db2.project.utils.TemplateEngineHandler;

@WebServlet("/CreateSubscription")
public class CreateSubscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TemplateEngine templateEngine;

	@EJB
	SpService sps;
	@EJB
	OptService ops;
	@EJB
	SubService sbs;

	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//create subscription object
		Subscription sub = new Subscription();
		
		//get package to link to subscription (and set to sub)
		int packId = Integer.parseInt(request.getParameter("package"));
		sub.setPackage_(packId);
		
		//get validity period and link to sub
		int valPer = Integer.parseInt(request.getParameter("validityPeriod"));
		ValidityPeriod validityPeriod = sbs.getValidityPeriod(valPer);
		sub.setValidityperiod(validityPeriod);
		
		//get starting date
		int startDay = Integer.parseInt(request.getParameter("startDay"));
		int startMonth = Integer.parseInt(request.getParameter("startMonth"));
		int startYear = Integer.parseInt(request.getParameter("startYear"));
		sub.setStartDate(new GregorianCalendar(startYear, startMonth - 1, startDay).getTime());
		
		// get optional product parameters, then products themselves and save to subscription
		List<String> allParameters = Collections.list(request.getParameterNames());
		List<String> optProducts = new LinkedList<>();
		for(String param: allParameters) {
			if(param.contains("optProd")) optProducts.add(request.getParameter(param));
		}
		String[] prodArray = new String[optProducts.size()];
		for(int i=0; i<optProducts.size(); i++) {
			prodArray[i] = optProducts.get(i);
		}
		List<OptionalProduct> products = ops.findProductsSelected(prodArray);
		sub.setOptionalProductsSub(products);
		
		//calculate and set amount
		int amount = validityPeriod.getMonthlyFee()*valPer;
		for(OptionalProduct op: products) {
			amount = amount + op.getMonthlyFee()*valPer;
		}
		sub.setAmount(amount);
		
		//give access to actual home page which should show the packages
		//String path = "/WEB-INF/prova.html";
		//ServletContext servletContext = getServletContext();
		//final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		//ctx.setVariable("sub", sub);
		//templateEngine.process(path, ctx, response.getWriter());

	}

}
