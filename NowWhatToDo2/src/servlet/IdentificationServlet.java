package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;









//import org.json.JSONException;
//import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class IdentificationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static String IDENTIFICATION_RESULT_LABEL = "identificationForm";
	private final static String IDENTIFICATION_LABEL = "identifiant";
	private final static String MOT_DE_PASSE_LABEL = "motDePasse";

    public IdentificationServlet() {
    	super();
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
	 
	    
	   //TO DO comparer valeurs
	   
	    
	
		
		//Format the answer
		response.setContentType("application/json");
		
		JSONArray arrayResponse = new JSONArray();
		
		JSONObject jsonToSend = new JSONObject();
		jsonToSend.put("identifiant", identifiantValeurFinale);
		jsonToSend.put("motDePasse",motdePasseValeurFinale);
		
		arrayResponse.add(jsonToSend);
		
		PrintWriter out = response.getWriter();
		//out.write(jsonToSend.toString());
		out.write(arrayResponse.toString());
		out.close();
				
				
	
	}
}
