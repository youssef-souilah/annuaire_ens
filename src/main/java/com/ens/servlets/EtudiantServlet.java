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
		
		String direction=request.getContextPath();
		
		switch (direction) {
		case "/n": {
			
			
		}
		default:
			index(request,response);
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
		
		request.getRequestDispatcher("/WEB-INF/views/etudiants/index.jsp").forward(request, response);
	}

}
