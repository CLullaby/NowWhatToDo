package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ActiviteModelBean;
import model.CompteModelBean;
import model.DomaineModelBean;
import dao.fabrique.DaoFabrique;
import dao.instance.DaoActivite;
import dao.instance.DaoCompte;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/test")
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testServlet() {
        super();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("hello je suis cense avoir ecrit");
		DaoCompte dao = DaoFabrique.getInstance().createUserDao();
//		CompteModelBean user = new CompteModelBean();
//		user.setNom("blabla");
//		user.setPrenom("titit");
//		user.setIdentifiant("toto");
//		user.setMotDePasse("hahaha");
//		user.setEmail("toto.toto@toto.fr");
//		user.setAge(12);
//		user.setLienPhoto("hahahahha");
//		user.setAdresse("rue cpe ");
//		user.setRole("comedien");
//		dao.addUtilisateur(user);
		
		CompteModelBean user2 = new CompteModelBean();
		user2.setNom("titititit");
		user2.setPrenom("tototo");
		user2.setIdentifiant("toto");
		user2.setMotDePasse("hahaha");
		user2.setEmail("toto.toto@toto.fr");
		user2.setAge(25);
		user2.setLienPhoto("hahahahha");
		user2.setAdresse("rue cpe ");
		user2.setRole("boulaner");
		
		dao.addUtilisateur(user2);
		
		ArrayList<CompteModelBean> userList = dao.getAllUser();
		for(CompteModelBean user: userList)
		{
			System.out.println(user.getNom() + " "+ user.getPrenom());
		}
		
		System.out.println("hello je suis cense avoir ecrit");
		
		
		//Tests création - mise à jour d'activités et domaines
		//Attention créer les domaines avant les activités (contrainte de clé etrangère)
		
		DaoActivite daoActivite = DaoFabrique.getInstance().createActiviteDao();
		
		ActiviteModelBean activite = createActivite();
		DomaineModelBean domaine = createDomaine();
		
		daoActivite.addDomaine(domaine);
		daoActivite.addActivite(activite);
		
		ActiviteModelBean updateActivite = createActivite();
		updateActivite.setNom("Corde");
		
		DomaineModelBean updateDomaine = createDomaine();
		updateDomaine.setNom("Remplacement");
		
		
		daoActivite.updateDomaine(domaine, updateDomaine);
		daoActivite.updateActivite(activite, updateActivite);
		
		//daoActivite.deleteActivite(updateActivite);
		//daoActivite.deleteDomaine(updateDomaine);
		
		
	}
	
	
	ActiviteModelBean createActivite(){
		ActiviteModelBean testActivite = new ActiviteModelBean();
		testActivite.setNom("Code Bleu");
		testActivite.setDescription("Urgence");
		testActivite.setIdDomaine(1);
		testActivite.setSiteWeb("http://www.epicfail.com");
		return testActivite;
		
	}
	
	DomaineModelBean createDomaine(){
		DomaineModelBean testDomaine = new DomaineModelBean();
		testDomaine.setNom("Essai de domaine");
		return testDomaine;
	}

}
