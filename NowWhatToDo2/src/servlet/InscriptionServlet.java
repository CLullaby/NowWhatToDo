package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

/**
 * Servlet implementation class InscriptionServlet
 */
//@WebServlet("/Inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/*
		String resultatIdentification = request.getParameter("");

	    System.out.println(resultatIdentification); 

		Object obj = JSONValue.parse(resultatIdentification);
	    JSONArray identificationValeurs = (JSONArray)obj;
	   
	    JSONObject values = (JSONObject) identificationValeurs.get(0);
	    String identifiantValeurFinale = " ";
	    String motdePasseValeurFinale = " ";
	    
	    System.out.println(values);
//	    try {
//			String identifiant = values.getString(IDENTIFICATION_LABEL);
//			identifiantValeurFinale = identifiant;
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    try {
//			String motDePasse = values.getString(MOT_DE_PASSE_LABEL);
//			motdePasseValeurFinale = motDePasse;
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    /*
	    System.out.println(identifiantValeurFinale+motdePasseValeurFinale);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		String mdpBis = request.getParameter("mdpBis");
		String email = request.getParameter("email");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		String telephone = request.getParameter("telephone");
		
		//Verifier Infos
		
		//Ajouter le compte
		int i = 0;
	}

}
