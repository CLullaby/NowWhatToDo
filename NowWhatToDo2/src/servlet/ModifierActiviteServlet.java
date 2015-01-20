package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.ActiviteModelBean;
import dao.fabrique.DaoFabrique;
import dao.instance.DaoActivite;

/**
 * Servlet implementation class ModifierActiviteServlet
 */
@WebServlet("/ModifierActivite")
public class ModifierActiviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifierActiviteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Récupération de l'id de l'activité à modifier
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);

		// Récupération des informations de l'activité concernée
		DaoActivite dao = DaoFabrique.getInstance().createActiviteDao();
		ActiviteModelBean activite = dao.getActiviteById(id);

		//Passer le domaine et l'activité en minuscule (pour faciliter la recherche par mot clé par la suite)
		String domaine = activite.getDomaine();
		domaine = domaine.toLowerCase();
		String nomActivite = activite.getNomActivite();
		nomActivite = nomActivite.toLowerCase();
		
		// Créer un json contenant le JsonArray
		JSONObject jsonToSend = new JSONObject();
		jsonToSend.put("domaine", domaine);
		jsonToSend.put("nomActivite", nomActivite);
		jsonToSend.put("nomLieu",activite.getNomLieu());
		jsonToSend.put("adresse", activite.getAdresse());
		jsonToSend.put("ville", activite.getVille());
		jsonToSend.put("codePostal", activite.getCodePostal());
		jsonToSend.put("siteWeb", activite.getSiteWeb());
		jsonToSend.put("telephone", activite.getTelephone());
		jsonToSend.put("email", activite.getEmail());
		jsonToSend.put("description",activite.getDescription());
		jsonToSend.put("lienPhoto", activite.getLienPhoto());
		jsonToSend.put("importance", activite.getImportance());

		// Envoyer le Json contenant le JsonArray
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());
		out.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//Récupération des données entrées en formulaire HTML
		String idString = request.getParameter("id");
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
		String importanceString = request.getParameter("importance");

		//Parse en INT
		int id = Integer.parseInt(idString);
		int importance = Integer.parseInt(importanceString);

		//Création de l'activité avec les données modifiées
		ActiviteModelBean newActivite = new ActiviteModelBean(activite,
				description, lieu, adresse, ville, codePostal, siteWeb,
				telephone, email, domaine, lienPhoto, importance);
		
		
		DaoActivite dao = DaoFabrique.getInstance().createActiviteDao();
		dao.updateActivite(id, newActivite);

		// Renvoyer une trame HTTP sous forme de JSON
		// Format the answer
		response.setContentType("application/json");
		JSONObject jsonToSend;
		jsonToSend = new JSONObject();
		jsonToSend.put("planSet", "yes");

		// Send the Json object to the web browser
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());
		out.close();
		
	}

}
