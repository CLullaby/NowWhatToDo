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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String resultatIdentification = request.getParameter(IDENTIFICATION_RESULT_LABEL);
		Object obj = JSONValue.parse(resultatIdentification);
		JSONArray array = (JSONArray) obj;
		Iterator iterator = array.iterator();
		
		String identifiantValeurFinale = " ";
		String motdePasseValeurFinale = " ";

		
		while(iterator.hasNext()) {
	    	
	    	JSONObject values = (JSONObject) iterator.next();
	    	identifiantValeurFinale = (String) values.get(IDENTIFICATION_LABEL);
	    	motdePasseValeurFinale =  (String) values.get(MOT_DE_PASSE_LABEL);
		
		}
	 
		//Cryptage sha256
		//motdePasseValeurFinale = daoCompte.hasher(motdePasseValeurFinale);
		
	   //TO DO comparer valeurs
	   //Regarder si user existe dans la BD
	   //Si existe: log dans session et redirection vers page compte
		//sinon: redirection vers login + message d'erreur
	
	   LoginBean currentUser = new LoginBean();	
	   currentUser.setIdentifiant(identifiantValeurFinale);
	   currentUser.setMotDePasse(motdePasseValeurFinale);
	   currentUser.setConnected(daoCompte.checkUtilisateur(currentUser));
		
	   System.out.println("requete");
	   
	   response.setContentType("application/json");
	   JSONArray arrayResponse = new JSONArray();
	   JSONObject jsonToSend = new JSONObject();
	   
	   
	   if(currentUser.isConnected()){
		   
		   HttpSession session = request.getSession();
		   session.putValue("connected", true);
		   session.putValue("login", identifiantValeurFinale);
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
