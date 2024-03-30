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
import com.ens.repositories.FiliereRepository;

/**
 * Servlet implementation class FiliereServlet
 */
public class FiliereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FiliereRepository repository;
	private DepartementRepository departemnetReporisory;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiliereServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.repository=new FiliereRepository();
        this.departemnetReporisory=new DepartementRepository();
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
			case "filieres": {
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
				response.sendRedirect("/annuaire_ens/filieres");
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
		String departement= request.getParameter("departement_id");
		try {
			if (search==null||departement==null) {
				request.setAttribute("list", this.repository.findAll());
			}
			else if(departement!=null){
				Long departementId = Long.parseLong(departement);
				request.setAttribute("list", this.repository.findByDepartementId(departementId));
			}
			else {
				request.setAttribute("list", this.repository.findByLongName(search));
			}
			request.setAttribute("departements", this.departemnetReporisory.findAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		request.getRequestDispatcher("/WEB-INF/views/filieres/insertFiliere.jsp").forward(request, response);
	}
	protected void modifier(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		if (request.getParameter("id") == null) {
			
			response.sendRedirect("/annuaire_ens/filieres");
			return;
		}
		else {
			try {
			    Long id = Long.parseLong(request.getParameter("id"));
			    try {
			    	if(this.repository.findById(id)==null) {
			    		response.sendRedirect("/annuaire_ens/filieres");
			    		return;
			    	}
					request.setAttribute("list", this.repository.findAll());
					request.setAttribute("filiere",this.repository.findById(id));
					request.setAttribute("departements", this.departemnetReporisory.findAll());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			} catch (NumberFormatException e) {
				response.sendRedirect("/annuaire_ens/filieres");
				return;
			}
		}
		request.getRequestDispatcher("/WEB-INF/views/filieres/insertFiliere.jsp").forward(request, response);
	}
	/*
	protected void rechercher(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String search=request.getParameter("param");
		if (search!=null) {
			try {
				request.setAttribute("item", this.repository.findByLongName(search));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		request.getRequestDispatcher("/WEB-INF/views/filieres/rechercherFiliere.jsp").forward(request, response);
	}
	*/
	protected void ajouter(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		boolean res=false;
		String nom = request.getParameter("nom");
		Departement departement = new Departement();
		Long departementId =Long.parseLong(request.getParameter("departement_id"));
		Filiere filiere = new Filiere();
		filiere.setNom(nom);
		filiere.setDepartementId(departementId);
		
		try {
			res=this.repository.save(filiere);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(res) {
			String message = "Filiere ajoutee";
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
		response.sendRedirect("/annuaire_ens/filieres");
	}
	protected void editer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		if (request.getParameter("id") == null) {
			response.sendRedirect("/annuaire_ens/filieres");
			return;
		}
		Long id = Long.parseLong(request.getParameter("id"));
		Filiere filiere = this.repository.findById(id);
		if(filiere==null) {
			String message = "Filiere invalide";
			String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
			Cookie cookie = new Cookie("erreur", encodedMessage);
	        cookie.setMaxAge(3); 
	        response.addCookie(cookie);
    		response.sendRedirect("/annuaire_ens/filieres");
			return;
		}
		boolean res=false;
		String nom = request.getParameter("nom");
		Long departementId =Long.parseLong(request.getParameter("departement_id"));
		filiere.setNom(nom);
		filiere.setDepartementId(departementId);
		
		try {
			res=this.repository.update(filiere);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(res) {
			String message = "Filiere modifiee";
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
		response.sendRedirect("/annuaire_ens/filieres");
	}
	protected void supprimer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		if (request.getParameter("id") == null) {
					
			response.sendRedirect("/annuaire_ens/filieres");
			return;
		}
		else {
			try {
			    Long id = Long.parseLong(request.getParameter("id"));
			    try {
			    	Filiere filiere=this.repository.findById(id);
			    	if(filiere!= null) {
			    		this.repository.delete(filiere);
			    		String message = "Filiere supprimee";
						String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
						Cookie cookie = new Cookie("message", encodedMessage);
				        cookie.setMaxAge(3); 
				        response.addCookie(cookie);
			    		response.sendRedirect("/annuaire_ens/filieres");
						return;
			    	}
			    	else {
			    		String message = "Filiere invalide";
						String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
						Cookie cookie = new Cookie("erreur", encodedMessage);
				        cookie.setMaxAge(3); 
				        response.addCookie(cookie);
			    		response.sendRedirect("/annuaire_ens/filiere");
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
				response.sendRedirect("/annuaire_ens/filieres");
				return;
			}
		}
	}

}
