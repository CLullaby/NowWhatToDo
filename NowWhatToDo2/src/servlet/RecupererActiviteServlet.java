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

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import model.ActiviteModelBean;
import dao.fabrique.DaoFabrique;
import dao.instance.DaoActivite;

/**
 * Servlet implementation class RecupererActiviteServlet
 */
@WebServlet("/RecupererActivite")
public class RecupererActiviteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DaoActivite daoA;

	private final static String NOM_ACTIVITE_LABEL = "nomActivite";
	private final static String DESCRIPTION_LABEL = "Description";
	private final static String NOM_LIEU_LABEL = "nomLieu";
	private final static String ADRESSE_LABEL = "adresse";
	private final static String VILLE_LABEL = "ville";
	private final static String CODE_POSTAL_LABEL = "codePostal";
	private final static String SITE_WEB_LABEL = "siteWeb";
	private final static String TELEPHONE_LABEL = "telephone";
	private final static String EMAIL_LABEL = "email";
	private final static String DOMAINE_LABEL = "domaine";
	private final static String LIEN_PHOTO_LABEL = "lienPhoto";
	private final static String IMPORTANCE_LABEL = "importance";   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecupererActiviteServlet() {
        super();
        daoA = DaoFabrique.getInstance().createActiviteDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recupération du nom de domaine sélectionée dans la page acceil
		String domaine = request.getParameter("domaine");

		//Récupération des activités du domaine dans la BD 
		ArrayList<ActiviteModelBean> allActList = daoA.getActivitebyDomaine(domaine);
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
			//System.out.println(arrayActiviteList);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.close();
		
		
//		for( Iterator<ArrayList> listIter = listeGeneral.iterator(); listIter.hasNext();){
//			for( Iterator<ActiviteModelBean> sublistIter = listIter.next().iterator(); sublistIter.hasNext();){
//				
//				System.out.println("List by act"+ sublistIter.next().getNomActivite() +" ");
//			}
//		}
//		
	}
	
	//Fonction remplissant le tableau de structures JSON avec des couples clé/valeur correspondant aux noms de colonnes de la BD avec leur valaurs correspondantes
	public JSONArray createJSONtoSend(ArrayList<ArrayList> listActivite) throws JSONException{

		JSONArray arrayResponse = new JSONArray();
		Iterator<ArrayList> iterator = listActivite.iterator();

		
		while(iterator.hasNext()) {
	
			Iterator<ActiviteModelBean> itActivite = iterator.next().iterator();
			
			while(itActivite.hasNext()){			
			
				JSONObject jsonActivite = new JSONObject();
				ActiviteModelBean activite =  itActivite.next();
				
				jsonActivite.put(NOM_ACTIVITE_LABEL,activite.getNomActivite());
				jsonActivite.put(DESCRIPTION_LABEL,activite.getDescription());
				jsonActivite.put(NOM_LIEU_LABEL,activite.getNomLieu());
				jsonActivite.put(ADRESSE_LABEL,activite.getAdresse());
				jsonActivite.put(VILLE_LABEL,activite.getVille());
				jsonActivite.put(CODE_POSTAL_LABEL,activite.getCodePostal());
				jsonActivite.put(SITE_WEB_LABEL,activite.getSiteWeb());
				jsonActivite.put(TELEPHONE_LABEL,activite.getTelephone());
				jsonActivite.put(EMAIL_LABEL,activite.getEmail());
				jsonActivite.put(DOMAINE_LABEL,activite.getDomaine());
				jsonActivite.put(LIEN_PHOTO_LABEL,activite.getLienPhoto());
				jsonActivite.put(IMPORTANCE_LABEL,activite.getImportance());

				arrayResponse.add(jsonActivite);
			}	
		}
		return arrayResponse;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
