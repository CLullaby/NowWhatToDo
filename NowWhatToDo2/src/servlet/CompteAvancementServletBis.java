package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.AvancementActiviteModelBean;


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
    
    public CompteAvancementServletBis() {
        super();
        this.daoCompte = DaoFabrique.getInstance().createUserDao();
        this.daoActivite = DaoFabrique.getInstance().createActiviteDao();
        this.daoAvancement = DaoFabrique.getInstance().createAvancementDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	//Met a jour l'avancement d'une activite d'un utilisateur
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Le parametre est forcement un int
		int idAvancementActivite = 0;
		String stringIdAvancementActivite = request.getParameter("idAvancementActivite");
		try {
			idAvancementActivite = Integer.parseInt(stringIdAvancementActivite);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		AvancementActiviteModelBean avancementActiviteModel = daoAvancement.getAvancementActiviteById(idAvancementActivite);
		int newAvancement = avancementActiviteModel.getAvancement() + 1;
		if(newAvancement < 3)
		{
			daoAvancement.updateAvancement(newAvancement, avancementActiviteModel);
		}
	}
	
}
