package it.polimi.db2.project.servlet;

import java.io.IOException;
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
import it.polimi.db2.project.entities.ServicePackage;
import it.polimi.db2.project.entities.ValidityPeriod;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.services.VPService;
import it.polimi.db2.project.utils.TemplateEngineHandler;
import it.polimi.db2.project.utils.Error;

@WebServlet("/GetBuyPage")
public class GetBuyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	TemplateEngine templateEngine;
	
	@EJB
	SpService sps;
	@EJB
	VPService vps;
	
	public void init() throws ServletException {
		templateEngine = TemplateEngineHandler.getEngine(getServletContext());
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get all service packages
		List<ServicePackage> packages = sps.getAllPackages();
		
		//fetch user from session
		Client user = (Client)request.getSession().getAttribute("user");
		
		//fetch all validity periods
		List<ValidityPeriod> periods = vps.getAllValidityPeriod();
		
		//fetch products for first package or create empty list
		List<OptionalProduct> products = null;
		try {
			products = packages.get(0).getOptionalProducts();
		} catch (Exception e) {
			products = new LinkedList<>();
		}
		
		//give access to actual home page which should show the packages
		String path = "/WEB-INF/buy.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("error", (Error)request.getAttribute("error"));
		ctx.setVariable("packages", packages);
		ctx.setVariable("user", user);
		ctx.setVariable("vperiods", periods);
		ctx.setVariable("products", products);
		templateEngine.process(path, ctx, response.getWriter());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
