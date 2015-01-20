package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;


import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;

import org.json.simple.JSONArray;
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
       
    public CompteAvancementServlet() {
        super();
        this.daoCompte = DaoFabrique.getInstance().createUserDao();
        this.daoActivite = DaoFabrique.getInstance().createActiviteDao();
        this.daoAvancement = DaoFabrique.getInstance().createAvancementDao();
    }

    //Permet de rechercher l'avancement d'un utilisateur
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		
		//Aller chercher dans la session le nom de la personne loge -> elle est forcement loge car on apelle cette fonction ajax juste apres celle qui ca chercher
		//les infos pour remplir les input de données personnelles dans CompteServlet!!
		HttpSession session = request.getSession();
		if(session != null)
		{
			String login = (String) session.getAttribute("connecte");
			if(login != null && login != "") //-> elle est forcement loge mais par surete
			{				
				//Requete la DAO
				CompteModelBean compte = daoCompte.getUserLogin(login);
					
				ArrayList<AvancementActiviteModelBean> listeAvancementActivite = daoAvancement.getAvancementActiviteByCompte(compte.getId());
				
				/*Cherche les 3 infos complementaires : domaine, activite, description*/
				ArrayList<ActiviteModelBean> listeActivite = daoActivite.getAllActivite();		
				for(int i=0; i < listeAvancementActivite.size(); i++)
				{
					AvancementActiviteModelBean avancementActivite = listeAvancementActivite.get(i);
					
					for(int j=0; j < listeActivite.size(); j++)
					{
						ActiviteModelBean activite = listeActivite.get(j);
						if(activite.getId() == avancementActivite.getCeActivite())
						{
							avancementActivite.setNomDomaine(activite.getDomaine());
							avancementActivite.setNomActivite(activite.getNomActivite());
							avancementActivite.setDescriptionActivite(activite.getDescription());
						}
					}
					listeAvancementActivite.set(i, avancementActivite);
				}
				/*Fin*/
				
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
				}
				
				
				JSONArray arrayResponse = createJSONtoSend( listeTerminee, listeEnCours, listePasCommencee);
				PrintWriter out = response.getWriter();
				out.write(arrayResponse.toString());
				out.close();	
				
				//System.out.println(arrayResponse);
			}
		}	
	}

	//L'utilisateur a selectionne une activite a faire, création d'une ligne dans la BD por avancementActivite
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
					//0 pour que l'activite soit en pas commencée
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

	
	//Cree le json d'AvancementActiviteModelBean
	public JSONObject getJSONavancement(AvancementActiviteModelBean avancement){
		
		JSONObject json = new JSONObject();
		
		json.put("id", avancement.getIdAvancement());
		json.put("avancement", avancement.getAvancement());
		json.put("domaine", avancement.getNomDomaine());
		json.put("nomActivite", avancement.getNomActivite());
		json.put("description", avancement.getDescriptionActivite());
		
		return json;
	}
	
	//Revoie un tableau contenant 3 json pour chaque type d'avancement et chacun contenant un array de jsons d'AvancementActiviteModelBean
	//Ex: [{"termine":[]},{"enCours":[]},{"pasCommence":[{"nomActivite":"carte d'identité","domaine":"administration","description":""},{"nomActivite":"actes de naissance","domaine":"administration","description":""}]}]
	public JSONArray createJSONtoSend(ArrayList<AvancementActiviteModelBean> listeTerminee, ArrayList<AvancementActiviteModelBean> listeEnCours, 
			ArrayList<AvancementActiviteModelBean> listePasCommencee){
		
		JSONArray arrayResponse = new JSONArray();
		
		Iterator<AvancementActiviteModelBean> iteratorTerminee = listeTerminee.iterator();
		Iterator<AvancementActiviteModelBean> iteratorEnCours = listeEnCours.iterator();
		Iterator<AvancementActiviteModelBean> iteratorPasCommencee = listePasCommencee.iterator();

		
		JSONObject jsonListTerminee = new JSONObject();
		JSONArray arrayListTerminee = new JSONArray();
		while(iteratorTerminee.hasNext()){
			
			AvancementActiviteModelBean avancement = iteratorTerminee.next(); 
			JSONObject jsonTerminee =  getJSONavancement(avancement);
			arrayListTerminee.add(jsonTerminee);
			
		}
		jsonListTerminee.put("termine", arrayListTerminee);
		arrayResponse.add(jsonListTerminee);
		
		
		JSONObject jsonListEnCours = new JSONObject();
		JSONArray arrayListEnCours = new JSONArray();
		while(iteratorEnCours.hasNext()){
			
			AvancementActiviteModelBean avancement = iteratorEnCours.next(); 
			JSONObject jsonEnCours =  getJSONavancement(avancement);
			arrayListEnCours.add(jsonEnCours);
			
		}
		jsonListEnCours.put("enCours", arrayListEnCours);
		arrayResponse.add(jsonListEnCours);
		
		
		JSONObject jsonListPasCommencee = new JSONObject();
		JSONArray arrayListPasCommencee = new JSONArray();
		while(iteratorPasCommencee.hasNext()){
			
			AvancementActiviteModelBean avancement = iteratorPasCommencee.next(); 
			JSONObject jsonPasCommencee =  getJSONavancement(avancement);
			arrayListPasCommencee.add(jsonPasCommencee);
			
		}
		jsonListPasCommencee.put("pasCommence", arrayListPasCommencee);
		arrayResponse.add(jsonListPasCommencee);
		
		
		return arrayResponse;
	}


}
