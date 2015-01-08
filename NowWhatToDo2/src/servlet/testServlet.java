package servlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import model.ActiviteModelBean;
import model.AvancementActiviteModelBean;
import model.CompteModelBean;
import model.LieuModelBean;
import dao.fabrique.DaoFabrique;
import dao.instance.DaoActivite;
import dao.instance.DaoAvancement;
import dao.instance.DaoCompte;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/vues/accueil/test")
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
		DaoAvancement daoA = DaoFabrique.getInstance().createAvancementDao();
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
		
//		CompteModelBean user2 = new CompteModelBean();
//		user2.setNom("titititit");
//		user2.setPrenom("tototo");
//		user2.setIdentifiant("toto");
//		user2.setMotDePasse("hahaha");
//		user2.setEmail("toto.toto@toto.fr");
//		user2.setAge(25);
//		user2.setLienPhoto("hahahahha");
//		user2.setAdresse("rue cpe ");
//		user2.setRole("boulaner");
//		
//		dao.addUtilisateur(user2);
		
//		AvancementActiviteModelBean act = new AvancementActiviteModelBean();
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		act.setAvancement(10);
//		act.setDateDebut("30-09-09");
//		act.setDateFin("02-02-10");
//		act.setCeCompte(1);
//		act.setCeActivite(2);
//		daoA.deleteAvancementActivite(3);
//		AvancementActiviteModelBean act2 = new AvancementActiviteModelBean();
//		// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		act2.setAvancement(7);
//		act2.setDateDebut("02-06-06");
//		act2.setDateFin("05-06-07");
//		act2.setCeCompte(3);
//		act2.setCeActivite(4);
//
//		daoA.updateAvancement(50, act2);
		
		
//		ArrayList<AvancementActiviteModelBean> userList = daoA.getAvancementActiviteByAvancement(50);
//		for(AvancementActiviteModelBean user: userList)
//		{
//			System.out.println(user.getAvancement() + " "+ user.getDateDebut());
//		}
//		
//		System.out.println("hello je suis cense avoir ecrit");
		
		
		//Tests création - mise à jour d'activités et lieux
	
		
		DaoActivite daoActivite = DaoFabrique.getInstance().createActiviteDao();
		
		ActiviteModelBean activite = createActivite();
		
		daoActivite.addActivite(activite);
		
		ActiviteModelBean updateActivite = createActivite();
		updateActivite.setNomActivite("Corde");
		
		
			
	
		daoActivite.updateActivite(activite, updateActivite);
		
		
		
		List<ActiviteModelBean> listeActivite = daoActivite.getAllActivite();
		for (ActiviteModelBean actif : listeActivite){
			System.out.println( "HAAHA: " +actif.getNomActivite());
			
		}
		daoActivite.deleteActivite(7);
		
		
	}
	
	
	ActiviteModelBean createActivite(){
		ActiviteModelBean testActivite = new ActiviteModelBean();
		testActivite.setNomActivite("Manger un repas");
		testActivite.setDescription("Venez manger");
		testActivite.setNomLieu("Hammamet");
		testActivite.setAdresse("2 rue Jubin");
		testActivite.setVille("Villeurbanne");
		testActivite.setCodePostal("69100");
		testActivite.setSiteWeb("www.hammamet-food.com");
		testActivite.setEmail("Hammamet@gmail.com");
		testActivite.setDomaine("Restauration");
		testActivite.setLienPhoto("/PHOTO");
		testActivite.setImportance(5);
		
		return testActivite;
	}
	

	


}
