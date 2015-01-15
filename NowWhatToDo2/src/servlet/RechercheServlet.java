package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
import model.AvancementActiviteModelBean;
import dao.fabrique.DaoFabrique;
import dao.instance.DaoActivite;
import dao.instance.DaoAvancement;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Iterator;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import model.ActiviteModelBean;
//import dao.fabrique.DaoFabrique;
//import dao.instance.DaoActivite;
/**
 * Servlet implementation class RechercheServlet
 */
@WebServlet("/Recherche")
public class RechercheServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DaoActivite DaoActivite;
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
    public RechercheServlet() {
        super();
        DaoActivite = DaoFabrique.getInstance().createActiviteDao();
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
	//Renvoie le résultat de la recherche
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String motCle = request.getParameter("motCle");
		motCle.toLowerCase();
		ArrayList<ActiviteModelBean> liste = DaoActivite.RechercheParMotCle(motCle);
		JSONArray arrayResponse = new JSONArray();
		
		for( Iterator<ActiviteModelBean> listIter = liste.iterator(); listIter.hasNext();){
				
			ActiviteModelBean activite =  listIter.next();
			System.out.println("resultat recherche "+ activite.getNomActivite() +" ");
			
			JSONObject jsonActivite = new JSONObject();
			
			
			try {
				jsonActivite.put(NOM_ACTIVITE_LABEL, activite.getNomActivite());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jsonActivite.put(DESCRIPTION_LABEL, activite.getDescription());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jsonActivite.put(ADRESSE_LABEL, activite.getAdresse());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jsonActivite.put(CODE_POSTAL_LABEL, activite.getCodePostal());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jsonActivite.put(DOMAINE_LABEL, activite.getDomaine());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jsonActivite.put(TELEPHONE_LABEL, activite.getTelephone());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jsonActivite.put(LIEN_PHOTO_LABEL, activite.getLienPhoto());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jsonActivite.put(VILLE_LABEL, activite.getVille());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jsonActivite.put(SITE_WEB_LABEL, activite.getSiteWeb());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jsonActivite.put(NOM_LIEU_LABEL, activite.getNomLieu());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jsonActivite.put(IMPORTANCE_LABEL, activite.getImportance());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				jsonActivite.put(EMAIL_LABEL, activite.getEmail());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			arrayResponse.add(jsonActivite);
		
		}
		
		
		
		//jsonToSend.put("liste", );
		System.out.println(arrayResponse);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(arrayResponse.toString());
		out.close();
	}

}
