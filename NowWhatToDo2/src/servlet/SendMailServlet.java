package servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import dao.fabrique.DaoFabrique;
import dao.instance.DaoCompte;
import model.CompteModelBean;
import model.MsgSend;

/**
 * Servlet implementation class SendMailServlet
 */
@WebServlet("/SendMail")
public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoCompte daoCompte;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMailServlet() {
        super();
        this.daoCompte = DaoFabrique.getInstance().createUserDao();
        // TODO Auto-generated constructor stub
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsonParam = request.getParameter("identificationForm");
		
		Object obj = JSONValue.parse(jsonParam);
		JSONArray array = (JSONArray) obj;
		Iterator iterator = array.iterator();

		String to = "";
		String msg = "";
		String nom ="";
		
		while(iterator.hasNext()) {
	    	JSONObject values = (JSONObject) iterator.next();
	    	to = (String) values.get("to");
	    	msg =  (String) values.get("msg");
	    	nom =  (String) values.get("nom");
		}
	   
		System.out.println("to:"+ to);
		System.out.println("msg"+ msg);
		System.out.println("nom:"+ nom);
		
//		response.setContentType("application/json");
//		JSONObject jsonToSend = new JSONObject();
//		PrintWriter out = response.getWriter();
//		out.write(jsonToSend.toString());
//		out.close();

		MsgSend mail = new MsgSend();
		
		//recuperer l'adresse mail de l'utilisateur qui est connecte 
		HttpSession session = request.getSession();
		String identifiant = (String) session.getAttribute("connecte");
		
		CompteModelBean user = daoCompte.getUserLogin(identifiant);
		String emailUser = user.getEmail();
		String mdp = user.getMotDePasse();
		
		System.out.println("emailUser:"+ emailUser);
		
		mail.setFrom(emailUser);
		mail.setTo(to);
		mail.setMsgText(msg);
		mail.setIdentif(identifiant);
		mail.setMdp(mdp);
		mail.envoiMail();
		
		
	}

}
