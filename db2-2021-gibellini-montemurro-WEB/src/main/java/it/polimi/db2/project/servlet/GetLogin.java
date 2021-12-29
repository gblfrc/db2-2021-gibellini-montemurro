package it.polimi.db2.project.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import it.polimi.db2.project.utils.TemplateEngineHandler;
import it.polimi.db2.project.utils.Error;

@WebServlet("/GetLogin")
public class GetLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
       
    public void init() throws ServletException {
    	templateEngine = TemplateEngineHandler.getEngine(getServletContext());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//fetch error and success (if present, otherwise error is null)
		Error success = (Error)request.getSession().getAttribute("success");
		request.getSession().removeAttribute("success");
		Error error = (Error)request.getSession().getAttribute("logError");	
		if (error!=null) request.getSession().removeAttribute("logError");
		else error = (Error)request.getAttribute("error");
		
		//give access to landing page (page for login or registration)
		String path = "/WEB-INF/index.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("error", error);
		ctx.setVariable("success", success);	
		templateEngine.process(path, ctx, response.getWriter());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
