package it.polimi.db2.project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.polimi.db2.project.utils.Error;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			//log out user and destroy other parameters (sub, order)
			session.removeAttribute("user");
			session.removeAttribute("subscription");
			session.removeAttribute("order");
			Error success = new Error(HttpServletResponse.SC_OK, "Successful logout");
			//save success in session
			session.setAttribute("success", success);
		}
		response.sendRedirect(getServletContext().getContextPath() + "/GetLogin");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
