package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VerificationConnexionServlet
 */
@WebServlet("/VerificationConnexion")
public class VerificationConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VerificationConnexionServlet() {
        super();
    }

    //Vérifie le log avant de renvoyer sur la page compte dans le menu
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null)
		{
			String login = (String) session.getAttribute("connecte");
			if(login != null && login != "")
			{
				response.sendRedirect("/NowWhatToDo2/vues/Compte/compte.html");
			}
			else
			{
				response.sendRedirect("/NowWhatToDo2/vues/Compte/utilisateurNonConnecte.html");
			}
		}
		//else non provable
		else
		{
			response.sendRedirect("../Compte/utilisateurNonConnecte.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
