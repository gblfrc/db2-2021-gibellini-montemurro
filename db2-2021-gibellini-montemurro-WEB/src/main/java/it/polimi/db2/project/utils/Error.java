package it.polimi.db2.project.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Data;

public @Data class Error {
	
	int code;
	String message;
	
	public Error(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public void forward(String forwardTo, HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setAttribute("error", this);
		RequestDispatcher rd = servlet.getServletContext().getRequestDispatcher(forwardTo);
		rd.forward(request, response);
	}
}
