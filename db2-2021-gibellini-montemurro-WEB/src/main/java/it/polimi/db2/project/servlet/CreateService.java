package it.polimi.db2.project.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.project.entities.Service;
import it.polimi.db2.project.services.ServService;
import it.polimi.db2.project.utils.Error;

@WebServlet("/CreateService")
public class CreateService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ServService ss;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal request");
		error.forward("/GetEmployeeHomePage", this, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// fetch and check type of service
		String type = request.getParameter("type");
		if (!type.equals("Fixed phone") && !type.equals("Mobile phone") && !type.equals("Fixed internet")
				&& !type.equals("Mobile internet")) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Unacceptable package type");
			error.forward("/GetEmployeeHomePage", this, request, response);
			return;
		}

		// check all parameters have been passed
		List<String> parameters = Collections.list(request.getParameterNames());
		System.out.println(parameters);
		if (!parameters.contains("igb") || !parameters.contains("egbf") || !parameters.contains("isms")
				|| !parameters.contains("esmsf") || !parameters.contains("im") || !parameters.contains("emf")) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal parameter passing");
			error.forward("/GetEmployeeHomePage", this, request, response);
			return;
		}

		// fetch parameters
		Integer igb, isms, im;
		Double egbf, esmsf, emf;
		try {
			String temp = request.getParameter("igb");
			igb = temp.equals("") ? null : Integer.valueOf(temp);
			temp = request.getParameter("egbf");
			egbf = temp.equals("") ? null : Double.valueOf(temp.replaceAll(",", "."));
			temp = request.getParameter("isms");
			isms = temp.equals("") ? null : Integer.valueOf(temp);
			temp = request.getParameter("esmsf");
			esmsf = temp.equals("") ? null : Double.valueOf(temp.replaceAll(",", "."));
			temp = request.getParameter("im");
			im = temp.equals("") ? null : Integer.valueOf(temp);
			temp = request.getParameter("emf");
			emf = temp.equals("") ? null : Double.valueOf(temp.replaceAll(",", "."));
		} catch (Exception e) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal value passed: enter only numbers");
			error.forward("/GetEmployeeHomePage", this, request, response);
			return;
		}

		// check validity of parameters
		try {
			if (type.equals("Fixed phone")
					&& (igb != null || egbf != null || isms != null || esmsf != null || im != null || emf != null))
				throw new IllegalArgumentException();
			else if (type.equals("Mobile phone") && (igb != null || egbf != null))
				throw new IllegalArgumentException();
			else if ((type.equals("Fixed internet") || type.equals("Mobile internet"))
					&& (isms != null || esmsf != null || im != null || emf != null))
				throw new IllegalArgumentException();
		} catch (Exception e) {
			Error error = new Error(HttpServletResponse.SC_BAD_REQUEST, "Illegal content for specified service type");
			error.forward("/GetEmployeeHomePage", this, request, response);
			return;
		}

		// try to create and persist service object
		Service service = new Service();
		service.setType(type);
		try {
			service.setIncludedGB(igb);
			service.setExtraGBFee(egbf);
			service.setIncludedSMS(isms);
			service.setExtraSMSFee(esmsf);
			service.setIncludedMinutes(im);
			service.setExtraMinutesFee(emf);
			ss.persistService(service);
		} catch (Exception e) {
			Error error = new Error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An accidental error occurred");
			error.forward("/GetEmployeeHomePage", this, request, response);
			return;
		}

		response.sendRedirect("GetEmployeeHomePage");
	}

}
