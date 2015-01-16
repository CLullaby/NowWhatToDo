package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.fabrique.DaoFabrique;
import dao.instance.DaoActivite;
import dao.instance.DaoAvancement;
import dao.instance.DaoCompte;

/**
 * Servlet implementation class CompteAvancementServlet
 */
@WebServlet("/CompteAvancementServlet")
public class CompteAvancementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoActivite daoActivite;
	private DaoCompte daoCompte;
	private DaoAvancement daoAvancement;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompteAvancementServlet() {
        super();
        this.daoCompte = DaoFabrique.getInstance().createUserDao();
        this.daoActivite = DaoFabrique.getInstance().createActiviteDao();
        this.daoAvancement = DaoFabrique.getInstance().createAvancementDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //Permet de rechercher l'avancement d'un utilisateur
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Aller cherhcer dans la session le nom de la personne loge
		
		//Requete la DAO
		
		//JSON de retour
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//Permet de mettre a jour, l'avancement d'un utilisateur
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Parametres
		
		//Verifier log
		
		//Demander le log , redirige si nécessaire
		
		//Requeter
		
	}

}
