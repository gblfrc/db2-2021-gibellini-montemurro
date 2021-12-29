package it.polimi.db2.project.servlet;

import java.io.IOException;

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
import it.polimi.db2.project.entities.Subscription;
import it.polimi.db2.project.services.OptService;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.services.SubService;
import it.polimi.db2.project.utils.Error;
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
		
		//check subscription isn't null
		if(sub==null) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request: non-existent subscription");
			error.forward("/GetClientHomePage", this, request, response);
			return;
		}
		
		//check the sub has a client, if not try to set it
		Client client = (Client)request.getSession().getAttribute("user");
		if (client!=null) {
			if (sub.getUser() == null) sub.setUser(client.getUsername());
			//handle case in which the user has changed profile after receiving the page once
			else if (!sub.getUser().equals(client.getUsername())) {
				request.getSession().removeAttribute("subscription");
				Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal access");
				error.forward("/GetClientHomePage", this, request, response);
				return;
			}
		}
		
		//give access to actual home page which should show the packages
		String path = "/WEB-INF/confirmation.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("user", client);
		ctx.setVariable("order", null);
		ctx.setVariable("sub", sub);
		ctx.setVariable("client", sub.getUser());
		templateEngine.process(path, ctx, response.getWriter());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
	}


}
