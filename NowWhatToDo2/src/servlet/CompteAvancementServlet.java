package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import model.ActiviteModelBean;
import model.AvancementActiviteModelBean;
import model.CompteModelBean;
import dao.fabrique.DaoFabrique;
import dao.instance.DaoActivite;
import dao.instance.DaoAvancement;
import dao.instance.DaoCompte;

/**
 * Servlet implementation class CompteAvancementServlet
 */
@WebServlet("/CompteAvancement")
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
		response.setContentType("application/json");
		JSONObject jsonToSend = new JSONObject();
		
		//Aller chercher dans la session le nom de la personne loge -> elle est forcement loge !!
		HttpSession session = request.getSession();
		if(session != null)
		{
			String login = (String) session.getAttribute("connecte");
			if(login != null && login != "") //-> elle est forcement loge mais par surete
			{				
				//Requete la DAO
				CompteModelBean compte = daoCompte.getUserLogin(login);
				
				ArrayList<AvancementActiviteModelBean> listeAvancementActivite = daoAvancement.getAvancementActiviteByCompte(compte.getId());
				
				ArrayList<AvancementActiviteModelBean> listeTerminee = new ArrayList<AvancementActiviteModelBean>();
				ArrayList<AvancementActiviteModelBean> listeEnCours = new ArrayList<AvancementActiviteModelBean>();
				ArrayList<AvancementActiviteModelBean> listePasCommencee = new ArrayList<AvancementActiviteModelBean>();
				
				//Cree 3 listes selon l'avancement termine/enCours/pasCommence
				for(int i=0; i < listeAvancementActivite.size(); i++)
				{
					AvancementActiviteModelBean avancementActiviteModelBean = listeAvancementActivite.get(i);
					
					if(avancementActiviteModelBean.getAvancement() == 0)
					{
						listePasCommencee.add(avancementActiviteModelBean);
					} else if(avancementActiviteModelBean.getAvancement() == 1)
					{
						listeEnCours.add(avancementActiviteModelBean);
					} else if(avancementActiviteModelBean.getAvancement() == 2)
					{
						listeTerminee.add(avancementActiviteModelBean);
					}
					jsonToSend.put("termine", listeTerminee);
					jsonToSend.put("enCours", listeEnCours);
					jsonToSend.put("pasCommence", listePasCommencee);
				}
			}
			else
			{
				jsonToSend.put("etat", "nonLoge");
			}
		}
		else
		{
			jsonToSend.put("etat", "nonLoge");
		}
		
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());
		out.close();	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//Permet de mettre a jour, l'avancement d'un utilisateur
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Return value
		response.setContentType("application/json");
		JSONObject jsonToSend = new JSONObject();
		
		//Parametres
		int idActivite = 0;
		String stringIdActivite = request.getParameter("idActivite");
		try {
			idActivite = Integer.parseInt(stringIdActivite);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//Si catch -> erreur -> message erreur dans le else
		
		//Verifier log
		HttpSession session = request.getSession();
		if(session != null)
		{
			String login = (String) session.getAttribute("connecte");
			if(login != null && login != "")
			{				
				CompteModelBean compte = daoCompte.getUserLogin(login);
				ActiviteModelBean activiteModel = daoActivite.getActiviteById(idActivite);
				
				if(compte != null && activiteModel != null)
				{
					Date date = new Date();
					AvancementActiviteModelBean activiteAvancementModel = new AvancementActiviteModelBean(0, date.toString(), "", compte.getId(), activiteModel.getId());
					daoAvancement.addAvancementActivite(activiteAvancementModel);
					jsonToSend.put("etat", "ok");
				}	
				else
				{
					jsonToSend.put("etat", "pb");
				}
			}
			else
			{
				jsonToSend.put("etat", "nonLoge");
			}
		}
		else
		{
			jsonToSend.put("etat", "nonLoge");
		}
		
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());
		out.close();	
	}
}
