package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		DaoActivite dao = DaoFabrique.getInstance().createActiviteDao();
		List<ActiviteModelBean> listeActivites = dao.getAllActivite();

		//Cr�er un JsonArray comprenant toutes les activit�s
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(listeActivites);
		
		//Cr�er un json contenant le JsonArray
		JSONObject jsonToSend = new JSONObject();
		try {
			jsonToSend.put("listeActivites", jsonArray.get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Envoyer le Json contenant le JsonArray
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
