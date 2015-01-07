package servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;







/**
 * Servlet implementation class IdentificationServlet
 */
//@WebServlet("/IdentificationServlet")
public class IdentificationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static String IDENTIFICATION_RESULT_LABEL = "identificationForm";
	private final static String IDENTIFICATION_LABEL = "identification";
	private final static String MOT_DE_PASSE_LABEL = "motDePasse";
       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
    public IdentificationServlet() {
    	super();
        // TODO Auto-generated constructor stub
    }
    
//    @Override
//	public void init() throws ServletException {
//		super.init();
//		// Create a connection to the datastore ONETIME at the init servlet
//		// process
//	}


//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		
		String resultatIdentification = request.getParameter(IDENTIFICATION_RESULT_LABEL);

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
	    
	    System.out.println(identifiantValeurFinale+motdePasseValeurFinale);
	   
	    
	
//		
//		//Format the answer
//		response.setContentType("application/json");
//		JSONObject jsonToSend;
//		jsonToSend = new JSONObject();
//		jsonToSend.put("plan_is_set", answer);
//		//System.out.println(jsonToSend.toString());
//		
//		//Send the Json object to the web browser
//		PrintWriter out = response.getWriter();
//		out.write(jsonToSend.toString());
//		out.close();
//				
//				
//			}
	}
}
