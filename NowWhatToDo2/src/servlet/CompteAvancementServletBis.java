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
 * Servlet implementation class CompteAvancementServletBis
 */
@WebServlet("/CompteAvancementBis")
public class CompteAvancementServletBis extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoActivite daoActivite;
	private DaoCompte daoCompte;
	private DaoAvancement daoAvancement;
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CompteAvancementServletBis() {
        super();
        this.daoCompte = DaoFabrique.getInstance().createUserDao();
        this.daoActivite = DaoFabrique.getInstance().createActiviteDao();
        this.daoAvancement = DaoFabrique.getInstance().createAvancementDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//Met a jour l'avancement d'une activite d'un utilisateur
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int idActivite = request.getParameter("idActivite");
		
		
	}

}
