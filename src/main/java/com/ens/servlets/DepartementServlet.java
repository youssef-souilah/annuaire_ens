package com.ens.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.ens.models.Departement;
import com.ens.models.Etudiant;
import com.ens.models.Filiere;
import com.ens.repositories.DepartementRepository;

/**
 * Servlet implementation class DepartementServlet
 */
public class DepartementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartementRepository reporisory;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartementServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.reporisory=new DepartementRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String direction=request.getRequestURI();
		String[] uriParts = direction.split("/");
        direction = uriParts[uriParts.length - 1];
		switch (direction) {
			/*
			case "rechercher": {
				rechercher(request, response);
				break;
			}
			*/
			case "modifier": {
				modifier(request, response);
				break;
			}
			case "supprimer": {
				supprimer(request, response);
				break;
			}
			case "etudiants": {
				index(request,response);
				break;
			}
			case "ajouter": {
				ajouter(request,response);
				break;
			}
			case "editer": {
				try {
					editer(request,response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			default:
				response.sendRedirect("/annuaire_ens/departements");
				break;

		}
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
		
			
			try {
				if (search==null) {
					request.setAttribute("list", this.reporisory.findAll());
				}
				else {
					request.setAttribute("list", this.reporisory.findByLongName(search));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		
		request.getRequestDispatcher("/WEB-INF/views/deparetements/insertDepartement.jsp").forward(request, response);
	}
	protected void modifier(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		if (request.getParameter("id") == null) {
			
			response.sendRedirect("/annuaire_ens/departements");
			return;
		}
		else {
			try {
			    Long id = Long.parseLong(request.getParameter("id"));
			    try {
			    	if(this.reporisory.findById(id)==null) {
			    		response.sendRedirect("/annuaire_ens/departements");
			    		return;
			    	}
					request.setAttribute("list", this.reporisory.findAll());
					request.setAttribute("departement",this.reporisory.findById(id));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			} catch (NumberFormatException e) {
				response.sendRedirect("/annuaire_ens/departements");
				return;
			}
		}
		request.getRequestDispatcher("/WEB-INF/views/departements/insertDepartement.jsp").forward(request, response);
	}
	/*
	protected void rechercher(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String search=request.getParameter("param");
		if (search!=null) {
			
			try {
				request.setAttribute("item", this.reporisory.findByLongName(search));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		request.getRequestDispatcher("/WEB-INF/views/etudiants/rechercherEtudiant.jsp").forward(request, response);
	}
	*/
	
	protected void ajouter(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		boolean res=false;
		String nom = request.getParameter("nom");
		Departement departement = new Departement();
		departement.setNom(nom);
		try {
			res=this.reporisory.save(departement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(res) {
			String message = "Departement ajoutee";
			String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
			Cookie cookie = new Cookie("message", encodedMessage);
	        cookie.setMaxAge(3); 
	        response.addCookie(cookie);
		}
		else {
			String message = "Erreur est survenue ";
			String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
			Cookie cookie = new Cookie("erreur", encodedMessage);
	        cookie.setMaxAge(3); 
	        response.addCookie(cookie);
		}
		response.sendRedirect("/annuaire_ens/departements");
	}
	protected void editer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		if (request.getParameter("id") == null) {
			
			response.sendRedirect("/annuaire_ens/departements");
			return;
		}
		Long id = Long.parseLong(request.getParameter("id"));
		Departement departement = this.reporisory.findById(id);
		if(departement==null) {
			String message = "Departement invalide";
			String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
			Cookie cookie = new Cookie("erreur", encodedMessage);
	        cookie.setMaxAge(3); 
	        response.addCookie(cookie);
    		response.sendRedirect("/annuaire_ens/departements");
			return;
		}
		boolean res=false;
		String nom = request.getParameter("nom");
		departement.setNom(nom);
		try {
			res=this.reporisory.update(departement);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(res) {
			String message = "Departement modifiee";
			String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
			Cookie cookie = new Cookie("message", encodedMessage);
	        cookie.setMaxAge(3); 
	        response.addCookie(cookie);
		}
		else {
			String message = "Erreur est survenue ";
			String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
			Cookie cookie = new Cookie("erreur", encodedMessage);
	        cookie.setMaxAge(3); 
	        response.addCookie(cookie);
		}
		response.sendRedirect("/annuaire_ens/departements");
	}
	protected void supprimer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		if (request.getParameter("id") == null) {
					
			response.sendRedirect("/annuaire_ens/departements");
			return;
		}
		else {
			try {
			    Long id = Long.parseLong(request.getParameter("id"));
			    try {
			    	Departement departement=this.reporisory.findById(id);
			    	if(departement!= null) {
			    		this.reporisory.delete(departement);
			    		String message = "Departement supprimee";
						String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
						Cookie cookie = new Cookie("message", encodedMessage);
				        cookie.setMaxAge(3); 
				        response.addCookie(cookie);
			    		response.sendRedirect("/annuaire_ens/departements");
						return;
			    	}
			    	else {
			    		String message = "Departement invalide";
						String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
						Cookie cookie = new Cookie("erreur", encodedMessage);
				        cookie.setMaxAge(3); 
				        response.addCookie(cookie);
			    		response.sendRedirect("/annuaire_ens/departements");
						return;
			    	}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			} catch (NumberFormatException e) {
				String message = "Erreur est survenue";
				String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
				Cookie cookie = new Cookie("erreur", encodedMessage);
		        cookie.setMaxAge(3); 
		        response.addCookie(cookie);
				response.sendRedirect("/annuaire_ens/departements");
				return;
			}
		}
	}

}
