package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ActiviteModelBean;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

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
		HttpSession session = request.getSession();
		String nomUtilisateur = (String) session.getAttribute("connecte");
		//Requete la DAO
		
		//JSON de retour
		
		
		//Recupération du nom de domaine sélectionée dans la page acceil
				String domaine = request.getParameter("domaine");

				//Récupération des activités du domaine dans la BD 
				ArrayList<ActiviteModelBean> allActList = daoActivite.getActivitebyDomaine(domaine);
				ArrayList listeGeneral = new ArrayList();
				List<String> nomAct = new ArrayList();
				
				//initialisation de la liste en recuperant tout les nom d'activite dans le domaine recupere
				//Création d'une liste contenant les noms d'activités recensées 
				for (Iterator<ActiviteModelBean> actIter = allActList.iterator(); actIter.hasNext();)
				{
					ActiviteModelBean current = actIter.next();
					if(!nomAct.contains(current.getNomActivite()))
					{
						nomAct.add(current.getNomActivite());
						//System.out.println(" nom d'act :" + current.getNomActivite() + " ");
					}
				}

				
				//Création d'une liste d'ActiviteModelBean triées par nom d'activité
				for(int i = 0; i< nomAct.size(); i++)
				{
					//chaque itteration correspond a une nouvelle activite
					ArrayList<ActiviteModelBean> liste  = new ArrayList<ActiviteModelBean>();
					for (Iterator<ActiviteModelBean> actIter = allActList.iterator(); actIter.hasNext();)
					{	
						//si le nom de l'activite courrante correspond au nom de l'activite de l'element courant
						//on ajoute le bean dans la liste courante 
						ActiviteModelBean current = actIter.next();
						if(current.getNomActivite().equals(nomAct.get(i)))
						{
							liste.add(current);
						}
					}
					//pour ne pas perdre les listes on les mets dans une liste de liste
					listeGeneral.add(liste);
				}
				
				//Ininitialisation de l'envoi du tableau de json vers transportScript.js
				response.setContentType("application/json");
				JSONArray arrayActiviteList;
				PrintWriter out = response.getWriter();
				
				try {
					//Remplissage du tableau avec la liste d'ActiviteModelBean créée précédemment
					arrayActiviteList = createJSONtoSend(listeGeneral);
					out.write(arrayActiviteList.toString());
					System.out.println(arrayActiviteList);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				out.close();
		
		
		
		
		
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
	
	public JSONArray createJSONtoSend(ArrayList<ArrayList> listActivite) throws JSONException{

		JSONArray arrayResponse = new JSONArray();
		Iterator<ArrayList> iterator = listActivite.iterator();

		
		while(iterator.hasNext()) {
	
			Iterator<ActiviteModelBean> itActivite = iterator.next().iterator();
			
			while(itActivite.hasNext()){			
			
				JSONObject jsonActivite = new JSONObject();
				ActiviteModelBean activite =  itActivite.next();
				
				jsonActivite.put(NOM_DOMAINE_LABEL,activite.getNomActivite());
				jsonActivite.put(NOM_DOMAINE_LABEL, activite.getDomaine());
				jsonActivite.put(DESCRIPTION_LABEL, activite.getDescription());
				

				arrayResponse.add(jsonActivite);
			}	
		}
		return arrayResponse;
	}


}
