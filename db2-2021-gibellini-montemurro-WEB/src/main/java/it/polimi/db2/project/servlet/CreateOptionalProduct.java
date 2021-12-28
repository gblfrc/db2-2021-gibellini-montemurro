package it.polimi.db2.project.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.project.services.OptService;
import it.polimi.db2.project.utils.Error;


@WebServlet("/CreateOptionalProduct")
public class CreateOptionalProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private OptService optService;	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
		error.forward("/GetEmployeeHomePage", this, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name;
		int monthlyFee;
		try {
			name = request.getParameter("name");
			monthlyFee = Integer.parseInt(request.getParameter("monthlyFee"));
			if(name.equals("")||name==null||name.substring(0,1).equals(" "))throw new Exception();
		}catch(Exception e) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal optional product request");
			error.forward("/GetEmployeeHomePage", this, request, response);
			return;
		}

		try {
			optService.addOptionalProduct(name,monthlyFee);
		}catch(Exception e) {
			Error error = new Error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An accidental error occurred, couldn't add optional product");
			error.forward("/GetEmployeeHomePage", this, request, response);
			return;
		}
		response.sendRedirect("GetEmployeeHomePage");
	}
}
