package com.ens.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ens.beans.CookieBean;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/bienvenue")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String direction = request.getParameter("direction");
		
		if (direction != null) {
		    switch (direction) {
		        case "administration":
		            response.addCookie(new Cookie("role", "admin"));
		            response.sendRedirect("/annuaire_ens/etudiants");
		            return;
		        case "utilisation":
		        	response.addCookie(new Cookie("role", "user"));
		        	response.sendRedirect("/annuaire_ens/etudiants");
		            return;
		        default:
		        	response.sendRedirect("/annuaire_ens/");
		        	return;
		    }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
