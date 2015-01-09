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

import classe.*;
import dao.instance.DaoCompte;
import dao.fabrique.DaoFabrique;
/**
 * Servlet implementation class InscriptionServlet
 */
//@WebServlet("/Inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoCompte DaoCompte;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
        this.DaoCompte = DaoFabrique.getInstance().createUserDao();
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
		//Recup des paramètres
		String login = request.getParameter("login"); //30
		String mdp = request.getParameter("mdp"); //200
		String mdpBis = request.getParameter("mdpBis");
		String email = request.getParameter("email"); //30
		String nom = request.getParameter("nom"); //30
		String prenom = request.getParameter("prenom"); //30
		String agee = request.getParameter("age"); //11
		String adresse = request.getParameter("adresse"); //50
		String codePostal = request.getParameter("codePostal"); //5 varchar
		String telephone = request.getParameter("telephone"); //20

		//Transformation des parametres
		int age = 0;
		try
		{
			age = Integer.parseInt(agee);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//Verifier Infos
		if(login.length() < 30 && mdp.length() < 200 && email.length() < 30 && nom.length() < 30 && prenom.length() < 30 && adresse.length() < 50 && codePostal.length() < 6 && telephone.length() < 20
				&& mdp.equals(mdpBis))
		{
			//Crétaion du modèle
			CompteModelBean compte = new CompteModelBean(nom, prenom, login, mdp, email, age, "", adresse, codePostal, telephone, Enumerations.Utilisateur.returnValue());

			//Appel a la DAO + ajout dans le BD
			DaoCompte.addUtilisateur(compte);
		}
		else
		{
			//Prévoir la cas ou les conditions du if ne sont pas respectéees
			//return false;
		}

	}

}
