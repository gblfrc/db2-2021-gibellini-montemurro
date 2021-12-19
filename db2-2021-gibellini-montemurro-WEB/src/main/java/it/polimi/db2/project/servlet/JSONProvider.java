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

@WebServlet("/JSONProvider")
public class JSONProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	SpService sps;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int packId = Integer.parseInt(request.getParameter("package"));
		ServicePackage sp = sps.getServicePackageById(packId);
		
		JsonArray prods = new JsonArray();
		
		for(OptionalProduct op : sp.getOptionalProducts()) {
			prods.add(op.getName());
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(prods));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
