package com.ens.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ens.models.Departement;
import com.ens.models.Etudiant;
import com.ens.models.Filiere;
import com.ens.repositories.DepartementRepository;
import com.ens.repositories.EtudiantRepository;
import com.ens.repositories.FiliereRepository;

/**
 * Servlet implementation class EtudiantServlet
 */

public class EtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EtudiantRepository reporisory;
	private FiliereRepository filiereReporisory;
	private DepartementRepository departementReporisory;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EtudiantServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.reporisory=new EtudiantRepository();
        this.filiereReporisory=new FiliereRepository();
        this.departementReporisory=new DepartementRepository();
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
			case "rechercher": {
				rechercher(request, response);
				break;
			}
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
				response.sendRedirect("/annuaire_ens/etudiants");
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
		
			
			try {
				if (search==null) {
					request.setAttribute("list", this.reporisory.findAll());
				}
				else {
					request.setAttribute("list", this.reporisory.findByName(search));
				}
				request.setAttribute("filieres", this.filiereReporisory.findAll());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			    Long id = Long.parseLong(request.getParameter("id"));
			    try {
			    	if(this.reporisory.findById(id)==null) {
			    		response.sendRedirect("/annuaire_ens/etudiants");
			    		return;
			    	}
					request.setAttribute("list", this.reporisory.findAll());
					request.setAttribute("etudiant",this.reporisory.findById(id));
					request.setAttribute("filieres", this.filiereReporisory.findAll());
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
		
		request.getRequestDispatcher("/WEB-INF/views/etudiants/rechercherEtudiant.jsp").forward(request, response);
	}
	
	protected void ajouter(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		boolean res=false;
		Long CNE = Long.parseLong( request.getParameter("CNE"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		Filiere filiere=new Filiere();
		Departement departement = new Departement();
		Long filiereId =Long.parseLong(request.getParameter("filiere"));
		try {
			filiere=this.filiereReporisory.findById(filiereId);
			departement=this.departementReporisory.findById(filiere.getDepartementId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String telephone = request.getParameter("telephone");
		Etudiant etudiant = new Etudiant();
		etudiant.setCNE(CNE);
		etudiant.setNom(nom);
		etudiant.setPrenom(prenom);
		etudiant.setFiliere(filiere.getNom());
		etudiant.setDepartement(departement.getNom());
		etudiant.setTelephone(telephone);
		
		try {
			res=this.reporisory.save(etudiant);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(res) {
			String message = "Etudiant ajoutee";
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
		response.sendRedirect("/annuaire_ens/etudiants");
	}
	protected void editer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		if (request.getParameter("id") == null) {
			
			response.sendRedirect("/annuaire_ens/etudiants");
			return;
		}
		Long id = Long.parseLong(request.getParameter("id"));
		Etudiant etudiant = this.reporisory.findById(id);
		if(etudiant==null) {
			String message = "Etudiant invalide";
			String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
			Cookie cookie = new Cookie("erreur", encodedMessage);
	        cookie.setMaxAge(3); 
	        response.addCookie(cookie);
    		response.sendRedirect("/annuaire_ens/etudiants");
			return;
		}
		boolean res=false;
		Long CNE = Long.parseLong( request.getParameter("CNE"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		Filiere filiere=new Filiere();
		Departement departement = new Departement();
		Long filiereId =Long.parseLong(request.getParameter("filiere"));
		try {
			filiere=this.filiereReporisory.findById(filiereId);
			departement=this.departementReporisory.findById(filiere.getDepartementId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String telephone = request.getParameter("telephone");
		
		etudiant.setCNE(CNE);
		etudiant.setNom(nom);
		etudiant.setPrenom(prenom);
		etudiant.setFiliere(filiere.getNom());
		etudiant.setDepartement(departement.getNom());
		etudiant.setTelephone(telephone);
		
		try {
			res=this.reporisory.update(etudiant);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(res) {
			String message = "Etudiant modifiee";
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
		response.sendRedirect("/annuaire_ens/etudiants");
	}
	protected void supprimer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		if (request.getParameter("id") == null) {
					
			response.sendRedirect("/annuaire_ens/etudiants");
			return;
		}
		else {
			try {
			    Long id = Long.parseLong(request.getParameter("id"));
			    try {
			    	Etudiant etudiant=this.reporisory.findById(id);
			    	if(etudiant!= null) {
			    		this.reporisory.delete(etudiant);
			    		String message = "Etudiant supprimee";
						String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
						Cookie cookie = new Cookie("message", encodedMessage);
				        cookie.setMaxAge(3); 
				        response.addCookie(cookie);
			    		response.sendRedirect("/annuaire_ens/etudiants");
						return;
			    	}
			    	else {
			    		String message = "Etudiant invalide";
						String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
						Cookie cookie = new Cookie("erreur", encodedMessage);
				        cookie.setMaxAge(3); 
				        response.addCookie(cookie);
			    		response.sendRedirect("/annuaire_ens/etudiants");
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
				response.sendRedirect("/annuaire_ens/etudiants");
				return;
			}
		}
	}
}
