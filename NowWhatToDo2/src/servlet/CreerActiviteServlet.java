package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.simple.JSONObject;

import dao.fabrique.DaoFabrique;
import dao.instance.DaoActivite;
import model.ActiviteModelBean;

/**
 * Servlet implementation class CreerActiviteServlet
 */
@WebServlet("/CreerActivite")
public class CreerActiviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreerActiviteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//Permet de verifier l'authentification sur la page creerActiviteAdmin
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String value = "";
		//Vérifie si l'admin est connecté
		HttpSession session = request.getSession();
		if(session != null)
		{
			String login = (String) session.getAttribute("connecteAdmin");
			if(login != null && login != "")
			{
				value = "oui";
			}
			else
			{
				value = "non";
			}
		}
		else //Peu provable
		{
			value = "non";
		}
		
		response.setContentType("application/json");
		JSONObject jsonToSend = new JSONObject();
		jsonToSend.put("connecte", value);
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//Renvoie le formulaire
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String domaine = request.getParameter("domaine");
		String activite = request.getParameter("activite");
		String lieu = request.getParameter("lieu");
		String adresse = request.getParameter("adresse");
		String ville = request.getParameter("ville");
		String codePostal = request.getParameter("codePostal");
		String siteWeb = request.getParameter("siteWeb");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String description = request.getParameter("description");
		String lienPhoto = request.getParameter("lienPhoto");
		String importance = request.getParameter("importance");

		int importanceInteger = Integer.parseInt(importance);

		ActiviteModelBean newActivite = new ActiviteModelBean(activite,
				description, lieu, adresse, ville, codePostal, siteWeb,
				telephone, email, domaine, lienPhoto, importanceInteger);
		DaoActivite dao = DaoFabrique.getInstance().createActiviteDao();
		dao.addActivite(newActivite);

		// Renvoyer une trame HTTP sous forme de JSON
		// Format the answer
		response.setContentType("application/json");
		JSONObject jsonToSend;
		jsonToSend = new JSONObject();
		jsonToSend.put("planSet", "yes");

		// Send the Json object to the web browser
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());
	}

}
