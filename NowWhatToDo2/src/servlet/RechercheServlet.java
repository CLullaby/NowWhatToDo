package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
 * Servlet implementation class RechercheServlet
 */
@WebServlet("/Recherche")
public class RechercheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoActivite DaoActivite;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercheServlet() {
        super();
        DaoActivite = DaoFabrique.getInstance().createActiviteDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//Renvoie le résultat de la recherche
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String motCle = request.getParameter("motCle");
		
		ArrayList<ActiviteModelBean> liste = DaoActivite.RechercheParMotCle(motCle);
		
		
		JSONObject jsonToSend = new JSONObject();
		
		//jsonToSend.put("liste", );
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());
		out.close();
	}

}
