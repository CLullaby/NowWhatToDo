package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import dao.fabrique.DaoFabrique;
import dao.instance.DaoCompte;

/**
 * Servlet implementation class LoginBisServlet
 */
@WebServlet("/LoginBis")
public class LoginBisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginBisServlet() {
        super();
    }

    //Permet de verifier si l'user est loge pour afficher le bouton logout
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		JSONObject jsonToSend = new JSONObject();
		
		//Vérifie si l'utilisateur est connecté
		HttpSession session = request.getSession();
		if(session != null)
		{
			String login = (String) session.getAttribute("connecte");
			String loginBis = (String) session.getAttribute("connecteAdmin");
			if(login != null && login != "")
			{
				jsonToSend.put("login", login);	
				jsonToSend.put("etat", "loge");		
			}
			if(loginBis != null && loginBis != "")
			{
				jsonToSend.put("login", loginBis);
				jsonToSend.put("etat", "loge");	
			}
		}
		else{
			jsonToSend.put("etat", "nonLoge");
		}	
		
		PrintWriter out = response.getWriter();
		out.write(jsonToSend.toString());
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null)
		{
			session.setAttribute("connecte", "");
			session.setAttribute("connecteAdmin", "");
		}
		response.sendRedirect("/NowWhatToDo2/vues/accueil/accueil.html");
	}

}
