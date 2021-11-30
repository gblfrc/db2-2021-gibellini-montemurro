package it.polimi.db2.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.entities.OptionalProduct;
import it.polimi.db2.project.entities.Subscription;
import it.polimi.db2.project.entities.ValidityPeriod;
import it.polimi.db2.project.services.OptService;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.services.SubService;
import it.polimi.db2.project.utils.TemplateEngineHandler;

@WebServlet("/GetConfirmationPage")
public class GetConfirmationPage extends HttpServlet {
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

		//fetch subscription object
		Subscription sub = (Subscription)request.getSession().getAttribute("subscription");
		
		//get deactivation date (needed for template)
		Date deactivationDate = sub.getDeactivationDate();
			
		//check the sub has a client, if not try to set it
		Client client = sub.getUser();
		if (client == null) {
			client = (Client)request.getSession().getAttribute("user");
			if (client != null) sub.setUser(client);
		}
		
		//give access to actual home page which should show the packages
		String path = "/WEB-INF/confirmation.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("sub", sub);
		ctx.setVariable("deactivationDate", deactivationDate);
		ctx.setVariable("client", client);
		templateEngine.process(path, ctx, response.getWriter());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
	}


}
