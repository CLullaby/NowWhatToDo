package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.fabrique.DaoFabrique;
import dao.instance.DaoCompte;

/**
 * Servlet implementation class LoginBisServlet
 */
@WebServlet("/LoginBis")
public class LoginBisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoCompte daoCompte;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginBisServlet() {
        super();
        this.daoCompte = DaoFabrique.getInstance().createUserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //Permet de verifier si l'user est loge pour afficher le bouton logout
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
