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
	
	private final static String NOM_TACHE_LABEL = "nomTache";
	private final static String NOM_DOMAINE_LABEL = "domaine";
	private final static String DESCRIPTION_LABEL = "dscription";
	
	private String etat;
	
       
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

		
//		//Aller cherhcer dans la session le nom de la personne loge
//		HttpSession session = request.getSession();
//		String nomUtilisateur = (String) session.getAttribute("connecte");
//		//Requete la DAO
//		
//		//JSON de retour
//		
//		
//		//Recupération du nom de domaine sélectionée dans la page acceil
//				String domaine = request.getParameter("domaine");
//
//				//Récupération des activités du domaine dans la BD 
//				ArrayList<ActiviteModelBean> allActList = daoActivite.getActivitebyDomaine(domaine);
//				ArrayList listeGeneral = new ArrayList();
//				List<String> nomAct = new ArrayList();
//				
//				//initialisation de la liste en recuperant tout les nom d'activite dans le domaine recupere
//				//Création d'une liste contenant les noms d'activités recensées 
//				for (Iterator<ActiviteModelBean> actIter = allActList.iterator(); actIter.hasNext();)
//				{
//					ActiviteModelBean current = actIter.next();
//					if(!nomAct.contains(current.getNomActivite()))
//					{
//						nomAct.add(current.getNomActivite());
//						//System.out.println(" nom d'act :" + current.getNomActivite() + " ");
//					}
//				}
//
//				
//				//Création d'une liste d'ActiviteModelBean triées par nom d'activité
//				for(int i = 0; i< nomAct.size(); i++)
//				{
//					//chaque itteration correspond a une nouvelle activite
//					ArrayList<ActiviteModelBean> liste  = new ArrayList<ActiviteModelBean>();
//					for (Iterator<ActiviteModelBean> actIter = allActList.iterator(); actIter.hasNext();)
//					{	
//						//si le nom de l'activite courrante correspond au nom de l'activite de l'element courant
//						//on ajoute le bean dans la liste courante 
//						ActiviteModelBean current = actIter.next();
//						if(current.getNomActivite().equals(nomAct.get(i)))
//						{
//							liste.add(current);
//						}
//					}
//					//pour ne pas perdre les listes on les mets dans une liste de liste
//					listeGeneral.add(liste);
//				}
//				
//				//Ininitialisation de l'envoi du tableau de json vers transportScript.js
//				response.setContentType("application/json");
//				JSONArray arrayActiviteList;
//				PrintWriter out = response.getWriter();
//				
//				try {
//					//Remplissage du tableau avec la liste d'ActiviteModelBean créée précédemment
//					arrayActiviteList = createJSONtoSend(listeGeneral);
//					out.write(arrayActiviteList.toString());
//					System.out.println(arrayActiviteList);
//					
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				out.close();
//		
//		
//		
		

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
//					jsonToSend.put("termine", listeTerminee);
//					jsonToSend.put("enCours", listeEnCours);
//					jsonToSend.put("pasCommence", listePasCommencee);
				}
			}
			else
			{
				//jsonToSend.put("etat", "nonLoge");
				etat =  "nonLoge";
			}
		}
		else
		{
			//jsonToSend.put("etat", "nonLoge");
			etat =  "nonLoge";
		}
		
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());
		out.close();	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//Permet de mettre a jour, l'avancement d'un utilisateur
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

	
	public JSONArray createJSONtoSend(ArrayList<AvancementActiviteModelBean> listeTerminee, ArrayList<AvancementActiviteModelBean> listeEnCours, 
			ArrayList<AvancementActiviteModelBean> listePasCommencee, String etat){
		
		JSONArray arrayResponse = new JSONArray();
		
		Iterator<AvancementActiviteModelBean> iteratorTerminee = listeTerminee.iterator();
		Iterator<AvancementActiviteModelBean> iteratorEnCours = listeTerminee.iterator();
		Iterator<AvancementActiviteModelBean> iteratorPasCommencee = listeTerminee.iterator();

		
		JSONObject jsonConnection = new JSONObject();
		jsonConnection.put("etat", etat);
		arrayResponse.add(jsonConnection);
		
		
		while(iteratorTerminee.hasNext()){
			
			JSONObject jsonTerminee = new JSONObject();
			AvancementActiviteModelBean avancement = iteratorTerminee.next(); 
			
		}
		
		
		//while(iterator.hasNext()) {
	
			//Iterator<ActiviteModelBean> itActivite = iterator.next().iterator();
			
//			while(itActivite.hasNext()){			
//			
//				JSONObject jsonActivite = new JSONObject();
//				ActiviteModelBean activite =  itActivite.next();
//				
//				jsonActivite.put(NOM_DOMAINE_LABEL,activite.getNomActivite());
//				jsonActivite.put(NOM_DOMAINE_LABEL, activite.getDomaine());
//				jsonActivite.put(DESCRIPTION_LABEL, activite.getDescription());
//				
//
//				arrayResponse.add(jsonActivite);
//			}	
//		}
		return arrayResponse;
	}


}
