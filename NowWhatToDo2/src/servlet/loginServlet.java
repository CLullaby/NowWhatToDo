package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import model.LoginBean;
import dao.instance.DaoCompte;
import dao.fabrique.DaoFabrique;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

@WebServlet("/Login")
public class loginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static String IDENTIFICATION_RESULT_LABEL = "identificationForm";
	private final static String IDENTIFICATION_LABEL = "identifiant";
	private final static String MOT_DE_PASSE_LABEL = "motDePasse";
	private DaoCompte daoCompte;
	
    public loginServlet() {
        super();
        this.daoCompte = DaoFabrique.getInstance().createUserDao();
    }

	//Partie Admin
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		String value = "";
		
		if(daoCompte.checkUtilisateurAdmin(login, mdp))
		{
			//Connection dans la session
			HttpSession session = request.getSession();
			if(session != null)	
			{
				session.setAttribute("connecteAdmin", login);
			}
			value="oui";
		}
		else
		{
			value="non";
		}
	
		response.setContentType("application/json");
		JSONObject jsonToSend = new JSONObject();
		jsonToSend.put("connecte", value);
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());
		out.close();
	}
	
	//Partie utilisateur
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String resultatIdentification = request.getParameter(IDENTIFICATION_RESULT_LABEL);
		Object obj = JSONValue.parse(resultatIdentification);
		JSONArray array = (JSONArray) obj;
		Iterator iterator = array.iterator();

		String login = "";
		String mdp = "";
		
		while(iterator.hasNext()) {
	    	JSONObject values = (JSONObject) iterator.next();
	    	login = (String) values.get(IDENTIFICATION_LABEL);
	    	mdp =  (String) values.get(MOT_DE_PASSE_LABEL);
		}

	   response.setContentType("application/json");
	   JSONArray arrayResponse = new JSONArray();
	   JSONObject jsonToSend = new JSONObject();
	   
	   //if(daoCompte.checkUtilisateur(login, mdp)){
	   if(login.equals("aaa") && mdp.equals("bbb"))
	   {		
		   HttpSession session = request.getSession();
		   if(session != null)
		   {
			   session.setAttribute("connecte", login);
		   }
		   jsonToSend.put("resultat", "succes");
	   }
	   else{
			jsonToSend.put("resultat", "echec");	
	   }
	   
	   arrayResponse.add(jsonToSend);
	   PrintWriter out = response.getWriter();
	   out.write(arrayResponse.toString());
	   out.close();
	}
}
