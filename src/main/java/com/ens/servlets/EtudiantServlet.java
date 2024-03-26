package com.ens.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ens.models.Etudiant;
import com.ens.repositories.EtudiantRepository;

/**
 * Servlet implementation class EtudiantServlet
 */

public class EtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EtudiantRepository reporisory;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EtudiantServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.reporisory=new EtudiantRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String direction=request.getRequestURI();
		String[] uriParts = direction.split("/");
        direction = uriParts[uriParts.length - 1];
        request.setAttribute("d", request.getParameter("id"));
		switch (direction) {
			case "rechercher": {
				rechercher(request, response);
				break;
			}
			case "modifier": {
				modifier(request, response);
				break;
			}
			case "etudiants": {
				index(request,response);
				break;
			}
			default:
				request.getRequestDispatcher("/WEB-INF/views/test.jsp").forward(request, response);
				break;

		}
		
		//
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void index(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String search=request.getParameter("search");
		if (search==null) {
			
			try {
				request.setAttribute("list", this.reporisory.findAll());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		request.getRequestDispatcher("/WEB-INF/views/etudiants/insertEtudiant.jsp").forward(request, response);
	}
	protected void modifier(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		if (request.getParameter("id") == null) {
			
			response.sendRedirect("/annuaire_ens/etudiants");
			return;
		}
		else {
			try {
			    int id = Integer.parseInt(request.getParameter("id"));
			    try {
					request.setAttribute("list", this.reporisory.findAll());
					request.setAttribute("etudiant",this.reporisory.findById(id));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			} catch (NumberFormatException e) {
				response.sendRedirect("/annuaire_ens/etudiants");
				return;
			}
		}
		request.getRequestDispatcher("/WEB-INF/views/etudiants/insertEtudiant.jsp").forward(request, response);
	}
	protected void rechercher(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String search=request.getParameter("param");
		if (search!=null) {
			String[] items = search.split(" ");
			try {
				request.setAttribute("item", this.reporisory.findByLongName(items[0],items[1]));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		request.getRequestDispatcher("/WEB-INF/views/etudiants/rechercher.jsp").forward(request, response);
	}

}
