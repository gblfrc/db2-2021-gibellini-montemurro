package it.polimi.db2.project.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;

import it.polimi.db2.project.entities.OptionalProduct;
import it.polimi.db2.project.entities.ServicePackage;
import it.polimi.db2.project.entities.Subscription;
import it.polimi.db2.project.entities.ValidityPeriod;
import it.polimi.db2.project.services.OptService;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.services.SubService;
import it.polimi.db2.project.utils.TemplateEngineHandler;
import it.polimi.db2.project.utils.Error;

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
		int packId = 0;
		ServicePackage sp = null;
		try{
			packId = Integer.parseInt(request.getParameter("package"));
			//check existence of specified package
			sp = sps.getServicePackageById(packId);
			if (sp == null) throw new NoSuchElementException();
			sub.setPackage_(sp);
		} catch(Exception e) {
			Error error = new Error(HttpServletResponse.SC_NOT_FOUND, "Unavailable package");
			error.forward("/GetBuyPage", this, request, response);
			return;
		}
		
		//get validity period and link to sub
		int valPer = 0;
		ValidityPeriod validityPeriod = null;
		try{
			valPer = Integer.parseInt(request.getParameter("validityPeriod"));
			validityPeriod = sbs.getValidityPeriod(valPer);
			//check existence of specified validity period
			if (validityPeriod==null) throw new NoSuchElementException();
			sub.setValidityperiod(validityPeriod);
		} catch(Exception e) {
			Error error = new Error(HttpServletResponse.SC_NOT_FOUND, "Unavailable validity period");
			error.forward("/GetBuyPage", this, request, response);
			return;
		}
		
		//get starting date
		try{
			int startDay = Integer.parseInt(request.getParameter("startDay"));
			int startMonth = Integer.parseInt(request.getParameter("startMonth"));
			int startYear = Integer.parseInt(request.getParameter("startYear"));
			//check date validity (otherwise creates unrequested date)
			//e.g. providing Feb. 30th returns Mar. 2nd
			if (startDay < 1 || startDay > 31) throw new IllegalArgumentException("Illegal request: non-existent day");
			else if(startMonth < 1 || startMonth > 12) throw new IllegalArgumentException("Illegal request: non-existent month");
			else if (startDay > 30 && (startMonth == 4 || startMonth == 6 || startMonth == 9 || startMonth == 11))
				throw new IllegalArgumentException("Illegal request: non-existent date");
			else if(startMonth == 2 && (startDay > 29 || (startDay > 28 && startYear % 4 != 0) ))
				throw new IllegalArgumentException("Illegal request: non-existent date");
			//check date isn't antecedent current date
			Date startDate = new GregorianCalendar(startYear, startMonth - 1, startDay).getTime();
			if (startDate.before(Calendar.getInstance().getTime())) throw new IllegalArgumentException("Illegal request: starting date is prior to today");
			sub.setStartDate(startDate);
		} catch(Exception e) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			error.forward("/GetBuyPage", this, request, response);
			return;
		}

		// get optional product parameters, then products themselves and save to subscription
		List<OptionalProduct> products = new LinkedList<>();
		try{
			List<String> allParameters = Collections.list(request.getParameterNames());
			List<String> optProducts = new LinkedList<>();
			for(String param: allParameters) {
				if(param.contains("optProd")) optProducts.add(request.getParameter(param));
			}
			//check optional products are available for a given package
			if(!sp.hasAllProducts(optProducts)) throw new IllegalArgumentException();
			//fetch optional products objects
			String[] prodArray = new String[optProducts.size()];
			optProducts.toArray(prodArray);
			products = ops.findProductsSelected(prodArray);
			sub.setOptionalProductsSub(products);
		} catch(Exception e) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Invalid optional product request");
			error.forward("/GetBuyPage", this, request, response);
			return;
		}
		
		//calculate and set amount
		int amount = validityPeriod.getMonthlyFee()*valPer;
		for(OptionalProduct op: products) {
			amount = amount + op.getMonthlyFee()*valPer;
		}
		sub.setAmount(amount);
		
		//save subscription in session
		request.getSession().setAttribute("subscription", sub);
		
		//redirect to get confirmation page
		response.sendRedirect(getServletContext().getContextPath() + "/GetConfirmationPage");
		
	}

}
