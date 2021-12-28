package it.polimi.db2.project.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.project.entities.OptionalProduct;
import it.polimi.db2.project.entities.Service;
import it.polimi.db2.project.services.OptService;
import it.polimi.db2.project.services.ServService;
import it.polimi.db2.project.services.SpService;
import it.polimi.db2.project.utils.Error;

/**
 * Servlet implementation class CreateServicePackage
 */
@WebServlet("/CreateServicePackage")
public class CreateServicePackage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private SpService spService;
	
	@EJB
	private OptService optService;
	
	@EJB
	private ServService servService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
		error.forward("/GetEmployeeHomePage", this, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name;
		String[] optProdList;
		String[] serviceList;
		try {
			name= request.getParameter("name");
			optProdList= request.getParameterValues("typeOptional");
			serviceList= request.getParameterValues("typeService");
			if(name.equals("")||name==null||name.substring(0,1).equals(" ")||serviceList==null)throw new Exception();
		}catch(Exception e) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal service package request");
			error.forward("/GetEmployeeHomePage", this, request, response);
			return;
		}
		
		List<OptionalProduct>optionals=null;
		try {
			if(optProdList!=null)optionals= optService.findProductsSelected(optProdList);
		}catch(Exception e) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Unavailable optional product");
			error.forward("/GetEmployeeHomePage", this, request, response);
			return;
		}
		List<Service>services;
		try {
			services= servService.findServicesSelected(serviceList);
		}catch(Exception e) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Unavailable service");
			error.forward("/GetEmployeeHomePage", this, request, response);
			return;
		}	
		
		try {
			spService.addServicePackage(name,services,optionals);
		}catch(Exception e) {
			Error error = new Error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An accidental error occurred, couldn't add service package");
			error.forward("/GetEmployeeHomePage", this, request, response);
			return;
		}
		
		response.sendRedirect("GetEmployeeHomePage");
	}

}
