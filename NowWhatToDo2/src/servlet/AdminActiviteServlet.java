package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;

import model.ActiviteModelBean;
import dao.fabrique.DaoFabrique;
import dao.instance.DaoActivite;

/**
 * Servlet implementation class AdminActiviteServlet
 */
@WebServlet("/AdminActivite")
public class AdminActiviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminActiviteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// GET : Récupération de toutes les activités en base de données
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {

		JSONObject jsonToSend = new JSONObject();
		String value ="";
		
		//Vérifie si l'admin est connecté
		HttpSession session = request.getSession();
		if(session != null)
		{
			String login = (String) session.getAttribute("connecteAdmin");
			if(login != null && login != "")
			{	
				//Va chercher les données
				DaoActivite dao = DaoFabrique.getInstance().createActiviteDao();
				List<ActiviteModelBean> listeActivites = dao.getAllActivite();
		
				// Créer un JsonArray comprenant toutes les activités
				JSONArray jsonArray = new JSONArray();
				jsonArray.put(listeActivites);
		
				// Créer un json contenant le JsonArray
				try {
					jsonToSend.put("listeActivites", jsonArray.get(0));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				value = "oui";

			
			}
			else //Redirection
			{
				value = "non";
			}
		}
		
		// Envoyer le Json contenant le JsonArray + si admin est connecte
		jsonToSend.put("connecte",value);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// POST : suppression d'une activité par son ID
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Récupération de l'action à effectuer et l'id de l'activité sur
		// laquelle agir
		String action = request.getParameter("action");
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);

		//Suppression dans la base de données
		DaoActivite dao = DaoFabrique.getInstance().createActiviteDao();
		dao.deleteActivite(id);
		
		//Formation d'une réponse Json
		JSONObject jsonToSend = new JSONObject();
		jsonToSend.put("response", "OK");

		// Envoyer le Json contenant le JsonArray
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());
		out.close();
	}

}
