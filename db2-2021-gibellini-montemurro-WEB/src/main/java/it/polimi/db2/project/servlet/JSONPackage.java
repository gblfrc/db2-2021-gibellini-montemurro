package it.polimi.db2.project.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import it.polimi.db2.project.entities.OptionalProduct;
import it.polimi.db2.project.entities.ServicePackage;
import it.polimi.db2.project.services.SpService;



@WebServlet("/JSONPackage")
public class JSONPackage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private SpService spService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
		String toSend = null;
		String param = request.getParameter("id");
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
		else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			toSend="Illegal request";
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
