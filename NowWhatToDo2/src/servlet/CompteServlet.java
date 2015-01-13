package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CompteModelBean;
import dao.fabrique.DaoFabrique;
import dao.instance.DaoCompte;

/**
 * Servlet implementation class CompteServlet
 */
@WebServlet("/Compte")
public class CompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoCompte DaoCompte;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompteServlet() {
        super();
        this.DaoCompte = DaoFabrique.getInstance().createUserDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //Récupére les infos pour afficher la page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Vérifie si l'utilisateur est connecté
		/*if(false)
		{
			//Aller chercher les infos dans la BD
			
			//Former la reponse JSON
			
			
		}
		else{
			response.sendRedirect("../../compteNonConnecte.html");
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//Met a jour les champs de la page (pas du mdp)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login"); 
		String email = request.getParameter("email"); 
		String nom = request.getParameter("nom"); 
		String prenom = request.getParameter("prenom");
		String agee = request.getParameter("age"); 
		String adresse = request.getParameter("adresse");
		String codePostal = request.getParameter("codePostal"); 
		String telephone = request.getParameter("telephone"); 
		
		//Transformation des parametres
		int age = 0;
		try
		{
			age = Integer.parseInt(agee);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//Verifier Infos
		if(login.length() < 30 && email.length() < 30 && nom.length() < 30 && prenom.length() < 30 && adresse.length() < 50 && codePostal.length() < 6 && telephone.length() < 20)
		{
			//Récup du login dans la session
			HttpSession session = request.getSession();
			//Verif si user connecté
			if(session != null)	
			{
				if(session.getAttribute("connecte") != null)
				{
					String loginConnecte = (String) session.getAttribute("connecte");
					CompteModelBean compteBd = DaoCompte.getUserNom(loginConnecte);
					
					//Création du nouveau compte
					CompteModelBean compteNouveau = new CompteModelBean(nom, prenom, login, "", email, age, "", adresse, codePostal, telephone, compteBd.getRole());
		
					//Appel a la DAO + update dans le BD
					DaoCompte.updateUtilisateur(compteBd, compteNouveau); 
					
					response.sendRedirect("../vues/Compte/compte.html");
				}
			}
			//else de non connecté -> Pb bizarre de code -> non utile a priori
		}
		else
		{
			//Prévoir la cas ou les conditions du if ne sont pas respectéees 
			//-> Renvoie un texte en JSON
			
		}
	}
}
